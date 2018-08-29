package com.miaoshasha.base.mapper.member;


import com.miaoshasha.common.entity.member.MemberOpen;

public interface MemberOpenMapper {
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
    int insert(MemberOpen record);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(MemberOpen record);

    /**
     *
     * @param pid
     * @return
     */
    MemberOpen selectByPrimaryKey(Long pid);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MemberOpen record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(MemberOpen record);
}