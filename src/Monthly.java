import java.time.LocalDate;

public class Monthly implements Repeatability {
    @Override
    public LocalDate nextTime(LocalDate localDate) {
        return localDate.plusMonths(1);
    }
}
