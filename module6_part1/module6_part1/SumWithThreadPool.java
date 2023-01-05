package module6_part1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumWithThreadPool {
    public static int calculate(int a, int b) {
        int sum=0;
        for(int i=a; i<=b; i++) {
            sum+=i;
        }
        return sum;
    }
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> f1 = executor.submit(()-> calculate(1,500));
        Future<Integer> f2 = executor.submit(()-> calculate(501,1000));
        int sum = f1.get() + f2.get();

        System.out.println(sum);

        executor.shutdown();
    }
}
