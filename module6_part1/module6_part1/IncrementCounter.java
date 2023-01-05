package module6_part1;

public class IncrementCounter {
    private int counter=0;

    private void increment() {
        for(int i=0;i<10000;i++) {
            counter++;
        }
    }

    public void test() throws InterruptedException {
        Thread t1=new Thread(()->increment());
        Thread t2=new Thread(()->increment());
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(counter);
    }

    public static void main(String[] args) throws InterruptedException {
        new IncrementCounter().test();
    }
}
