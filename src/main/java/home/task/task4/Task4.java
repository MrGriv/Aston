package home.task.task4;

public class Task4 {
    private static volatile boolean print = true;

    public static void main(String[] args) {
        Object lock = new Object();

        Runnable print1 = () -> {
            while(true){
                synchronized (lock) {
                    while (!print) {
                        try {
                            lock.wait();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException();
                        }
                    }

                    print = false;
                    lock.notifyAll();

                    System.out.println(1);
                }
            }
        };

        Runnable print2 = () -> {
            while(true){
                synchronized (lock) {
                    while (print) {
                        try {
                            lock.wait();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException();
                        }
                    }

                    print = true;
                    lock.notifyAll();

                    System.out.println(2);
                }
            }
        };

        Thread thread1 = new Thread(print1);
        Thread thread2 = new Thread(print2);

        thread1.start();
        thread2.start();
    }
}