import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPool {
    public static Queue<Runnable> taskBlockingQueue = new LinkedList<>();
    public List<Consumer> consumerList = new ArrayList<>();
    public List<Producer> producerList = new ArrayList<>();

    public ThreadPool(int numProducers,int numConsumers){
        for(int i = 0;i< numProducers;i++){
            Producer producer = new Producer(taskBlockingQueue,i);
            producerList.add(producer);
            new Thread(producer).start();
        }
//        System.out.println("Thread Pool Producer Creation " + producerList.size());
        for(int i=0;i<numConsumers;i++){
            Consumer consumer = new Consumer(taskBlockingQueue);
            consumerList.add(consumer);
            new Thread(consumer).start();
        }
//        System.out.println("Thread Pool Consumer Creation " + consumerList.size());
    }

    public synchronized void end(){
        for(Consumer consumer: consumerList) {
            consumer.doStop();
        }
    }

    public synchronized void waitForEnd() {
        while(this.taskBlockingQueue.size() > 0) {
            System.out.println(taskBlockingQueue.size());
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
