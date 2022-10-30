
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ServiceTask {
    protected final static Map<Integer, Task> setOfTasks = new HashMap<>();

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

    protected static void getDateTask(LocalDate taskForDate) {
        Collection<Task> o = setOfTasks.values();
        for (Task task : o) {
            LocalDate cycleDate = task.getTimeCreateTask();
            if (task.getTimeCreateTask().isEqual(taskForDate)) {
                System.out.println(task);
            }
            while (!(task.getPeriodTask().equals(PeriodTask.ONETIME))
                    && (cycleDate.isBefore(taskForDate)
                    || (cycleDate.isEqual(taskForDate)
                    || task.getTimeCreateTask().isEqual(taskForDate) ))) {
                cycleDate = Repeatability.checkDate(cycleDate, task.getPeriodTask());
                if (cycleDate.isEqual(taskForDate)) {
                    System.out.println(task);
                }
            }
        }
    }



}
