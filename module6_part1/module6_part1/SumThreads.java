package module6_part1;

import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

class Summer implements Runnable {
    final int a,b;
    int sum;

    public Summer(int a, int b) { this.a = a; this.b = b; }

    public void run() {
        for(int i=a;i<=b;i++) {
            sum += i;
        }
    }
}

public class SumThreads {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Summer s1 = new Summer(1,500);
        Summer s2 = new Summer(501,1000);
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);

        t1.start(); t2.start();
        t1.join(); t2.join();
        int sum = s1.sum + s2.sum;
        System.out.println(sum);
    }
}
