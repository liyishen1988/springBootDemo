package com.lys.main.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2020/5/19.
 */
public class ThirdThread {

    public static void main(String[] args) {
        //创建callable对象
        ThirdThread thirdThread = new ThirdThread();
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int i=0;
                for (; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName());
                }
                return i;
            }
        });


    }
}
