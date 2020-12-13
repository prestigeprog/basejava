package com.urise.webapp;

public class MainDeadlock {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() ->
                testDeadLock(lock1, lock2)
        ).start();
        new Thread(() ->
                testDeadLock(lock2, lock1)
        ).start();

    }

    private static void testDeadLock(Object lock1, Object lock2) {
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + ": Holding lock" + lock2.toString() + "..");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            System.out.println(Thread.currentThread().getName() + ": Waiting for lock " + lock1.toString() + "...");

            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + ": Holding lock " + lock1.toString() + "...");
            }
        }
    }
}
