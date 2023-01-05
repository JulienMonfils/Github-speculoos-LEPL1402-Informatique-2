package module6_part1;

class MyThread implements Runnable {
    private String text;

    public MyThread(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        System.out.println(text);
    }
}

public class CreateThreadWithClass {
    public static void main(String[] args) {
        Thread t1=new Thread(new MyThread("Hello"));
        t1.start();

        Thread t2=new Thread(new MyThread("World"));
        t2.start();

        System.out.println("This is "+Thread.currentThread().getName());
    }
}
