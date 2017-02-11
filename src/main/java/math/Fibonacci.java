package math;

/**
 * Created by lifeng on 16/6/25.
 */
public class Fibonacci {
    /**
     * 递归进行的斐波那契
     *
     * @param N
     * @return
     */
    public static long recursive(int N, long[] b) {
        if (N==0)return 0;
        if (N==1)return 1;

        long val;
        if ((val=b[N - 1]) != 0) return val;

        long a = recursive(N-1,b);
        long c = recursive(N-2,b);

        b[N-1] = a+c;
        return a+c;
    }


    /**
     * 迭代进行的菲波那切数列
     *
     * @param N
     * @return
     */
    public static long simpleIterate(int N) {
        long lo = 0;
        long hi = 1;
        for (int i = 0; i < N; i++) {
            hi = hi + lo;
            lo = hi - lo;
            //System.out.println(i + " " + lo + "(" + hi + ")");
        }
        return lo;
    }



    public static void main(String[] args) {
        long[] b = new long[100];
        for (int N = 0; N <100; N++)
            System.out.println(N + " " +recursive(N,b) );
    }
}
