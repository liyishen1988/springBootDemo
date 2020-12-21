package com.lys.baseJava.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 测试布隆过滤器(可用于redis缓存穿透)
 */
public class MyBloomFilter {

    private static int total = 1000000;

    private static BloomFilter<Integer> bf = BloomFilter.create(Funnels.integerFunnel(), total);

    public static void main(String[] args) {
        // 初始化1000000条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put(i);
        }

        // 匹配已在过滤器中的值，是否有匹配不上的
        for (int i = 0; i < total; i++) {
            if (!bf.mightContain(i)) {
                System.out.println("有坏人逃脱了~~~");
            }
        }

        // 匹配不在过滤器中的10000个值，有多少匹配出来
        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if (bf.mightContain(i)) {
                count++;
            }
        }
        //运行结果表示，遍历这一百万个在过滤器中的数时，都被识别出来了。一万个不在过滤器中的数，误伤了320个，错误率是0.03左右。
        System.out.println("误伤的数量：" + count);

    }
}
