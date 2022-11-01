
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ServiceTask {
    protected final static Map<Integer, Task> setOfTasks = new HashMap<>();

    public static Collection<Task> getTasksOnDate(LocalDate taskForDate) {
        Collection<Task> copy = setOfTasks.values();
        Collection<Task> tasksOnDate = new ArrayList<>();
        for (Task task : copy) {
            LocalDate cycleDate = task.getTimeCreateTask();
            while ((cycleDate.isBefore(taskForDate)
                    || (cycleDate.isEqual(taskForDate)))) {
                if (taskForDate.isEqual(cycleDate)) {
                    tasksOnDate.add(task);
                }
                task.getRepeatability().nextTime(cycleDate);
            }
        }
        return tasksOnDate;
    }

    public static void deleteTask(int choiceId) {
        Collection<Task> o = setOfTasks.values();
        for (Task task : o) {
            if (task.getId() == choiceId) {
                setOfTasks.remove(task.getId());
                System.out.println("Задача с id " + choiceId + " успешно удалена.");
            } else {
                System.out.println("Задача с id " + choiceId + " не найдена.");
            }
        }
    }
}
