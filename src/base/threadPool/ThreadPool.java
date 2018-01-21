package base.threadPool;

import base.blockingQueue.BlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<PoolThread> threads = new ArrayList<>();
    private boolean isStopped = false;

    private ThreadPool(int numOfThreads, int maxNumOfTasks){
        taskQueue = new BlockingQueue<>(maxNumOfTasks);
        for(int i=0; i< maxNumOfTasks; i++){
            threads.add(new PoolThread(taskQueue));
        }
    }

    public ThreadPool init(int numOfThreads, int maxNumOfTasks){
        ThreadPool threadPool = new ThreadPool(numOfThreads, maxNumOfTasks);
        for (PoolThread thread : threads){
            thread.start();
        }
        return threadPool;
    }

    public synchronized void execute(Runnable task) throws InterruptedException {
        if(this.isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        isStopped = true;
        for(PoolThread thread:threads){
            thread.doStop();
        }
    }
}
