import java.time.LocalDate;

public class Weekly implements Repeatability {
    @Override
    public LocalDate nextTime(LocalDate localDate) {
        return localDate.plusWeeks(1);
    }
}
