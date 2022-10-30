import java.time.LocalDate;

public interface Repeatability {
    static LocalDate checkDate(LocalDate localDate, PeriodTask periodTask) {
        if(periodTask.equals(PeriodTask.DAILY)){
            localDate = localDate.plusDays(1);
        }
        if (periodTask.equals(PeriodTask.WEEKLY)){
            localDate = localDate.plusWeeks(1);
        }
        if (periodTask.equals(PeriodTask.MONTHLY)){
            localDate = localDate.plusMonths(1);
        }
        if (periodTask.equals(PeriodTask.ANNUAL)){
            localDate = localDate.plusYears(1);
        }
        return localDate;
    }
}
