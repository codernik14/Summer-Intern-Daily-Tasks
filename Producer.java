import java.util.Queue;

public class Producer implements Runnable{
    private Thread thread;
    protected Queue <Runnable> taskBlockingQueue;
    public int producerNum;
    public Producer(Queue<Runnable> q,int num){
        taskBlockingQueue = q;
        producerNum = num;
    }

    @Override
    public void run() {
//        thread = Thread.currentThread();
        int numTask = (int) (Math.random() * 5) + 1;
//        System.out.println("Number of task by producer-" + producerNum + " are " + numTask);
        for(int i=0; i<numTask; i++) {
            synchronized(taskBlockingQueue){
                int taskNo = i;
                taskBlockingQueue.add(() -> {
                    String message = "Task-" + taskNo + " by Producer " + this.producerNum;
                    System.out.println(message);
                });
//              System.out.println("Producer " + taskBlockingQueue.size());
              taskBlockingQueue.peek().run();
            }
            try {
                wait(2000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
