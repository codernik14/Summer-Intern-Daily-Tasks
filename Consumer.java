import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer implements Runnable{
    private Thread thread;
    protected Queue<Runnable> taskBlockingQueue;
    private boolean aliveStatus = true;
    public Consumer(Queue<Runnable> q){
        taskBlockingQueue = q;
    }
    @Override
    public void run(){
//        this.thread = Thread.currentThread();
//        System.out.println("Hello " + this.thread.getName());
//        System.out.println("Alive Status "  + aliveStatus);
        while(aliveStatus){
//            System.out.println("Alive Status 2 "  + aliveStatus);
            synchronized (taskBlockingQueue) {
                try {
                    Runnable runnable = (Runnable) taskBlockingQueue.remove();
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void doStop(){
        aliveStatus = false;
//        this.thread.interrupt();
    }
}
