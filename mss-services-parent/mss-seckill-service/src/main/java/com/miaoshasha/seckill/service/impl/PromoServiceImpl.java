package com.miaoshasha.seckill.service.impl;

import com.miaoshasha.common.entity.store.PromoInfo;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.common.utils.RedisCache;
import com.miaoshasha.redis.annotation.EnableCache;
import com.miaoshasha.seckill.mapper.PromoInfoMapper;
import com.miaoshasha.seckill.mapper.PromoTypeMapper;
import com.miaoshasha.seckill.service.PromoService;
import com.miaoshasha.seckill.util.CacheConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengchaojun on 2018/6/14.
 */
@Service
public class PromoServiceImpl implements PromoService {

    private static final Logger logger = LoggerFactory.getLogger(PromoServiceImpl.class);

    @Autowired
    private PromoInfoMapper promoInfoMapper;

    @Autowired
    private PromoTypeMapper promoTypeMapper;

    @Autowired
    private RedisCache redisCache;


    @Override
    public long save(PromoInfo promoInfo) {

        //保存秒杀活动到数据库，保存成功后同时同步到redis中
        if (System.currentTimeMillis() >= promoInfo.getBeginTime()) {
            throw new SystemException(ErrorCode.EXECUTE_FAIL, "活动开始时间必须大于当前时间");
        }
        long cnt = promoInfoMapper.insert(promoInfo);
        long  promoId =  promoInfo.getPromoId();
        logger.info("promoId=========="+promoInfo.getPromoId());
        if (cnt > 0) {
            //同步到redis中，并指定过期时间（失效时间-当前时间）
            redisCache.setForTimeMS(CacheConstant.REDIS_PREFIX + ":" + promoId, this.findById(promoId), (promoInfo.getEndTime() - System.currentTimeMillis()));
        }
        return promoId;
    }

    @Override
    public int modify(PromoInfo promoInfo) {
        return 0;
    }

    @Override
    public int removeById(Long id) {
        int res = promoInfoMapper.deleteByPrimaryKey(id);
        if (res > 0) {
            //删除缓存
            redisCache.delete(CacheConstant.REDIS_PREFIX + ":" + id);
        }
        return res;
    }

    @Override
    public List<PromoInfo> findAll(PromoInfo promoInfo) {
        return promoInfoMapper.selectAll(promoInfo);
    }

    @Override
    @EnableCache(cacheName = CacheConstant.REDIS_PREFIX ,key = "#id")
    public PromoInfo findById(Long id) {
        return promoInfoMapper.selectByPrimaryKey(id);
    }


    public void getPromoInfo(){
        this.findById(3l);
    }
}
