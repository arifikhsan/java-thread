package org.example.model;

@SuppressWarnings("ALL")
public class SynchronizeCounter {
    private Long value = 0L;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    // synchronized satu fungsi
    // public synchronized void increment() {
    //     value++;
    // }

    public void increment() {
        // synchronized statement
        synchronized (this) {
            value++;
        }
    }
}
