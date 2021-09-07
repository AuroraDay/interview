package com.aurora.intreview;

import java.nio.ByteBuffer;

public class DirectMemoryDemo {
    public static void main(String[] args) {
        System.out.println(sun.misc.VM.maxDirectMemory());
        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
