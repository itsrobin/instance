package test.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * 测试java8的时间工具类
 * @author  lifeng
 * @version 1.0 2016-08-23
 *
 */
@Slf4j
public class testTimeUtil {

    public static void main(String[] args) {
    }

    @Test
    public void testLocalDate() {
       // LocalDate date = LocalDate.of(2016,8,23);
        LocalDate date = LocalDate.parse("2015-08-23");
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap  = date.isLeapYear();
        log.info("日期为:{}-{}-{},星期{},该月总天数为{},是否闰年:{}",
                year,month.getValue(),day,dayOfWeek.getValue(),len,leap);

        /*通过枚举*/
        LocalDate today = LocalDate.now();
         year = today.get(ChronoField.YEAR);
        int monthValue = today.get(ChronoField.MONTH_OF_YEAR);
         day = today.get(ChronoField.DAY_OF_MONTH);
        log.info("日期为:{}-{}-{}",year,monthValue,day);

    }


    @Test
    public void testLocalTime() {
        LocalTime time = LocalTime.now();
        //LocalTime time = LocalTime.of(13, 45, 20);
        //LocalTime time = LocalTime.parse("13:45:20");
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        log.info("当前时间为:{}:{}:{}",hour,minute,second);
    }

    @Test
    public void testLocalDateTime() {
        //当前时间
        LocalDateTime dt0 = LocalDateTime.now();
        log.info("输出dt0时间:{}",dt0);
        //指定时间
        LocalDateTime dt1 = LocalDateTime.of(2016, Month.AUGUST, 23, 14, 5, 20);
        log.info("输出dt1时间:{}",dt1);
        //合并time和date
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        log.info("输出dt2时间:{}",dt2);
        //已设定日期,指定时间
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        log.info("输出dt3时间:{}",dt3);
        LocalDateTime dt4 = date.atTime(time);
        log.info("输出dt4时间:{}",dt4);
        //已设定时间,指定日期
        LocalDateTime dt5 = time.atDate(date);
        log.info("输出dt5时间:{}",dt3);

        LocalDate date1 = dt1.toLocalDate();
        log.info("输出dt1的日期时间:{}",date1);
        LocalTime time1 = dt1.toLocalTime();
        log.info("输出dt1的时间信息:{}",time1);
    }

    @Test
    public void testInstant() {
        log.info("当前时间的时间戳:{}",Instant.now());
        log.info("当前时间的时间戳:{}",LocalDateTime.now());


    }
}
