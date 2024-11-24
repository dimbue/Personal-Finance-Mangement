import java.util.LinkedList;
import java.util.Queue;


//This class is currently in limbo as I don't know if I will still utilize it in the final
public class QueueManager {
    private Queue<Runnable> scheduledTasks;


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
