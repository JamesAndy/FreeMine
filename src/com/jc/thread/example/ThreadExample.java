package com.jc.thread.example;

import java.awt.*;

/**
 * File Description/檔案描述:
 * @author JamesChang
 * @since 2020/6/9下午 03:22
 * @version 1.0
 **/
public class ThreadExample {

    static int threadCount = 0;

    public static void main(String[] args){
        String[] fileList = {"a","b","c","d","e","f"};
        int i = 0;
        while (i< fileList.length){
            Thread t1 = new MyThread(fileList[i]);
            t1.start();
            i++;
        }

        for (i = 0; i < 1000; i++)
        {
            threadCount++;
        }

        System.out.println("Hello World in main Thread = "+threadCount);
    }
}
