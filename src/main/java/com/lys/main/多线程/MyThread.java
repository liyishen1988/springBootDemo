package com.lys.main.多线程;

/**
 * 采用实现Runnable、Callable接口的方式创建多线程的优缺点：

 1、线程类只是实现了Runnable接口或Callable接口,还可以继承其他类。

 2、多个线程可以共享同一个target对象,非常适合多个相同线程来处理同一份资源的情况,较好的体现了面向对象的思想。

 3、需要访问当前线程,则必须使用Thread.currentThread()方法。

 采用继承Thread类的方式创建多线程的优缺点：

 1、因为该线程已经继承了Thread类,所以不能在继承其他父类。

 2、编写简单,如果需要访问当前线程,则无需使用Thread.currentThread()方法,直接使用this即可获得当前线程。
 */
public class MyThread implements Runnable {


    @Override
    public void run() {

    }
}
