package org.example.model;

@SuppressWarnings("ALL")
public class Balance {
    private Long value = 0L;

    public Balance(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public static void transfer(Balance from, Balance to, Long amount) throws InterruptedException {
        // deadlock
//        synchronized (from) {
//            Thread.sleep(1_000);
//            synchronized (to) {
//                Thread.sleep(1_000);
//                from.setValue(from.getValue() - amount);
//                to.setValue(to.getValue() + amount);
//            }
//        }

        // not deadlock
        synchronized (from) {
            Thread.sleep(1_000);
            from.setValue(from.getValue() - amount);
        }
        synchronized (to) {
            Thread.sleep(1_000);
            to.setValue(to.getValue() + amount);
        }
    }
}
