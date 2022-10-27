import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Task{
    private boolean personalOrWork;
    private String taskName;
    private String descriptionOfTask;
    private String currentTime;
    private String dateTime;
    private PeriodTask periodTask;
    public static int count;
    private Integer id;
    private static Map<Integer,Task> setOfTasks = new HashMap<>();


    public Task(boolean personalOrWork, String taskName,
                String descriptionOfTask, String dateTime,
                PeriodTask periodTask) throws Exception {
        setPersonalOrWork(personalOrWork);
        setTaskName(taskName);
        setDescriptionOfTask(descriptionOfTask);
        setDateTime(dateTime);
        setPeriodTask(periodTask);
        setCurrentTime();
        id = count++;
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
        String dateTime = DateTimeFormatter.ofPattern("dd MMM yyyy, k:mm").format(LocalDateTime.now());
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) throws Exception {
        checkDate(dateTime);

        LocalDate   dateTime1 = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateTime1.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru")));

        this.dateTime =  dateTime1.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru")));
    }

    private void checkDate(String dateTime) throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", new Locale("ru"));
        DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

        if (!validator.isValid(dateTime)){
            throw new Exception ("неверно указана дата.");
        }
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

    public static void adSetOfTasks(Task task, PeriodTask periodTask) {

        switch (periodTask){
            case ONETIME -> {setOfTasks.put(task.getId(), task);
            }
            case DAILY -> {setDaily(task);
            }
            case WEEKLY -> {setWeekly(task);
            }
            case MONTHLY -> {setMonthly(task);
            }
            case ANNUAL -> {setAnnual(task);
            }
        }

    }

    private static void setDaily(Task task) {
        for (int i = 0; i < (365*3); i++) {
            setOfTasks.put(task.getId(), task);
        }

    }
    public static void setWeekly(Task task) {
        for (int i = 0; i < ((365*3)/7); i++) { // todo: currentTime дата добавления, dateTime должна меняться в констр и в методе (или убрать)
            // todo dateTime добавить время
        setOfTasks.put(task.getId(), task);}
    }
    public static void setMonthly(Task task) {
        for (int i = 0; i < ((365*3)/30); i++) {
        setOfTasks.put(task.getId(), task);}
    }
    public static void setAnnual(Task task) {
        for (int i = 0; i < 3; i++) {

        setOfTasks.put(task.getId(), task);}
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
