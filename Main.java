public class Main {
    public static void main(String args[]) throws Exception{
        int numProducers = Integer.parseInt(args[0]);
        int numConsumers = Integer.parseInt(args[1]);

        ThreadPool threadPool = new ThreadPool(numProducers,numConsumers);
        for(Producer producer: threadPool.producerList)
        {
            System.out.println(producer.toString() + " " +  producer.producerNum);
        }
//        try{
//            this.sleep(10000);
//        }
//        catch(InterruptedException e){
//            e.printStackTrace();
//        }
//        for(Runnable task: ThreadPool.taskBlockingQueue)
//        {
//            task.run();
//        }
//        System.out.println("From Main " + ThreadPool.taskBlockingQueue.size());
//        Thread.sleep(1000);
        threadPool.waitForEnd();
//        System.out.println("From Main " + ThreadPool.taskBlockingQueue.size());
//        System.out.print("Here before End");
        threadPool.end();
    }
}
