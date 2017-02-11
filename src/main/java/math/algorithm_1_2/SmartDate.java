package math.algorithm_1_2;

/**
 * Date拓展类
 */
public class SmartDate extends Date {


    public SmartDate(int month, int day, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("月份非法");
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("日期非法");
        }
        //year是负数的话,当成公元前的吧
        //检查闰月情况
        checkFeb(month, day, year);
        checkDay(month, day);

        this.year = year;
        this.month = month;
        this.day = day;

    }

    /**
     * 检查每个月的最大天数
     *
     * @param month 月份
     * @param day   天数
     */
    private void checkDay(int month, int day) {
        if (month < 7) {
            if (month % 2 != 1) {
                if (day > 30) {
                    throw new IllegalArgumentException("日期非法");
                }
            }
        }
        if (month > 8) {
            if (month % 2 != 0) {
                if (day > 30) {
                    throw new IllegalArgumentException("日期非法");
                }
            }
        }
    }

    private void checkFeb(int month, int day, int year) {
        if (isLeapYear(year)) {
            if (month == 2 && day > 29) {
                throw new IllegalArgumentException("闰年2月份日期非法");
            }
        } else {
            if (month == 2 && day > 28) {
                throw new IllegalArgumentException("非闰年2月份日期非法");
            }
        }
    }

    private Boolean isLeapYear(int year) {
        //判断是否为整百年
        if (year % 100 == 0) {
            if (year % 4 == 0) {
                return true;
            }
        }
        //非整百年判断是否是闰年
        else if (year % 4 == 0) {
            return true;
        }
        return false;
    }


    private int dayOfTheWeek(int year, int month, int day) {
        //当年的1,2月要当成上一年的13,14月进行计算
        if (month == 1 || month == 2) {
            month += 12;
            year -= 1;
        }
        int y = year % 100;
        int c = year / 100;
        //蔡勒公式
        int week = y + y / 4 + c / 4 - 2 * c + (26 * (month + 1) / 10) + day - 1;
        while (week < 0) {
            week += 7;
        }
        return week % 7;
    }

    @Override
    public int compareTo(Date o) {
        if (this.hashCode() == o.hashCode()) {
            return 0;
        }
        if (this.hashCode() > o.hashCode()) {
            return 1;
        }
        return -1;
    }


}
