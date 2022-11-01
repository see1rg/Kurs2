import java.time.LocalDate;

public class Annual implements Repeatability {
    @Override
    public LocalDate nextTime(LocalDate localDate) {
        return localDate.plusYears(1);
    }
}
