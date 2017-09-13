package test.memorytest;

import lombok.AllArgsConstructor;
import test.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmamula on 13.3.2017.
 */
public class MemoryTest {

    @AllArgsConstructor
    public static class T {
        int a;
        int b;
        int c;
    }

    public static void test() {

        int mb = 1024*1024;

        Runtime runtime = Runtime.getRuntime();

        //    Map<Integer, Integer[]> object = new HashMap<>();
//    Map<Integer, int[]> object = new HashMap<>();
        List<T> l = new ArrayList<>();

        for (int i = 0; i < 500000; i++) {
            l.add(new T(new Integer(1),new Integer(2),new Integer(3)));


//      Integer[] list = new Integer[100000];
//      for (int j = 0; j < 100000; j++) { list[j] = j; }
//      object.put(i, list);
//      int[] list = new int[100000];
//      for (int j = 0; j < 100000; j++) { list[j] = j; }
//      object.put(i, list);

//      Integer[] arr = new Integer[10];
//      object.put(i, new Integer[]{1,2,3,4,5,6,7,8,9,0});
//      object.put(i, new int[]{1,2,3,4,5,6,7,8,9,0});
        }

        System.out.println("Used memory");
        System.out.println((runtime.totalMemory() - runtime.freeMemory()) / mb);

    }


}
