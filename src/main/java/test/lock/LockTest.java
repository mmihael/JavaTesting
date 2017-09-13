package test.lock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class LockTest {

    public int i = 0;

    private Set<String> locks = new HashSet<>();

    public ExecutorService executorService = Executors.newSingleThreadExecutor();

    public boolean lock(String name) {
        if (locks.contains(name)) { return false; }
        locks.add(name);
        return true;
    }

    public void increment() {
        executorService.submit(() -> { i += 1; });
    }

    public boolean unlock(String name) {
        locks.remove(name);
        return true;
    }

    public static void test() {

        LockTest lockTest = new LockTest();
        
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    //lockTest.i += 1;
                    lockTest.increment();
                }
            }));   
        }

        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (Exception e) {}
        });

        lockTest.executorService.shutdown();
        try { lockTest.executorService.awaitTermination(1, TimeUnit.MINUTES); } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(lockTest.i);
        
    }

}
