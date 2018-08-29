package com.miaoshasha.seckill.util;

import com.miaoshasha.common.dto.order.PromoDTO;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 记录活动的请求，请求数量，不能超过promo的库存数量
 * Created by fengchaojun on 2018/7/5.
 */
public class PromoRequestCache {

    /**
     * 请求缓存，用于存储请求信息
     */
    private ConcurrentHashMap<Long, ConcurrentLinkedQueue<PromoDTO>> requestCache = new ConcurrentHashMap<>();

    /**
     * 用于记录请求的计数器
     */
    private ConcurrentHashMap<Long, Integer> requstCounter = new ConcurrentHashMap<>();


    /**
     * 记录库存总数量
     */
    private ConcurrentHashMap<Long, Integer> promoStockQuantity = new ConcurrentHashMap<>();


    private static volatile PromoRequestCache promoRequestCache = null;

    private PromoRequestCache() {
    }

    /**
     * 单例
     *
     * @return
     */
    public static PromoRequestCache getInstance() {
        if (promoRequestCache == null) {
            synchronized (PromoRequestCache.class) {
                if (promoRequestCache == null) {
                    promoRequestCache = new PromoRequestCache();
                }
            }
        }
        return promoRequestCache;
    }

    /**
     * 加入到当前promo中的队列中
     *
     * @param promoDTO
     */
    public boolean offer(PromoDTO promoDTO) {
        boolean flag = false;
        long promoId = promoDTO.getPromoId();
        Integer stockQuantity = promoStockQuantity.get(promoId);
        if (stockQuantity != null) {

            ConcurrentLinkedQueue<PromoDTO> queue = requestCache.get(promoId);
            int cnt = requstCounter.get(promoId);
            if (queue == null) {//若没有队列，则创建队列；并生成库存数据
                queue = new ConcurrentLinkedQueue<>();
                requestCache.put(promoId, queue);

                //生成对应的计数器
                cnt = 0;
                requstCounter.put(promoId, cnt);
            }
            //入列
            if (stockQuantity >= cnt) {//库存大于当前请求计数器的数时
                boolean res = queue.offer(promoDTO);
                if (res) {
                    cnt++;
                    flag = true;
                }
            }
        }

        return flag;
    }


    /**
     * 设置总库存
     *
     * @param promoId
     * @param stockQuantity
     */
    public void setPromoStockQuantity(long promoId, int stockQuantity) {
        if (promoStockQuantity.get(promoId) == null) {//保证第一次设置成功
            promoStockQuantity.put(promoId, stockQuantity);
        }
    }
}
