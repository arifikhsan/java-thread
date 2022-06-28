package org.example.model;

public class SynchronizeCounter {
    private Long value = 0L;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public synchronized void increment() {
        value++;
    }
}
