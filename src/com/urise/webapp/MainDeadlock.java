package com.urise.webapp;

public class MainDeadlock {

    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() ->
                testDeadLock(Lock1, Lock2)
                );
        t1.start();
        Thread t2 = new Thread(() ->
                testDeadLock(Lock2, Lock1)
        );
        t2.start();
    }

    private static void testDeadLock(Object lock1, Object lock2){
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + ": Holding lock ..");

            try { Thread.sleep(10); }
            catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + ": Waiting for lock ...");

            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + ": Holding lock...");
            }
        }
    }
}
