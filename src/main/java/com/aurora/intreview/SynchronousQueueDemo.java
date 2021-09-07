package com.aurora.intreview;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列:
 *  2.1 阻塞队列有没有好的一面
 *  2.2 不得不阻塞, 你如何管理
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {

        BlockingQueue<String> blockingDeque = new SynchronousQueue<>();

        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "put 1");
                blockingDeque.put("1");
                System.out.println(Thread.currentThread().getName() + "\t" + "put 2");
                blockingDeque.put("2");
                System.out.println(Thread.currentThread().getName() + "\t" + "put 3");
                blockingDeque.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();


        new Thread(() ->{
            try {
                System.out.println(blockingDeque.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(blockingDeque.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(blockingDeque.take());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();

    }
}
