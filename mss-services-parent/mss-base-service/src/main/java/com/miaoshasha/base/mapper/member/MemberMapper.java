package com.miaoshasha.base.mapper.member;

import com.miaoshasha.common.entity.member.Member;

public interface MemberMapper {

    /**
     * @param memberId
     * @return
     */
    int deleteByPrimaryKey(Long memberId);

    /**
     * @param record
     * @return
     */
    int insert(Member record);

    /**
     * @param record
     * @return
     */
    int insertSelective(Member record);

    /**
     * @param memberId
     * @return
     */
    Member selectByPrimaryKey(Long memberId);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(Member record);
}