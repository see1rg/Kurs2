public enum PeriodTask {
    ONETIME("однократная"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    ANNUAL("ежегодная");

//    void setOnetime();
    private final String periodTask;

    PeriodTask(String periodTask) {
        this.periodTask = periodTask;
    }

    public String getPeriodTask() {
        return periodTask;
    }
}
