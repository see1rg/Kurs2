
import java.time.LocalDate;
import java.util.*;

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
                if (PeriodTask.DAILY.equals(task.getRepeatability())) {
                    cycleDate = new Daily().nextTime(cycleDate);
                } else if (PeriodTask.WEEKLY.equals(task.getRepeatability())) {
                    cycleDate = new Weekly().nextTime(cycleDate);
                } else if (PeriodTask.MONTHLY.equals(task.getRepeatability())) {
                    cycleDate = new Monthly().nextTime(cycleDate);
                } else if (PeriodTask.ANNUAL.equals(task.getRepeatability())) {
                    cycleDate = new Annual().nextTime(cycleDate);
                } else if (PeriodTask.ONETIME.equals(task.getRepeatability())) {
                    cycleDate = new OneTime().nextTime(cycleDate);
                }
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
