package base.threadPool;

import base.blockingQueue.BlockingQueue;

public class PoolThread extends Thread{
    private BlockingQueue<Runnable> taskQueue = null;
    private boolean isStoppend = false;

    public PoolThread(BlockingQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (! isStoppend()){
            try {
                Runnable runnable = taskQueue.dequeue();
                runnable.run();
            } catch (Exception e){
                //log or otherwise report exception
                //but keep pool thread alive
            }
        }
    }

    public synchronized void doStop(){
        isStoppend = true;
        this.interrupt();//break pool thread out of dequeue
    }

    public boolean isStoppend() {
        return isStoppend;
    }
}
