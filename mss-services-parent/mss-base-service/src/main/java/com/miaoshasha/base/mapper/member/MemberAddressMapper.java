package com.miaoshasha.base.mapper.member;

import com.miaoshasha.common.entity.member.MemberAddress;

public interface MemberAddressMapper {

    /**
     * @param addressId
     * @return
     */
    int deleteByPrimaryKey(Long addressId);

    /**
     * @param record
     * @return
     */
    int insert(MemberAddress record);

    /**
     * @param record
     * @return
     */
    int insertSelective(MemberAddress record);

    /**
     * @param addressId
     * @return
     */
    MemberAddress selectByPrimaryKey(Long addressId);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MemberAddress record);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(MemberAddress record);
}