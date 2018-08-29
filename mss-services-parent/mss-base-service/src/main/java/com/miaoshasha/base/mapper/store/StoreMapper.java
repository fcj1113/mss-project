package com.miaoshasha.base.mapper.store;


import com.miaoshasha.common.entity.store.Store;

public interface StoreMapper {
    /**
     *
     * @param storeId
     * @return
     */
    int deleteByPrimaryKey(Long storeId);

    /**
     *
     * @param record
     * @return
     */
    int insert(Store record);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(Store record);

    /**
     *
     * @param storeId
     * @return
     */
    Store selectByPrimaryKey(Long storeId);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Store record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(Store record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Store record);
}