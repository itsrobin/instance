package math.algorithm_1_2;

/**
 * @author lifeng
 * @version 2.0 on 17/1/4.
 */
public class Accumulator {
    private double m;
    private double s;
    private int N;

    public void addDataValue(double x) {
        N++;
        s = s+1.0*(N-1)/N * (x-m)*(x-m);

    }
}
