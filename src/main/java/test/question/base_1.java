package test.question;

import com.algs4.stdlib.StdIn;
import com.algs4.stdlib.StdOut;

import java.util.Arrays;

/**
 * Created by lifeng on 16/6/25.
 */
public class base_1 {
    public static void question1_1_1() {
        System.out.println((0 + 15) / 2);
        System.out.println((2.0e-6) * 100000000.1);
        System.out.println(true && false || true && true);
    }

    public static void question1_1_2() {
        StdOut.println((1 + 2.236) / 2);
        StdOut.println(1 + 2 + 3 + 4.0);
        StdOut.println(4.1 >= 4);
        StdOut.println(1 + 2 + "3");
    }

    public static void question1_1_3() {
        int[] a = new int[3];
        StdOut.println("请输入三个数");
        for (int i = 0; i < a.length; i++) {
            a[i] = StdIn.readInt();
        }
        if (a[0] == a[1] && a[1] == a[2]) {
            StdOut.println("equals");
        } else {
            StdOut.println("not equals");
        }
    }

    public static void question1_1_4() {
        StdOut.println("a 没有then");
        StdOut.println("b 没有括号");
        StdOut.println("c 正确");
        StdOut.println("d c=0应该加上分号");
    }

    public static void question1_1_6() {
        int f = 0;
        int g = 1;
        for (int i = 0; i < 15; i++) {
            StdOut.println(f);
            f = f + g;
            g = f - g;
        }
        StdOut.println("会打印出菲波那切数列");
    }

    public static void question1_1_7() {
        StdOut.println("a---------");
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .001) {
            t = (9.0 / t + t) / 2.0;
        }
        StdOut.printf("%.5f\n", t);
        StdOut.println("b---------");
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        StdOut.println(sum);
        StdOut.println("c---------");
        int sum2 = 0;
        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < 1000; j++) {
                sum2++;
            }
        }
        StdOut.println(sum2);
    }

    public static void question1_1_8() {
        System.out.println('b');
        System.out.println('b' + 'c');
        System.out.println((char) ('a' + 4));
        StdOut.println("char类型是ASCII码编码,不同的字符对应不同的整型值,char为8位," +
                "第一位为符号位,所以范围为-2^7~2^7-1");
    }

    public static void question1_1_9() {
        StringBuilder s = new StringBuilder();
        int N = 4;
        for (int i = N; i > 0; i /= 2) {
            s.append(i % 2);
        }
        //将s内的值反转并转换为字符串
        StdOut.println(s.reverse().toString());
    }

    public static void question1_1_11() {
        int[][] a = new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                StdOut.print(a[i][j] + " ");
            }
            StdOut.println();
        }
    }

    public static void question1_1_12() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = 9 - i;
        }
        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
        }
        StdOut.println(Arrays.toString(a));
    }

    public static void question1_1_13() {
        int a[][] = new int[][]{{1, 2, 3}, {2, 3, 4}, {3, 4, 5}};
        int b[][] = new int[3][3];
        for (int i = a[0].length - 1; i >= 0; i--) {
            System.out.println(i);
            for (int j = 0; j < a.length; j++) {
                b[a[0].length - i - 1][j] = a[j][i];
            }
        }

        for (int[] anA : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(anA[j] + " ");
            }
            System.out.println("");
        }

        System.out.println("");

        for (int[] aNewArray : b) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(aNewArray[j] + " ");
            }
            System.out.println("");
        }
    }

    public static double question1_1_14(double N) {
        if (N < 0) StdOut.println("数据异常");
        double t = N;
        double err = 1e-15;
        while (Math.abs(t - N / t) > err * t) {
            t = (t + N / t) / 2;
        }
        return t;
    }

    public static int[] histogram1_1_15(int M) {
        int[] a = new int[]{1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 9};
        int[] b = new int[M];
        for (int i = 1; i <= M; i++) {
            int n = 0;
            for (int j : a) {
                if (j == i) n++;
            }
            b[i - 1] = n;
        }
        return b;
    }

    public static String exR1(int n) {
        if (n<=0) return "";
        return exR1(n-3)+n+exR1(n-2)+n;
    }

    public static int mystery(int a, int b) {
        if (b==0) return 1;
        if (b%2 == 0) return mystery(a*a,b/2);
        return mystery(a*a,b/2)*a;
    }

    public static long F(int N) {
        long lo = 0;
        long hi = 1;
        for (int i = 0; i < N; i++) {
            hi = hi + lo;
            lo = hi - lo;
            System.out.println(lo);

        }
        return lo;
    }

    public static double In(double N) {
        if (N < 0) return -1;
        double t = Math.sqrt(N);
        double E = Math.E;
        double err = 1e-15;
        while(Math.abs(t - N*t/ (Math.pow(E, t))) > err * t) {
            t = t-1+N/Math.pow(E,t);
        }
        return t;
    }

    public static double question1_1_20(double N) {
        if (N<=1) {
            return 1;
        }
        else {
            return N * question1_1_20(N-1);
        }

    }

    public static void main(String[] args) {
//        System.out.println(In(Math.pow(Math.E, 20)));

        System.out.println(In(Math.pow(Math.E, 2)));
    }
}
