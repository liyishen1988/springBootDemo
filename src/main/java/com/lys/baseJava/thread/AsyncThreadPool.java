package com.lys.baseJava.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 */
public class AsyncThreadPool {

    private static AsyncThreadPool asyncThreadPool = null;
    private static ExecutorService threadPool= null;

    private AsyncThreadPool(){
        int process=10;
        threadPool = Executors.newFixedThreadPool(process);
    }

    public final static AsyncThreadPool getInstance(){
        if(asyncThreadPool==null){
            asyncThreadPool=new AsyncThreadPool();
        }
        return asyncThreadPool;
    }

    public void execute(Runnable thread){
        threadPool.execute(thread);
    }

    public void submit(Runnable thread){
        threadPool.submit(thread);
    }
}
