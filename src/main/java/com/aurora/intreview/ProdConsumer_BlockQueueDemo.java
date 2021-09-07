package com.aurora.intreview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {

    private volatile boolean FLAG = true;

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.getAndIncrement() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "老板叫停, 生产动作结束");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "超出两秒没有取到数据");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "消费成功" + result);
        }

    }

    public void stop() {
        this.FLAG = false;
    }
}


public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "生产者启动");
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "prod").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "消费者启动");
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        TimeUnit.SECONDS.sleep(5);

        System.out.println("时间超过5S, 大老板叫停");
        myResource.stop();
    }
}
