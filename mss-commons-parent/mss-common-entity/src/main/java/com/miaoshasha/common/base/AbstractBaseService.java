package com.miaoshasha.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
     * @param entity
     * @return 返回id
     */
    @Transactional
    @Override
    public long save(E entity) {
        //若是AbstractBaseEntity 则记录时间
        if(entity instanceof AbstractBaseEntity){
            AbstractBaseEntity baseEntity = (AbstractBaseEntity)entity;
            Timestamp current = new Timestamp(System.currentTimeMillis());
            if(baseEntity.getId() == null){//不存在id时，记录为insert时间
                baseEntity.setCreateTime(current);
            }
            baseEntity.setUpdateTime(current);
        }
        int res = mapper.insert(entity);
        if (res > 0) {
            return entity.getId();
        }
        return res;
    }

    /**
     * 修改对象
     *
     * @param entity
     * @return
     */
    @Transactional
    @Override
    public int modify(E entity) {
        if(entity instanceof AbstractBaseEntity){
            AbstractBaseEntity baseEntity = (AbstractBaseEntity)entity;
            Timestamp current = new Timestamp(System.currentTimeMillis());
            baseEntity.setUpdateTime(current);
        }
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 删除对象
     *
     * @param pid
     * @return
     */
    @Transactional
    @Override
    public int removeById(Long pid) {
        return mapper.deleteByPrimaryKey(pid);
    }


    /**
     * 根据查询条件查询所有数据
     *
     * @param entity
     * @return
     */
    @Override
    public List<E> findAll(E entity) {
        return mapper.selectAll(entity);
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
