package com.miaoshasha.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

/**
 * service 基础抽象类
 * Created by fengchaojun on 2018/6/20.
 */
public abstract class AbstractBaseService<M extends BaseMapper<E>, E extends BaseEntity> implements BaseService<E> {

    @Autowired
    protected M mapper;

    /**
     * 保存对象
     *
     * @param e
     * @return 返回id
     */
    @Override
    public long save(E e) {
        //若是AbstractBaseEntity 则记录时间
        if(e instanceof AbstractBaseEntity){
            AbstractBaseEntity baseEntity = (AbstractBaseEntity)e;
            Timestamp current = new Timestamp(System.currentTimeMillis());
            if(baseEntity.getId() == null){
                baseEntity.setCreateTime(current);
            }
            baseEntity.setUpdateTime(current);
        }
        int res = mapper.insert(e);
        if (res > 0) {
            return e.getId();
        }
        return res;
    }

    /**
     * 修改对象
     *
     * @param e
     * @return
     */
    @Override
    public int modify(E e) {
        if(e instanceof AbstractBaseEntity){
            AbstractBaseEntity baseEntity = (AbstractBaseEntity)e;
            Timestamp current = new Timestamp(System.currentTimeMillis());
            baseEntity.setUpdateTime(current);
        }
        return mapper.updateByPrimaryKey(e);
    }

    /**
     * 删除对象
     *
     * @param pid
     * @return
     */
    @Override
    public int removeById(Long pid) {
        return mapper.deleteByPrimaryKey(pid);
    }


    /**
     * 根据查询条件查询所有数据
     *
     * @param e
     * @return
     */
    @Override
    public List<E> findAll(E e) {
        return mapper.selectAll(e);
    }


    /**
     * 根据id查询单个
     *
     * @param pid
     * @return
     */
    @Override
    public E findById(Long pid) {
        return mapper.selectByPrimaryKey(pid);
    }
}
