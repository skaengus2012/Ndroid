package Ndroid.appFactory.launcher;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Njava.util.time.TimeBuilder;
import Njava.util.time.TimeUtil;

/**
 * Created by Doohyun on 2017. 3. 26..
 */

public class TimeBuilderTest {

    @Test
    public void runCreateBuilder() {
        // Create Builder

        // non-param : current time.
        TimeBuilder currentTimeBuilder = TimeBuilder.Create();

        // param : Calendar.
        TimeBuilder calendarBuilder = TimeBuilder.Create(TimeUtil.GetCalendar());

        // param : date
        TimeBuilder dateBuilder = TimeBuilder.Create(new Date());

        // param : string, format
        TimeBuilder stringBuilder = TimeBuilder.Create("2017-3-26", "yyyy-MM-dd");
    }

    @Test
    public void runSimpleComputeDate() {
        // simple java code.
        String dateString = "2017-3-26 16:40";

        try {
            // parse.
            Date formatData = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateString);

            // calculating
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatData);
            calendar.add(Calendar.YEAR, 1);
            calendar.add(Calendar.MONTH, 5);
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            // to yyMMdd
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            System.out.println(new SimpleDateFormat("yyyy.MM.dd (hh,mm,ss a)", Locale.ENGLISH).format(calendar.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        TimeBuilder.Create(dateString, "yyyy-MM-dd hh:mm").
                addYear(1).
                addMonth(5).
                addDay(-1).
                setLocale(Locale.ENGLISH).
                to_yyMMdd().
                getStringFormat("yyyy.MM.dd (hh,mm,ss a)").
                subscribe(System.out::println);

        TimeBuilder.Create(dateString, "yyyy-MM-dd hh:mm").endDayOfMonth().getStringFormat("yyyy.MM.dd").subscribe(System.out::println);
    }

    @Test
    public void runReturnType() {
        TimeBuilder currentTimeBuilder = TimeBuilder.Create();

        // return calendar.
        System.out.println(currentTimeBuilder.getCalendar());

        // return date.
        System.out.println(currentTimeBuilder.getDate());

        // return milli second.
        System.out.println(currentTimeBuilder.getTime());

        // return year.
        System.out.println(currentTimeBuilder.getYear());

        // return month (0-11).
        System.out.println(currentTimeBuilder.getMonth());

        // return month for human (1-12).
        System.out.println(currentTimeBuilder.getMonthForHuman());

        // return hour 24 type
        System.out.println(currentTimeBuilder.getHour24());

        // return hour 12 type
        System.out.println(currentTimeBuilder.getHour12());
    }

    @Test
    public void runEndOfMonth() {
        TimeBuilder.Create().addMonth(-1).addYear(-1).endDayOfMonth().getStringFormat("yyyy-MM-dd").subscribe(System.out::println);
        TimeBuilder.Create().firstDayOfMonth().getStringFormat("yyyy-MM-dd").subscribe(System.out::println);
    }

    @Test
    public void runTimeUtilExample() {
        Calendar calendar = TimeUtil.GetCalendar();

        System.out.println(TimeUtil.GetHour24(calendar));
    }
}
