import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by reclaimer on 6/6/16.
 */
interface TaskManager {
    public void changeTaskName(int id, String newName);

    public void changeTaskDate(int id, Date newDate);

    public void deleteTask(int id);

    public void createTask(int id, String newTask);

    public void markComplete(int id);

    public ArrayList<Task> getAllTasks();

    public Task getTask(int id);
}
