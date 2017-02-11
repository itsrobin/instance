package math.algorithm_1_4;

/**
 * @author lifeng
 * @createTime 2017/1/30.
 * @updateTime 2017/1/30
 */
public class TestLog {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(test1_4_6_g(i));
        }
    }

    private static double test1_4_6_e(int N) {
       return Math.log(N<<1)/Math.log(N);
    }

    private static double test1_4_6_f(int N) {
        return Math.log10(N*N+1)/Math.log10(N);
    }

    private static double test1_4_6_g(int N) {
        double a = Math.pow(N,100);
        double b = Math.pow(2,N);
        return a/b;
    }
}
