import java.time.LocalDate;

public class Daily implements Repeatability {
    @Override
    public LocalDate nextTime(LocalDate localDate) {
        return localDate.plusDays(1);
    }
}

