import java.util.ArrayList;

public class ThreadManager {
    private ArrayList<Thread> activeThreads;

    public ThreadManager() {
        this.activeThreads = new ArrayList<>();
    }

    public void runConcurrentCalculations(Runnable task) {
        Thread thread = new Thread(task);
        activeThreads.add(thread);
        thread.start();
    }
}
