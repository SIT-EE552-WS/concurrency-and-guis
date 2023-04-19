package edu.stevens.friccobo;
import java.util.concurrent.atomic.AtomicInteger;
public class Main {

    public static void printWithThread(String message){
        String name = Thread.currentThread().getName();
        System.out.println("[" + name + "] " + message);
    }
    static int count = 0;
    static AtomicInteger atomicCount = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        for(int i = 0; i < 10; i++){
            incrementCount();
        }

        printWithThread(""+ count);

        // Thread
        Thread thread = new MyThread();
        Thread thread2 = new MyThread2();
        thread.start();
        thread2.start();

        printWithThread(""+ count);
//        thread.join();
        thread2.join();
        Thread.sleep(7000);
        thread.interrupt();


        printWithThread(""+ count);
        printWithThread(""+ atomicCount.get());
    }

    private static synchronized int incrementCount() {
        // [Thread 1] get the current value of count
        // [Thread 1] add one to it
        // [Thread 2] get the current value of count
        // [Thread 2] add one to it
        // [Thread 2] store the result back in the variable count
        // [Thread 1] store the result back in the variable count
        atomicCount.incrementAndGet();
        return count++;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    printWithThread("I was interrupted");
                    Thread.currentThread().interrupt();
                    return;
                }
//                printWithThread("*");
                incrementCount();
            }
            printWithThread(""+count);
        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < 1000; i++) {
//                try {
////                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                printWithThread("o");
                incrementCount();
            }
            printWithThread(""+count);
        }
    }
}
