package com.aurora.intreview;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) {
        weakHashMap();
    }

    private static void hashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();

        System.out.println(map);
    }



    private static void weakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "hashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();

        System.out.println(map);
    }
}
