import java.util.LinkedList;
import java.util.Queue;

public class QueueManager {
    private Queue<Runnable> scheduledTasks;

    //implementing a LinkedList that will be used to manage a queue of tasks.
    public QueueManager() {
        this.scheduledTasks = new LinkedList<>();
    }

    public void addTask(Runnable task) {
        scheduledTasks.add(task);
    }

    public void processTasks() {
        while (!scheduledTasks.isEmpty()) {
            Runnable task = scheduledTasks.poll();
            if (task != null) {
                task.run();
            }
        }
    }
}
