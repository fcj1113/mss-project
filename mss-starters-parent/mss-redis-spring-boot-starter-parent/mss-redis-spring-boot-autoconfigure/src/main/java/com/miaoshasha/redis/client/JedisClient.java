package com.miaoshasha.redis.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JedisClient<T> {


    /************ String begin**************/
    /**
     * 获取缓存
     * @param key
     * @return
     */
    String get(String key);


    /**
     * 获取带有对象的缓存
     * @param key
     * @param tClass
     * @return
     */
    T get(String key, Class<T> tClass);

    /**
     * 设置缓存
     * @param key
     * @param value
     */
    boolean set(String key, String value);

    /**
     * 设置带失效时间的缓存
     * @param key
     * @param seconds
     * @param value
     */
    boolean set(String key, int seconds, String value);

    /**
     * 存储对象
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, T value);

    /**
     * 存储对象
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    boolean set(String key, int seconds, T value);


    /**
     * 存储对象
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setEntity(String key, T value);

    /**
     * 存储对象
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public boolean setEntity(String key, int seconds, T value);

    /**
     * 获取缓存对象
     *
     * @param key
     * @return
     */
    public T getEntity(String key);


    /**
     * 设置value到redis,如果key不存在则返回TRUE,否则返回FALSE
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    boolean setnx(String key, int seconds, T value);

    /**
     * 设置value到redis,如果key不存在则返回TRUE,否则返回FALSE
     *
     * @param key
     * @param value
     * @return
     */
    boolean setnx(String key, T value);

    /**
     *
     * 删除缓存
     * @param key
     * @return
     */
    boolean delete(String key);

    /**
     * 删除多个keys
     * @param keys
     * @return
     */
    boolean delete(String... keys);

    /************ String end**************/


    /************ Hash begin**************/

    /**
     * 将哈希表 key 中的字段 field 的值设为 value 。
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hset(String key, String field, String value);

    /**
     * 只有在字段 field 不存在时，设置哈希表字段的值。
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hsetnx(String key, String field, String value);

    /**
     * 获取存储在哈希表中指定字段的值。
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     * 获取所有给定字段的值
     * @param key
     * @param fields
     * @return
     */
    List<String> hmget(String key, String... fields);

    /**
     * 删除一个或多个哈希表字段
     * @param key
     * @param fields
     * @return
     */
    boolean hdel(String key, String... fields);

    /**
     * 获取所有哈希表中的字段
     * @param key
     * @return
     */
    Set<String> hkeys(String key);


    /**
     * 获取哈希表中所有值
     * @param key
     * @return
     */
    List<String> hvals(String key);

    /**
     * 获取在哈希表中指定 key 的所有字段和值
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);

    /************ Hash end**************/


    /************ List begin**************/

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param timeout
     * @param key
     * @return
     */
    List<String> blpop(int timeout, String key);

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param timeout
     * @param key
     * @return
     */
    List<String> brpop(int timeout, String key);

    /**
     * 获取长度
     * @param key
     * @return
     */
    Long llen(String key);

    /**
     * 通过索引获取列表中的元素
     * @param key
     * @param index
     * @return
     */
    String lindex(String key, long index);


    /**
     *通过索引设置列表元素的值
     * @param key
     * @param index
     * @param value
     * @return
     */
    String lset(String key, long index, String value);

    /**
     * 移出并获取列表的第一个元素
     * @param key
     * @return
     */
    String lpop(String key);

    /**
     * 移除并获取列表最后一个元素
     * @param key
     * @return
     */
    String rpop(String key);


    /**
     * 在列表中添加一个或多个值
     * @param key
     * @param string
     * @return
     */
    Long rpush(String key, String... string);

    /**
     * 将一个或多个值插入到列表头部
     * @param key
     * @param string
     * @return
     */
    Long lpush(String key, String... string);

    /**
     * 获取列表指定范围内的元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lrange(String key, long start, long end);

    /**
     * Redis Ltrim 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。成功返回ok
     * @param key
     * @param start
     * @param end
     * @return
     */
    boolean ltrim(String key, long start, long end);

    /************ List end**************/



    /************ SET begin**************/

    /**
     * 向集合添加一个或多个成员
     * @param key
     * @param member
     * @return
     */
    boolean sadd(String key, String... member);

    /**
     * 返回集合中的所有成员，无序
     * @param key
     * @return
     */
    Set<String> smembers(String key);

    /**
     * 获取集合的成员数
     * @param key
     * @return
     */
    Long scard(String key);

    /**
     * 判断 member 元素是否是集合 key 的成员
     * @param key
     * @param member
     * @return
     */
    Boolean sismember(String key, String member);



    /************ SET end**************/


    /************ sorted set begin**************/

    /**
     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     * @param key
     * @param score
     * @param member
     * @return
     */
    Long zadd(String key, double score, String member);

    /************ sorted set end**************/




    /**
     * 以秒为单位，返回给定 key 的剩余生存时间。
     * 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 以毫秒为单位，返回给定 key 的剩余生存时间。
     * 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以毫秒为单位，返回 key 的剩余生存时间。
     * @param key
     * @return
     */
    Long pttl(String key);

    /**
     *
     *为给定 key 设置过期时间。
     * @param key
     * @param seconds
     * @return
     */
    Long expire(String key, int seconds);




}
