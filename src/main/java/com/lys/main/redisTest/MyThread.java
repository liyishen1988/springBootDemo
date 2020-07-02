package com.lys.main.redisTest;

/**
 * Created by Administrator on 2020/6/24.
 */
public class MyThread extends Thread{

    MyRedis service = new MyRedis();

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.run();
    }

    @Override
    public void run(){
        while (true){
            service.limitCall("用户A");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
