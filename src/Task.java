import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Task{
    private boolean personalOrWork;
    private String taskName;
    private String descriptionOfTask;
    private String currentTime;
    private LocalDate dateTime;
    private PeriodTask periodTask;
    public static int count;
    private Integer id;
    private static Map<Integer,Task> setOfTasks = new HashMap<>();


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

    public static void getDateTask(LocalDate localDate){
        var o = setOfTasks.values();
        for (Task task : o) {
           if (task.dateTime.equals(localDate)){
               System.out.println("Задачи на дату: " + localDate + " " + task);
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

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime() {
        String dateTime = ofPattern("dd MMM yyyy, k:mm").format(LocalDateTime.now());
        this.currentTime = dateTime;
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

    public void setDateTime(LocalDate dateTime) throws Exception {

        this.dateTime =  dateTime;
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
        this.periodTask = periodTask;
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
        return "Task{" +
                "personalOrWork = " + personalOrWork +
                ", taskName = '" + taskName + '\'' +
                ", descriptionOfTask = '" + descriptionOfTask + '\'' +
                ", currentTime = " + currentTime +
                ", dateTime = '" + dateTime + '\'' +
                ", periodTask = " + periodTask +
                '}';
    }
}
