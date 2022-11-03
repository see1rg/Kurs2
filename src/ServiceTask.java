
import java.time.LocalDate;
import java.util.*;

public class ServiceTask {

    private final static Map<Integer, Task> SET_OF_TASKS = new HashMap<>();
    public static void adSetOfTasks(Task task) {
        ServiceTask.SET_OF_TASKS.put(task.getId(), task);}

    public static Collection<Task> getTasksOnDate(LocalDate taskForDate) {
        Collection<Task> copy = SET_OF_TASKS.values();
        Collection<Task> tasksOnDate = new ArrayList<>();
        for (Task task : copy) {
            LocalDate cycleDate = task.getTimeCreateTask();
            while ((cycleDate.isBefore(taskForDate)
                    || (cycleDate.isEqual(taskForDate)))) {
                if (taskForDate.isEqual(cycleDate)) {
                    tasksOnDate.add(task);
                }
                cycleDate = task.getRepeatability().nextTime(cycleDate);
            }
        }
        return tasksOnDate;
    }

    public static void deleteTask(int choiceId) throws IllegalAccessException {
       if (!SET_OF_TASKS.containsKey(choiceId)){
             throw new IllegalAccessException("Задача с id " +
                     choiceId + " не найдена.");
       } else {SET_OF_TASKS.remove(choiceId);
           System.out.println("Задача с id " + choiceId + " успешно удалена.");
       }
    }
}
