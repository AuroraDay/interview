package com.aurora.intreview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统生产者和消费者
 */
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number ++ ;
            System.out.println(Thread.currentThread().getName()+ "\t" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void deIncrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number --;
            System.out.println(Thread.currentThread().getName()+ "\t" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


public class ProducerConsumerDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                shareData.increment();
            }, "AAA").start();

        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                shareData.deIncrement();
            }, "BBB").start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                shareData.deIncrement();
            }, "CCC").start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                shareData.increment();
            }, "DDD").start();
        }
    }
}
