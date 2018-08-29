package com.miaoshasha.redis.util;

import com.miaoshasha.redis.annotation.EnableCache;

public class ObjectHolder {

    private Object value ;

    public static ObjectHolder getInstance(Object value) {
        ObjectHolder objectHolder = new ObjectHolder();
        objectHolder.value = value;
        return objectHolder;
    }

    public Object getValue() {
        if (EnableCache.EMPTY.equals(value)) {
            return null;
        }
        return value;
    }


    /**
     * 值是否存在
     * @return
     */
    public boolean exist(){
        return value != null ;
    }
}
