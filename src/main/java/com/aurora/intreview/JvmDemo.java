package com.aurora.intreview;

public class JvmDemo {
    public static void main(String[] args) throws InterruptedException {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TotalMemory:" + totalMemory);
        System.out.println("MaxMemory:" + maxMemory);
    }
}
