package base.lock;

public class Counter {
    private Lock lock = new Lock();
    private int count = 0;

    public int inc(){
        int newCount = 0;
        try {
            lock.lock();
            newCount = ++count;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unLock();
        }
        return newCount;
    }
}
