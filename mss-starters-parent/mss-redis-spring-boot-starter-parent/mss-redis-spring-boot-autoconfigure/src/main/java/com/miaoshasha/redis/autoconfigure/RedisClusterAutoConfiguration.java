package com.miaoshasha.redis.autoconfigure;

import com.alibaba.fastjson.parser.ParserConfig;
import com.miaoshasha.common.utils.FastJsonRedisSerializer;
import com.miaoshasha.redis.client.JedisClusterClient;
import com.miaoshasha.redis.lock.DistributedLock;
import com.miaoshasha.redis.lock.RedisDistributedLock;
import com.miaoshasha.redis.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-27
 * Time：14:41
 * ================================
 */

@Configuration
@EnableConfigurationProperties(RedisClusterProperties.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisClusterAutoConfiguration {

    @Autowired
    private RedisClusterProperties redisClusterProperties;

    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodes = readHostAndPort(redisClusterProperties.getCluster().getNodes());
        int connectionTimeout = redisClusterProperties.getTimeout();
        int maxRedirects = redisClusterProperties.getCluster().getMaxRedirects();
        int soTimeout = redisClusterProperties.getSoTimeout();
        // 节点，超时时间，最多重定向次数，链接池
        return new JedisCluster(nodes, connectionTimeout, soTimeout, maxRedirects, jedisPoolConfig());
    }

    /**
     * 配置JedisPoolConfig
     *
     * @return
     */
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        RedisClusterProperties.Pool pool = this.redisClusterProperties.getPool() != null ?
                redisClusterProperties.getPool() : new RedisClusterProperties.Pool();
        config.setMaxTotal(pool.getMaxActive());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        config.setMaxWaitMillis(pool.getMaxWait());
        return config;
    }


    /**
     * 获取Redis集群HOST:PORT
     *
     * @return
     */
    public Set<HostAndPort> readHostAndPort(List<String> nodes) {
        Set<HostAndPort> nodeSet = new HashSet<>();
        if (nodes != null) {
            for (String ipPort : nodes) {
                String[] ipPortPair = ipPort.split(":");
                nodeSet.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
            }
        }
        return nodeSet;
    }


    @Bean
    @ConditionalOnBean(JedisCluster.class)
    public JedisClusterClient jedisClusterClient(JedisCluster jedisCluster) {
        return new JedisClusterClient(jedisCluster);
    }


    @Bean
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        // 全局开启AutoType，不建议使用
        // ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单
        ParserConfig.getGlobalInstance().addAccept("com.miaoshasha.");
        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public DistributedLock redisDistributedLock(RedisTemplate<Object, Object> redisTemplate){
        return new RedisDistributedLock(redisTemplate);
    }

    /**
     * 实例化redis工具
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisCache redisCache(StringRedisTemplate redisTemplate){
        return new RedisCache(redisTemplate);
    }

}
