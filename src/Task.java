import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Task {
    private PersonalOrWork personalOrWork;
    private String taskName;
    private String descriptionOfTask;
    private LocalDate timeCreateTask;
    private static int count;
    private final Integer id;
    private Repeatability repeatability;

    public Task(PersonalOrWork personalOrWork, String taskName,
                String descriptionOfTask, LocalDate timeCreateTask,
                Repeatability repeatability) throws Exception {
        setRepeatability(repeatability);
        setPersonalOrWork(personalOrWork);
        setTaskName(taskName);
        setDescriptionOfTask(descriptionOfTask);
        setTimeCreateTask(timeCreateTask);
        id = count++;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public void setRepeatability(Repeatability repeatability) {
        this.repeatability = repeatability;
    }

    public PersonalOrWork isPersonalOrWork() {
        return personalOrWork;
    }

    public void setPersonalOrWork(PersonalOrWork personalOrWork) {
        if (personalOrWork != null) {
            this.personalOrWork = personalOrWork;
        } else {
            this.personalOrWork = PersonalOrWork.PERSONAL;
        }
    }

    public String getTaskName() {
        return taskName;
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

    public LocalDate getTimeCreateTask() {
        return timeCreateTask;
    }

    public void setTimeCreateTask(LocalDate timeCreateTask) {
        this.timeCreateTask = timeCreateTask;
    }

    private LocalDate checkDate(LocalDate dateTime) throws Exception {
        DateTimeFormatter dateFormatter = ofPattern("dd MM yyyy", new Locale("ru"));
        DateValidatorUsingDateTimeFormatter validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);
        return dateTime;
    }

    public static void adSetOfTasks(Task task) {
        ServiceTask.setOfTasks.put(task.getId(), task);
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
                ", задание на дату '" + timeCreateTask + '\'' +
                ", периодичность -> " + repeatability +
                '}';
    }

}
