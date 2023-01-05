package module6_part1;

public class CreateThreadSimple {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("This is "+Thread.currentThread().getName());
        }, "My new thread");
        t.start();

        System.out.println("This is "+Thread.currentThread().getName());
    }
}
