package home.task.task4;

public class DeadLock {
    private static final Object resource1 = "resource1";
    private static final Object resource2 = "resource2";

    public static void main(String[] args){

        Thread firstThread = new Thread(() -> {
            synchronized(resource1){
                System.out.println("Thread 1: locked resource 1");

                try{
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }

                synchronized(resource2){
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        }, "thread1");

        Thread secondThread = new Thread(() -> {
            synchronized(resource2){
                System.out.println("Thread 2: locked resource 2");

                try{
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }

                synchronized(resource1){
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        }, "thread2");

        firstThread.start();
        secondThread.start();
    }
}