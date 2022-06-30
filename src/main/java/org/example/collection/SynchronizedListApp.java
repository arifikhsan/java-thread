package org.example.collection;

import java.util.Collections;
import java.util.List;

public class SynchronizedListApp {
    public static void main(String[] args) {
        var list = List.of("Arif", "Babu", "Chandu");
        var syncList = Collections.synchronizedList(list);
        System.out.println(syncList);
    }
}
