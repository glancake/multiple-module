package com.gl.util;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;

public class JedisPoolSingleton {

    // 私有静态变量，用于保存唯一实例
    private static volatile JedisPool jedisPool;


    // 私有构造函数，防止外部实例化
    private JedisPoolSingleton() {
    }

    // 获取 JedisPool 实例的方法
    public static JedisPool getInstance() {
        Environment env = SpringUtil.getBean(Environment.class);

        // 获取默认的 Redis 配置
        String host = env.getProperty("spring.data.redis.host");
        int port = Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.data.redis.port")));
        return getInstance(host, port);

//        return null;
    }

    // 获取 JedisPool 实例的方法
    public static JedisPool getInstance(String host, int port) {
        if (jedisPool == null) {
            synchronized (JedisPoolSingleton.class) {
                if (jedisPool == null) {
                    // 初始化 JedisPoolConfig
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(50);        // 最大连接数
                    poolConfig.setMaxIdle(10);         // 最大空闲连接数
                    poolConfig.setMinIdle(2);          // 最小空闲连接数
                    poolConfig.setMaxWaitMillis(3000); // 获取连接的最大等待时间
                    poolConfig.setTestOnBorrow(true);  // 获取连接时测试是否可用
                    poolConfig.setTestOnReturn(true);  // 归还连接时测试是否可用

                    // 初始化 JedisPool
                    jedisPool = new JedisPool(poolConfig, host, port);
                }
            }
        }
        return jedisPool;
    }

    // 重载方法，支持带密码的 Redis
    public static JedisPool getInstance(String host, int port, String password) {
        if (jedisPool == null) {
            synchronized (JedisPoolSingleton.class) {
                if (jedisPool == null) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(50);
                    poolConfig.setMaxIdle(10);
                    poolConfig.setMinIdle(2);
                    poolConfig.setMaxWaitMillis(3000);
                    poolConfig.setTestOnBorrow(true);
                    poolConfig.setTestOnReturn(true);

                    // 初始化 JedisPool（带密码）
                    jedisPool = new JedisPool(poolConfig, host, port, 2000, password);
                }
            }
        }
        return jedisPool;
    }
}
