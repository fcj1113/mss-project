package com.miaoshasha.seckill.mapper;

import com.miaoshasha.common.entity.store.PromoType;

public interface PromoTypeMapper {
    /**
     *
     * @param pid
     * @return
     */
    int deleteByPrimaryKey(Long pid);

    /**
     *
     * @param record
     * @return
     */
    int insert(PromoType record);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(PromoType record);

    /**
     *
     * @param pid
     * @return
     */
    PromoType selectByPrimaryKey(Long pid);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PromoType record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(PromoType record);
}