package base.synchronizedBlock;

public class Main {
    private static int count = 0;

    /**
     *
     * @param value
     * @implSpec synchronized instance methods: One thread per instance
     */
    public synchronized void addA(int value){
        count += value;
    }

    /**
     *
     * @param value
     * @implSpec synchronized static methods: One thread per class
     */
    public static synchronized void addB(int value){
        count += value;
    }

    /**
     *
     * @param value
     * @implSpec synchronized blocks in instance method
     */
    public void addC(int value){
        synchronized (this){
            count += value;
        }
    }

    /**
     *
     * @param value
     * @implSpec synchronized blocks in static method
     */
    public static void addD(int value){
        synchronized (Main.class){
            count += value;
        }
    }
}
