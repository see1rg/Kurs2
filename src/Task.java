import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Task {
    private boolean personalOrWork;
    private String taskName;
    private String descriptionOfTask;
    private String currentTime;
    private LocalDate dateTime;
    private PeriodTask periodTask;
    public static int count;
    private final Integer id;
    private final static Map<Integer, Task> setOfTasks = new HashMap<>();


    public Task(boolean personalOrWork, String taskName,
                String descriptionOfTask, LocalDate dateTime,
                PeriodTask periodTask) throws Exception {
        setPersonalOrWork(personalOrWork);
        setTaskName(taskName);
        setDescriptionOfTask(descriptionOfTask);
        setDateTime(dateTime);
        setPeriodTask(periodTask);
        setCurrentTime();
        id = count++;
    }

    public static void getDateTask(LocalDate localDate) {
        Collection<Task> o = setOfTasks.values();
        for (Task task : o) {
            if (task.dateTime.equals(localDate)) {
                System.out.println("Задачи на дату: " + localDate + " " + task);
            } else {
                System.out.println("Задачи на дату: " + localDate + " отсутствуют.");
            }
        }
    }

    public static void deleteTask(int choiceId) {
        Collection<Task> o = setOfTasks.values();
        for (Task task : o) {
            if (task.id == choiceId) {
                setOfTasks.remove(task.id);
                System.out.println("Задача с id " + choiceId + " успешно удалена.");
            } else {
                System.out.println("Задача с id " + choiceId + " не найдена.");
            }
        }
    }

    public boolean isPersonalOrWork() {
        return personalOrWork;
    }

    public void setPersonalOrWork(boolean personalOrWork) {
        this.personalOrWork = personalOrWork;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setCurrentTime() {
        this.currentTime = ofPattern("dd MMM yyyy, k:mm").format(LocalDateTime.now());
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescriptionOfTask() {
        return descriptionOfTask;
    }

    public void setDescriptionOfTask(String descriptionOfTask) {
        this.descriptionOfTask = descriptionOfTask;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    private LocalDate checkDate(LocalDate dateTime) throws Exception {
        DateTimeFormatter dateFormatter = ofPattern("dd MM yyyy", new Locale("ru"));
        DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);
        return dateTime;
    }

    public PeriodTask getPeriodTask() {
        return periodTask;
    }

    public void setPeriodTask(PeriodTask periodTask) {
        if (periodTask != null) {
            this.periodTask = periodTask;
        } else {
            this.periodTask = PeriodTask.ONETIME;
        }
    }

    public static Map<Integer, Task> getSetOfTasks() {
        return setOfTasks;
    }

    public static void adSetOfTasks(Task task) {
        setOfTasks.put(task.getId(), task);
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{Задача id ->" + id + "{" +
                "личная -> " + personalOrWork +
                ", название -> '" + taskName + '\'' +
                ", описание -> '" + descriptionOfTask + '\'' +
                ", время создания задания -> " + currentTime +
                ", задание на дату '" + dateTime + '\'' +
                ", периодичность -> " + periodTask +
                '}';
    }
}
