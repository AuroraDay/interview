package com.aurora.intreview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
    }
}
