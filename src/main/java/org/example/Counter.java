package org.example;

public class Counter {
    private Long value = 0L;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public void increment() {
        value++;
    }
}
