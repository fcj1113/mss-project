package com.miaoshasha.seckill.service;

import com.miaoshasha.common.dto.order.PromoDTO;

/**
 * Created by fengchaojun on 2018/6/27.
 */
public interface SeckillService {

    /**
     * 下单
     * @param promoDTO
     * @return
     */
    void doSecKill(PromoDTO promoDTO);

}
