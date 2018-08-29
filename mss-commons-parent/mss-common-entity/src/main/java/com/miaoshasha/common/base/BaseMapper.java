package com.miaoshasha.common.base;

import java.util.List;

/**
 * Created by fengchaojun on 2018/5/8.
 */
public interface BaseMapper<E extends BaseEntity> {

    /**
     * 根据主键删除对象
     * @param pid
     * @return
     */
    int deleteByPrimaryKey(Long pid);

    /**
     * 保存对象
     * @param entity
     * @return
     */
    int insert(E entity);

    /**
     * 保存有值的数据
     * @param entity
     * @return
     */
    int insertSelective(E entity);

    /**
     * 根据主键查询详情
     * @param pid
     * @return
     */
    E selectByPrimaryKey(Long pid);

    /**
     * 修改只有值的数据
     * @param entity
     * @return
     */
    int updateByPrimaryKeySelective(E entity);

    /**
     * 根据主键修改内容
     * @param entity
     * @return
     */
    int updateByPrimaryKey(E entity);

    /**
     * 根据查询条件查询所有
     * @param entity
     * @return
     */
    List<E> selectAll(E entity);


}
