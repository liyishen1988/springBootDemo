package com.lys.baseJava.redisTest;

import com.alibaba.fastjson.JSON;
import com.lys.demo.entity.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * java中使用Redis + 请求调用次数的限制案例代码模拟
 */
public class MyRedis {

    private Integer num=0;

    // 请求模拟
    void call() {
        num+=1;
        System.out.println("调用服务:"+num);
    }

    // 请求调用次数的限制案例代码模拟
    void limitCall(String name) {
        Jedis jedis = JedisUtil.getJedis();
//        Jedis jedis2 = new Jedis("127.0.0.1",6379);
        String value = jedis.get("user" + name);
        //第一步，查看该值是否存在
        try {
            //如果不存在，创建值，设置生命周期为20s
            if (value == null) {
                jedis.setex("user" + name, 20, Long.MAX_VALUE - 10 + "");
            } else {
                //存在则+1，直到超过最大值（Long.MAX_VALUE）时抛出异常
                jedis.incr("user" + name);
                call();
            }
        } catch (JedisDataException e) {
            //超过最大值，即每20s访问超过10次，则执行异常
            num+=1;
            System.out.println("超过最大值Long.MAX_VALUE，即达到请求上限，稍后再试:"+num);
        } finally {
            jedis.close();
        }
    }

    // java中使用Redis
    public static void main(String[] args) throws InterruptedException {
        //连接本地的Redis服务
        Jedis jedis = new Jedis("127.0.0.1", 6379);  //默认端口6379 可以省略
//        jedis.auth("redis");  //无密码此步可省略
        System.out.printf("连接成功：" + jedis.ping());

        //Redis命令
        jedis.flushDB();    //清空库中所有的数据
        jedis.exists("haha");   //判断键是否存在
        jedis.del("haha");  //删除指定key
        jedis.set("haha", "苹果");    //往指定key里塞值
        jedis.set("key001", "value001");
        jedis.set("key002", "value002");
        jedis.set("key003", "value003");
        jedis.append("key003", "APPLE!");


        //系统中所有的键
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.printf("key");
        }

        //设置指定键的过期时间
        jedis.expire("haha", 10);
        Thread.sleep(2000);
        jedis.ttl("haha");  //查看指定键的剩余生存时间
        jedis.persist("haha"); //移除指定键的生存时间

        jedis.type("haha"); //查看指定键的值类型

        //对象塞入redis
        User user1 = new User("小飞侠", 20, 0);
        User user2 = new User("小云", 28, 0);
        User user3 = new User("小雪", 28, 0);
        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("u1", JSON.toJSONString(user1));
        jedis.sadd("20", "u1");
        userMap.put("u2", JSON.toJSONString(user2));
        jedis.sadd("28", "u2");
        userMap.put("u3", JSON.toJSONString(user3));
        jedis.sadd("28", "u3");
        jedis.hmset("user", userMap);


        Set<String> userList = jedis.smembers("28");
        for (String u : userList) {
            System.out.println(jedis.hget("user", u));
        }

        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
            }
        });


        //3.关闭连接
        jedis.close();

    }
}
