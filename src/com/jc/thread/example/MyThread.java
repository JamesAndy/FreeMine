package com.jc.thread.example;

/**
 * File Description/檔案描述:
 * @author JamesChang
 * @since 2020/6/9下午 03:21
 * @version 1.0
 **/
public class MyThread extends Thread{

    private String x;
    private static int threadCount = 0;

    public MyThread(String x){
        // turn to string
        this.x = String.valueOf(x);
    }

    public void run(){
        for (int i = 0; i < 1000; i++)
        {
            threadCount++;
        }
        System.out.println("I'm "+x+" = "+ threadCount);
    }
}
