package com.lys.baseJava.thread;

/**
 * Created by Administrator on 2020/5/19.
 */
public class SecondThread implements Runnable{


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            SecondThread secondThread = new SecondThread();
            new Thread(secondThread,"新线程1").start();
            new Thread(secondThread,"新线程2").start();
        }
    }
}
