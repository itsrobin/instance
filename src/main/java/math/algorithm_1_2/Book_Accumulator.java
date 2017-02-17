package math.algorithm_1_2;

/**
 * 这段代码来自算法(第四版)1.2.4.3
 * @author lifeng
 * @version 1.0 on 2017/2/15.
 */
public class Book_Accumulator {
    private double total;
    private int N;

    public void addDataValue(double val) {
        N++;
        total += val;
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "Mean (" + N + "values):" + String.format("%7.5f", mean());
    }
}
