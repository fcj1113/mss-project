package com.miaoshasha.redis.client;

import com.alibaba.fastjson.JSON;
import com.miaoshasha.common.utils.ByteUtil;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class JedisClusterClient<T> implements JedisClient<T> {

    public static final String OK = "ok";


    private JedisCluster jedisCluster;


    public JedisClusterClient(JedisCluster jedisCluster){
        this.jedisCluster = jedisCluster;
    }

    /************ String begin**************/
    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return jedisCluster.get(key);
    }


    /**
     * 获取带有对象的缓存
     *
     * @param key
     * @param tClass
     * @return
     */
    public T get(String key, Class<T> tClass) {
        return JSON.parseObject(jedisCluster.get(key), tClass);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    public boolean set(String key, String value) {
        return jedisCluster.set(key, value).equals(OK);
    }

    /**
     * 设置带失效时间的缓存
     *
     * @param key
     * @param seconds
     * @param value
     */
    public boolean set(String key, int seconds, String value) {
        return jedisCluster.setex(key, seconds, value).equals(OK);
    }

    /**
     * 存储对象
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, T value) {
        return jedisCluster.set(key, JSON.toJSONString(value)).equals(OK);
    }

    /**
     * 存储对象
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public boolean set(String key, int seconds, T value) {
        return jedisCluster.setex(key, seconds, JSON.toJSONString(value)).equals(OK);
    }


    /**
     * 存储对象
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setEntity(String key, T value) {
        byte[] bv = ByteUtil.ObjectToByte(value);
        return jedisCluster.set(key.getBytes(), bv).equals(OK);
    }

    /**
     * 存储对象
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public boolean setEntity(String key, int seconds, T value) {
        byte[] bv = ByteUtil.ObjectToByte(value);
        return jedisCluster.setex(key.getBytes(), seconds, bv).equals(OK);
    }


    /**
     * 获取缓存对象
     *
     * @param key
     * @return
     */
    public T getEntity(String key) {
        byte[] value = jedisCluster.get(key.getBytes());
        if (value == null) {
            return null;
        }
        return (T) ByteUtil.ByteToObject(value);
    }


    /**
     * 设置value到redis,如果key不存在则返回TRUE,否则返回FALSE
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public boolean setnx(String key, int seconds, T value) {
        try {
            return jedisCluster.setnx(key, JSON.toJSONString(value)) > 0;
        } finally {
            jedisCluster.expire(key, seconds);
        }
    }

    /**
     * 设置value到redis,如果key不存在则返回TRUE,否则返回FALSE
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setnx(String key, T value) {
        return jedisCluster.setnx(key, JSON.toJSONString(value)) > 0;
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public boolean delete(String key) {
        return jedisCluster.del(key) > 0;
    }

    /**
     * 删除多个keys
     *
     * @param keys
     * @return
     */
    public boolean delete(String... keys) {
        return jedisCluster.del(keys) > 0;
    }

    /************ String end**************/


    /************ Hash begin**************/

    /**
     * 将哈希表 key 中的字段 field 的值设为 value 。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    /**
     * 只有在字段 field 不存在时，设置哈希表字段的值。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hsetnx(String key, String field, String value) {
        return jedisCluster.hsetnx(key, field, value);
    }

    /**
     * 获取存储在哈希表中指定字段的值。
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @param fields
     * @return
     */
    public List<String> hmget(String key, String... fields) {
        return jedisCluster.hmget(key, fields);
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key
     * @param fields
     * @return
     */
    public boolean hdel(String key, String... fields) {
        return jedisCluster.hdel(key, fields) > 0;
    }

    /**
     * 获取所有哈希表中的字段
     *
     * @param key
     * @return
     */
    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }


    /**
     * 获取哈希表中所有值
     *
     * @param key
     * @return
     */
    public List<String> hvals(String key) {
        return jedisCluster.hvals(key);
    }

    /**
     * 获取在哈希表中指定 key 的所有字段和值
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    /************ Hash end**************/


    /************ List begin**************/

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param timeout
     * @param key
     * @return
     */
    public List<String> blpop(int timeout, String key) {
        return jedisCluster.blpop(timeout, key);
    }

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param timeout
     * @param key
     * @return
     */
    public List<String> brpop(int timeout, String key) {
        return jedisCluster.brpop(timeout, key);
    }

    /**
     * 获取长度
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        return jedisCluster.llen(key);
    }

    /**
     * 通过索引获取列表中的元素
     *
     * @param key
     * @param index
     * @return
     */
    public String lindex(String key, long index) {
        return jedisCluster.lindex(key, index);
    }


    /**
     * 通过索引设置列表元素的值
     *
     * @param key
     * @param index
     * @param value
     * @return
     */
    public String lset(String key, long index, String value) {
        return jedisCluster.lset(key, index, value);
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key
     * @return
     */
    public String lpop(String key) {
        return jedisCluster.lpop(key);
    }

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key
     * @return
     */
    public String rpop(String key) {
        return jedisCluster.rpop(key);
    }


    /**
     * 在列表中添加一个或多个值
     *
     * @param key
     * @param values
     * @return
     */
    public Long rpush(String key, String... values) {
        return jedisCluster.rpush(key, values);
    }

    /**
     * 将一个或多个值插入到列表头部
     *
     * @param key
     * @param string
     * @return
     */
    public Long lpush(String key, String... string) {
        return jedisCluster.lpush(key, string);
    }

    /**
     * 获取列表指定范围内的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, long start, long end) {
        return jedisCluster.lrange(key, start, end);
    }

    /**
     * Redis Ltrim 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。成功返回ok
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public boolean ltrim(String key, long start, long end) {
        return jedisCluster.ltrim(key, start, end).equals(OK);
    }

    /************ List end**************/


    /************ SET begin**************/

    /**
     * 向集合添加一个或多个成员
     *
     * @param key
     * @param member
     * @return
     */
    public boolean sadd(String key, String... member) {
        return jedisCluster.sadd(key, member) > 0;
    }

    /**
     * 返回集合中的所有成员，无序
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        return jedisCluster.smembers(key);
    }

    /**
     * 获取集合的成员数
     *
     * @param key
     * @return
     */
    public Long scard(String key) {
        return jedisCluster.scard(key);
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     *
     * @param key
     * @param member
     * @return
     */
    public Boolean sismember(String key, String member) {
        return jedisCluster.sismember(key, member);
    }


    /************ SET end**************/


    /************ sorted set begin**************/

    /**
     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Long zadd(String key, double score, String member) {
        return jedisCluster.zadd(key, score, member);
    }

    /************ sorted set end**************/

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间。
     * 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
     *
     * @param key
     * @return
     */
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    /**
     * 以毫秒为单位，返回给定 key 的剩余生存时间。
     * 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以毫秒为单位，返回 key 的剩余生存时间。
     *
     * @param key
     * @return
     */
    public Long pttl(String key) {
        return jedisCluster.pttl(key);

    }

    /**
     * 为给定 key 设置过期时间。
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }


}
