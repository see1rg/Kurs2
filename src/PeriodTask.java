import java.time.LocalDate;

public enum PeriodTask implements Repeatability {
    ONETIME("однократная"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    ANNUAL("ежегодная");

    private final String periodTask;

    PeriodTask(String periodTask) {
        this.periodTask = periodTask;
    }

    public String getPeriodTask() {
        return periodTask;
    }

    @Override
    public LocalDate nextTime(LocalDate localDate) {
        return null;
    }
}
