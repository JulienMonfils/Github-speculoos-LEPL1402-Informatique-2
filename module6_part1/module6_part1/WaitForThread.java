package module6_part1;

public class WaitForThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5000);
            }
            catch(InterruptedException e) {
                throw new RuntimeException("Unexpected interrupt", e);
            }
            System.out.println("New thread has finished its work");
        }, "NewThread");
        t.start();

        System.out.println("This is the main thread");


        try {
            t.join(10000); // wait 10 seconds maximum
            if(t.isAlive()) {
                // the other thread is still running (or not yet started)
            }
        }
        catch(InterruptedException e) {
            throw new RuntimeException("Unexpected interrupt", e);
        }

        System.out.println("All threads have finished");
    }
}
