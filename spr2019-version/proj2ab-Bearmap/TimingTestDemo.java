package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

/**
 * Created by hug. Demonstrates how you can use either
 * System.currentTimeMillis or the Princeton Stopwatch
 * class to time code.
 */
public class TimingTestDemo {
    public static void main(String[] args) {
        /**
         *

        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 100000; i += 1) {
            for (int j = 0; j < 10000; j += 1) {
                sum = sum + i + j;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

         */
        //time test
        Random random = new Random(2312412);
        ArrayHeapMinPQ<Integer> hp = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        for (int i = 1000000; i > 0 ; i -= 1) {
            double priority = random.nextDouble();
            hp.add(i, priority);
            naiveMinPQ.add(i, priority);
        }
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 1000000; i += 1) {
            hp.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");

        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i < 10000; i += 1) {
            naiveMinPQ.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw1.elapsedTime() +  " seconds.");
    }
}
