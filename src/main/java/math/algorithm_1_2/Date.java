package math.algorithm_1_2;

/**
 * @author lifeng
 * @version 2.0 on 17/1/3.
 */
 abstract class Date implements Comparable<Date>{
     int month;
     int day;
     int year;
    public  int month(){
        return month;
    };
    public int day(){
       return day;
    };
    public  int year() {
        return year;
    };

    public String toString() {
        return year+"-"+month+"-"+day;
    }

    public boolean equals(Object obj) {
        return obj instanceof Date &&
                obj.hashCode() == this.hashCode() &&
                obj.toString().equals(this.toString());
    }

    public int hashcode() {
        return year*10000+month*100+day;
    }
}
