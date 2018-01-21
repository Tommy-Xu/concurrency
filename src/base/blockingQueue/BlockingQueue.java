package base.blockingQueue;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {
    private List<T> queue = new LinkedList<>();
    private int limit = 10;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == limit){
            wait();
        }
        if(queue.size() == 0){
            notifyAll();
        }
        queue.add(item);
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.size() == 0){
            wait();
        }
        if(queue.size() == limit){
            notifyAll();
        }

        return queue.remove(0);
    }
}
