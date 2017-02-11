package math.algorithm_1_4;

/**
 * Created by lifeng on 2017/1/26.
 */
public class StopWatch {
    private final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    public double elapseTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(~(-3)));
        System.out.println(Integer.toBinaryString((-3)));
    }
}
