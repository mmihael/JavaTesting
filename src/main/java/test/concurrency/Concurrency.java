package test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmamula on 20.12.2016.
 */
public class Concurrency {

    public static void thread() {
        Thread thread = new Thread(new Runner());
        thread.start();
    }

    public static void workQueue() {
        ExecutorService e = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        e.submit(new Runner());
        e.submit(new Runner());
        e.submit(new Runner());

        try {
            e.shutdown();
            e.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println(e.isTerminated());
    }

}

class Runner implements Runnable {

    public void run() {
        try {
            System.out.println("Before");
            Thread.sleep(1000);
            System.out.println("After");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
