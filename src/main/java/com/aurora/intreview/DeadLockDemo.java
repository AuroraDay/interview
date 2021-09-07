package com.aurora.intreview;

import java.util.concurrent.TimeUnit;

class HoldLock implements Runnable {

    private String lockA;

    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t" + lockA + "尝试获得B:" +  lockB);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t" + lockB + "尝试获得A:" +  lockA);
            }
        }
    }
}


public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA, lockB), "AAA").start();
        new Thread(new HoldLock(lockB, lockA), "BBB").start();
    }
}
