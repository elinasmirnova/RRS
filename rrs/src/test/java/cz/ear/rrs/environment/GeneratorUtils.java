package cz.ear.rrs.environment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class GeneratorUtils {

    public GeneratorUtils() {
    }

    private static final Random RAND = new Random();

    private static int randomInt() {
        return RAND.nextInt();
    }

    public static LocalDate createRandomDate() {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(2019, 2020);
        return LocalDate.of(year, month, day);
    }

    public static LocalTime createRandomTimeFrom() {
        LocalTime time1 = LocalTime.of(0, 0, 0);
        LocalTime time2 = LocalTime.of(1, 0, 0);
        int secondOfDayTime1 = time1.toSecondOfDay();
        int secondOfDayTime2 = time2.toSecondOfDay();
        Random random = new Random();
        int randomSecondOfDay = secondOfDayTime1 + random.nextInt(secondOfDayTime2 - secondOfDayTime1);
        LocalTime randomLocalTime = LocalTime.ofSecondOfDay(randomSecondOfDay);
        return randomLocalTime;
    }

    public static LocalTime createRandomTimeTo(LocalTime time) {
        LocalTime time2 = LocalTime.of(time.getHour() + 3, 0, 0);
        int secondOfDayTime1 = time.toSecondOfDay();
        int secondOfDayTime2 = time2.toSecondOfDay();
        Random random = new Random();
        int randomSecondOfDay = secondOfDayTime1 + random.nextInt(secondOfDayTime2 - secondOfDayTime1);
        LocalTime randomLocalTime = LocalTime.ofSecondOfDay(randomSecondOfDay);
        return randomLocalTime;
    }

    private static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}
