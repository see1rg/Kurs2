import java.time.LocalDate;

public class OneTime implements Repeatability {
    @Override
    public LocalDate nextTime(LocalDate localDate) {
        return null;
    }
}
