package com.miaoshasha.seckill.service.impl;

import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.store.PromoInfo;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.common.utils.RedisCache;
import com.miaoshasha.common.utils.Sequence;
import com.miaoshasha.redis.lock.DistributedLock;
import com.miaoshasha.seckill.mq.OrderMsgPublisher;
import com.miaoshasha.seckill.service.PromoService;
import com.miaoshasha.seckill.service.SeckillService;
import com.miaoshasha.seckill.util.CacheConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fengchaojun on 2018/6/27.
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private PromoService promoService;

    @Autowired
    private OrderMsgPublisher orderMsgPublisher;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private DistributedLock redisDistributedLock;

    public static final String REDIS_PROMO_LOCK = "LOCK_PROMO_";

    public static final long TIMEOUT_MILLIS = 2000;//设置超时时间2秒
    public static final int RETRY_TIMES = 5;//重试次数
    public static final long SLEEP_MILLIS = 200;//等待时间

    @Autowired
    private Sequence sequence;

    @Override
    public void doSecKill(PromoDTO promoDTO) {
        long promoId = promoDTO.getPromoId();
        String lockKey = REDIS_PROMO_LOCK + promoId;
        PromoInfo promoInfo = checkPromo(promoId);
        //锁定库存更新并发送消息
        if (redisDistributedLock.lock(lockKey, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS)) {
            long currTime = System.currentTimeMillis();
            try {
                //锁内进行校验，为了保证加锁过程中的库存数量
                promoInfo = checkPromo(promoId);
                int stockQuantity = promoInfo.getStockQuantity();//库存数量
                //3.发送mq消息，成功后更新redis中的库存信息，消息队列由订单服务进行消费
                //生成订单号
                long orderId = sequence.nextId();
                log.debug("新生成订单号："+orderId);
                promoDTO.getOrderInfo().setOrderId(orderId);
                promoDTO.getOrderInfo().setOrderNo("DD" + orderId);
                orderMsgPublisher.send(promoDTO);
                //扣除缓存中的库存
                promoInfo.setStockQuantity(stockQuantity - promoDTO.getOrderProduct().getQuantity());
                //更新库存
                //同步更新到redis中，并指定过期时间（失效时间-当前时间）
                redisCache.setForTimeMS(CacheConstant.REDIS_PREFIX + ":" + promoId, promoInfo, (promoInfo.getEndTime() - System.currentTimeMillis()));

            } finally {//释放锁
                boolean releaseResult = redisDistributedLock.releaseLock(lockKey);
                log.debug("释放锁 : " + lockKey + (releaseResult ? " success" : " failed"));
                log.debug("执行时长："+(System.currentTimeMillis()-currTime));
            }

        }
    }

    /**
     * 校验promo
     * @param promoId
     * @return
     */
    private PromoInfo checkPromo(long promoId){
        //1.先从redis中查询库存信息，进行库存判断
        PromoInfo promoInfo = promoService.findById(promoId);

        if (promoInfo.getEndTime() < System.currentTimeMillis()) {
            log.info("太遗憾了，活动已经结束");
            throw new SystemException(ErrorCode.EXECUTE_FAIL, "太遗憾了，活动已经结束");
        }

        if (promoInfo.getStockQuantity() <= 0) {
            log.info("太遗憾了，已经被抢光了");
            throw new SystemException(ErrorCode.EXECUTE_FAIL, "太遗憾了，已经被抢光了");
        }

        return promoInfo ;
    }

}
