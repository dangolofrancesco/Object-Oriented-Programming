import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ExampleApp {
    public static void main(String[] args) {
        LocalTime start = LocalTime.parse("12:30");
        LocalTime end = LocalTime.parse("18:20");

        long difference = start.until(end, ChronoUnit.MINUTES);

        System.out.println(difference%60);
    }
}
