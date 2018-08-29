package com.miaoshasha.common.base;

import java.util.List;

/**
 * Created by fengchaojun on 2018/5/8.
 */
public interface BaseService<E extends BaseEntity> {

    /**
     * 保存接口
     * @param entity
     * @return 返回主键id
     */
    long save(E entity);

    /**
     * 更新对象
     * @param entity
     * @return
     */
    int modify(E entity);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int removeById(Long id);

    /**
     * 查询全部
     * @param entity
     * @return
     */
    List<E> findAll(E entity);

    /**
     * 分页查询全部
     * @param paramMap 查询条件
     * @param startIndex 分页的偏移量
     * @param pageSize 每页数量
     * @return
     */
    //List<E> findByPage(Map<String, Object> paramMap, Integer startIndex, Integer pageSize);

    /**
     * 分页查询全部数量
     * @param paramMap 查询条件
     * @return
     */
    //long findAllCount(Map<String, Object> paramMap);


    /**
     * 根据id获取
     * @param id
     * @return
     */
    E findById(Long id) ;

}
