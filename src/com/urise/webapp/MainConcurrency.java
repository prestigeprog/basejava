package com.urise.webapp;

public class MainConcurrency {
//    public static final int THREADS_NUMBER = 10000;
//    private static volatile int counter;
//    private static final Object LOCK = new Object();

    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo1 T1 = new ThreadDemo1();
        ThreadDemo2 T2 = new ThreadDemo2();
        T1.start();
        T2.start();

//        System.out.println(Thread.currentThread().getName());
//        Thread thread0 = new Thread() {
//            @Override
//            public void run() {
//                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
//            }
//        };
//        thread0.start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
//            }
//
//            private void inc() {
//                synchronized (this) {
//                    counter++;
//                }
//            }
//        }).start();
//
//        System.out.println(thread0.getState());
//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
//        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//            });
//            thread.start();
//            threads.add(thread);
//        }
//
//        threads.forEach(t-> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(counter);
//        LazySingleton.getInstance();
//    }
//
//    private synchronized void inc() {
//        //synchronized (MainConcurrency.class){
//        //synchronized (this) {
//            counter++;
//            //wait();
//            //readFile
//            //...
//        //}
//    }
//    public synchronized static void deadlock() {
//        try {
//            Thread t = new Thread(Test::deadlock);
//            t.start();
//            t.join();
//        } catch (Exception ex) {}
    }

    private static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }
    private static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding lock 2...");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (Lock1) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }
}
