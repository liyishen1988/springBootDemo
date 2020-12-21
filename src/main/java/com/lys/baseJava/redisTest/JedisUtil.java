package com.lys.baseJava.redisTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * 搭建一个Jedis工具类
 */
public class JedisUtil {

    private static JedisPool jedisPool=null;

    static {
        //通过配置文件修改参数
        ResourceBundle rb=ResourceBundle.getBundle("jedis");
        String host = rb.getString("redis.host");
        int port = Integer.parseInt(rb.getString("redis.port"));
        int maxidle = Integer.parseInt(rb.getString("redis.maxidle"));
        int maxtotal= Integer.parseInt(rb.getString("redis.maxtotal"));


        //配置线程池
        JedisPoolConfig config=new JedisPoolConfig();
        //设置最大空闲等待数
        config.setMaxIdle(maxidle);
        //设置最大连接数
        config.setMaxTotal(maxtotal);
        jedisPool=new JedisPool(config,host,port);
    }

    //通过该方法获取jedis对象
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }


}
