package math;

import collection.Lists;
import edu.princeton.cs.algs4.*;
import math.algorithm_1_2.VisualCounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by lifeng on 16/6/21.
 */
public class MathCal {
    /**
     * greatest common divisor(gcd)
     * 计算两个数的最大公约数,欧几里得算法
     * 猜想gcd(a,b) = gcd (b,a%b)
     * 设 d 是 i 和 j 的最大公约数,
     * i = md , j = nd , m 和 n 互为质数(不然 d 还会更大)
     * r = a % b --> i = kj + r
     * md = knd +r --> r = d(m-kn)
     * m-kn 与 m 互质
     * 那么 d 也是 r的最大公约数
     * 因为 d 为 r 和 j 的最大公约数
     * 因此gcd(a,b) = gcd (b,a%b)
     * 猜想成立
     *
     * @param numA 数字a
     * @param numB 数字b
     * @return
     */
    public static int gcd(int numA, int numB) {
        if (numB == 0) return numA;
        int r = numA % numB;
        return gcd(numB, r);
    }


    /**
     * 素数是只能被1和他本身整除的整数
     *
     * @param i
     * @return
     */
    public static boolean isPrime(int i) {
        if (i < 2) return false;
        for (int j = 2; j * j < i; j++) {
            if (i % j == 0) return false;
        }
        return true;
    }

    /**
     * 牛顿迭代法求平方根
     * 牛顿迭代法用来求解 f(x)=0的根.
     * 假设这个根为r.那么我们先拿出一个与r近似的值x0
     * 过这个点且与f(x)相切的曲线为y=f(x0)+f'(x0)(x-x0)
     * 这样就能得出该曲线与x轴的交点为x=x0-f(x0)/f'(x0),这个点比x0更接近r
     * 通过反复地计算这个点最后得出近似的点.--> xn+1 = xn-f(xn)/f'(xn)
     * <p>
     * 用(x,f(x))的切线来逼近方程x^2-a=0的根,
     * 设t=根号a,t实际上就是x^2-a=0的一个正实根,这个函数的导数是2x.
     * 也就是说，函数上任一点(x,f(x))处的切线斜率是2x.
     * xn+1 = xn - (xn²-a)/2xn=1/2(xn+a/xn)
     *
     * @param a
     * @return
     */
    public static double sqrt(double a) {
        if (a < 0) return Double.NaN;
        double err = 1e-15;//double的小数位长度
        double t = a;

        while (Math.abs(t - a / t) > err * t) {
            t = (a / t + t) / 2.0;
        }
        return t;
    }

    /**
     * n次方的开方求解
     *
     * @param n n次方
     * @param a 被开方的数
     * @return
     */
    public static double nSqrt(double n, double a) {
        if (a < 0) return Double.NaN;
        double err = 1e-15;
        double t = a;
        while (Math.abs(t - a / (Math.pow(t, n - 1))) > err * t) {
            t = (1 - 1 / n) * t + a / (n * Math.pow(t, n - 1));
        }
        return t;
    }

    /**
     * 求e的x次方的求解.根据牛顿迭代法推算得到
     * 缺陷,不能接受过大的数:例如e的100次方
     *
     * @param N
     * @return
     */
    public static double In(double N) {
        if (N < 0) return -1;
        double t = N;
        double E = Math.E;
        double err = 1e-15;
        while (Math.abs(t - N * t / (Math.pow(E, t))) > err * t) {
            t = t - 1 + N / Math.pow(E, t);
            System.out.println(t);
        }
        return t;
    }

    /**
     * 二进制转换
     *
     * @param a 10进制数
     * @return 二进制
     */
    public static String toBinaryString(int a) {
        StringBuilder s = new StringBuilder();
        for (int i = a; i > 0; i /= 2) {
            s.append(i % 2);
        }
        ;
        //将顺序反转
        return s.reverse().toString();
    }

    public static int rank(int key, int[] a, int lo, int hi, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        System.out.print("lo: " + lo + " hi: " + hi + "\n");
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1, depth + 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, depth + 1);
        else return mid;
    }

    public static void sort(int a, int b, int c) {
        int t;
        if (a > b) {
            t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            t = b;
            b = c;
            c = t;
        }
    }

    ;


    /**
     * $ C_{n+1}^m = C_n^m + C_n^{m-1}$
     *
     * @param N
     * @param K
     * @return
     */
    public static double binomial(int N, int K, Double[][][] m) {
        double p = 0.5;
        if (N == 0 && K == 0) {
            return 1.0;
        } else if (N < 0 || K < 0) {
            return 0.0;
        }

        Double val;
        if ((val = m[N][K][0]) != null) {
            return val;
        }
        double a = (1.0 - p) * binomial(N - 1, K, m);
        double b = p * binomial(N - 1, K - 1, m);
        m[N][K][0] = a + b;
        return a + b;
    }

//    public static void main(String[] args) {
//
//        Double[][][] m = new Double[101][100][1];
//        long a = System.nanoTime();
//        System.out.println(a);
//        System.out.println(binomial(100,5,m));
//        long b = System.nanoTime();
//        System.out.println(b);
//        System.out.println(b-a);
//         a = System.nanoTime();
//        System.out.println(a);
//        System.out.println(binomial(100,5,0.5));
//        b = System.nanoTime();
//        System.out.println(b);
//        System.out.println(b-a);
//    }

    public static double binomial(int N, int K, double p) {
        double i = factorial(N, K);
        double succ = Math.pow(p, K);
        double fail = Math.pow(1 - p, N - K);
        return i * succ * fail;
    }

    public static double factorial(double N, double K) {
        double value1 = 1;
        double value2 = 1;
        double c = N;
        for (double i = K; i > 0; i--) {
            value1 *= c;
            c -= 1;
        }
        for (double i = K; i > 0; i--) {
            value2 *= i;
        }
        return value1 / value2;
    }


    public static int[] deleteDuplicate(int[] a) {
        int temp = a[0];
        int length = 1;
        for (int i = 1; i < a.length; i++) {
            if (temp != a[i]) {
                temp = a[i];
                a[length] = temp;
                length++;
            }
        }
        int[] b = new int[length];
        System.arraycopy(a, 0, b, 0, 3);
        return b;
    }


    public static void main(String[] args) {
        int range = 100; //设置x和y轴范围的参数
        int r = 64; //画圆的半径
        int N = 64; // 均分的点
        double rate = 0.9; //相连的概率

        Random random = new Random();

        //设置x和y轴的范围
        StdDraw.setXscale(-range, range);
        StdDraw.setYscale(-range, range);
        //指出圆心
        StdDraw.text(0, 0, "圆心");
        //画出x和y轴
        StdDraw.line(0, 0, 100, 0);
        StdDraw.line(0, 0, 0, 100);
        StdDraw.circle(0, 0, r);

        StdDraw.text(16, 0, "固定点");
        double startAngle = 0; //初始点的角度
        double angle = 360.0 / N;//每个点偏移的角度

        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            StdDraw.setPenRadius(0.02);
            //计算每个点的坐标
            double x = Math.cos(Math.toRadians(startAngle));
            double y = Math.sin(Math.toRadians(startAngle));
            //将坐标加入到数组中
            points[i] = new Point(r * x, r * y);
            //将点画出来
            StdDraw.point(r * x, r * y);
            //角度递增,用于计算下一个点
            startAngle += angle;
        }

        //计算每两个点的组合,用于连线.
        List<Point> list = new ArrayList<>();
        comb(points, 2, list);

        //每两个为一对
        //例如5个点进行匹配,总共会有10对组合,10对组合的点总共有20个对象,因此在list每两个为一对
        for (int i = 0; i < list.size(); i += 2) {
            //每次遍历得到两个点
            Point pointA = list.get(i);
            Point pointB = list.get(i + 1);
            StdDraw.setPenRadius(0.001);
            //根据概率控制生成
            double num = random.nextDouble();
            if (num < rate) {
                //两个点相连
                StdDraw.line(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
            }
        }

    }

    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }


    /**
     * 计算组合的方法
     *
     * @param points 可选参数的数组
     * @param n      要组合的个数
     * @param result 各个组合的结果
     */
    public static void comb(Point[] points, int n, List<Point> result) {
        /**
         * 初始时默认组合的字符是数组的前n项.假设数组长度为5(1,2,3,4,5),n为3
         * 那么pos为(0,1,2),即默认组合为1,2,3,也可以理解为 1,1,1,0,0(1代表被组合的项)
         */
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = i;
        }

        while (true) {
            boolean isMove = false;
            for (int po : pos) {
                result.add(points[po]);
            }

            /**
             * 现在我们要做的是要返回的组合全部罗列出来,从(1,1,1,0,0)一直到(0,0,1,1,1)的组合
             * 1.(1,1,1,0,0) -> pos(0,1,2);
             * 就是我们需要返回的全部组合.需要最右边的1依次向右推进那么就变成了
             * 2.(1,1,0,1,0) -> pos(0,1,3);
             *
             * 3.(1,1,0,0,1) -> pos(0,1,4);
             * 已经到底,那么倒数第二位开始推进,初始状态是这样
             * 4.(1,0,1,1,0) -> pos(0,2,3);
             *
             * 5.(1,0,1,0,1) -> pos(0,2,4);
             * 6.(1,0,0,1,1) -> pos(0,3,4);
             * 接下来是倒数第三位右推,初始状态
             * 7.(0,1,1,1,0) -> pos(1,2,3);
             *
             * 8.(0,1,1,0,1) -> pos(1,2,4);
             * 9.(0,1,0,1,1) -> pos(1,3,4);
             * 10.(0,0,1,1,1) -> pos(2,3,4);
             * 总共有10种,和C(5,3)的结果一致
             */

            for (int i = n - 1; i >= 0; i--) {
                if (pos[i] < points.length - n + i) {
                    pos[i]++;

                    //每次i的值变更后,都需要重置i之后的选位的值,例如在第3步,i为2,pos[i]为4
                    //但是之后i为1,那么之后的pos[2]应该重置为2+1
                    for (int j = i + 1; j < n; j++) {
                        pos[j] = pos[i] + j - i;

                    }
                    isMove = true;
                    break;
                }
            }

            if (!isMove) {
                break;
            }
        }
    }

    public static double dot(double[] x, double[] y) {
        double result = 0;
        if (x.length != y.length) {
            return result;
        }

        for (int i = 0; i < x.length; i++) {
            result += x[i] * y[i];
        }
        return result;
    }


    public static void histogram(String[] args) {
        double[] doubleStream = new double[]{1.2, 1.3, 1.5, 1.6, 1.7};

        int N = 5;
        double l = 1;
        double r = 2;

        double dis = (r - l) / N;

        int[] arrays = new int[N];

        for (double v : doubleStream) {
            int index = (int) Math.ceil((v - l) / dis);
            if (index > 0) {
                arrays[index]++;
            }
        }

        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 25);
        StdDraw.line(0, 0, 100, 0);
        StdDraw.line(0, 0, 0, 100);
        for (int i = 0; i < arrays.length; i++) {
            double x = l + i * dis;
            double y = arrays[i];

            StdDraw.filledRectangle(x, y, dis - 0.15, y);
        }
//        StdDraw.filledRectangle(1,2,1,2);
    }

    public static int dice() {
        /**
         * 首先计算理论数据
         */
        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                dist[i + j] += 1.0;
            }

        }

        for (int k = 2; k <= 2 * SIDES; k++) {
            dist[k] /= 36.0;
        }

        double[] dist2 = new double[2 * SIDES + 1];

        int N = 1;
        Random random = new Random();
        boolean isComplete = false;
        double dist3[];
        //随着N的递增,每次进行计算并和理论数据比对
        while (!isComplete) {
            int a = 1 + random.nextInt(6);
            int b = 1 + random.nextInt(6);
            dist2[a + b] += 1.0;
            dist3 = dist2.clone();
            for (int k = 2; k <= 2 * SIDES; k++) {
                dist3[k] /= N;
            }
            int index = 0;
            for (int i = 0; i < dist.length; i++) {
                int m = (int) (dist[i] * 1000);
                int n = (int) (dist3[i] * 1000);
                //数组中任何一个匹配数值不对就跳出循环
                if (m != n) {
                    break;
                }
                index = i;
            }
            //匹配完成,跳出while循环
            if (index == dist2.length - 1) {
                isComplete = true;
            }
            N++;
        }

        return N;
    }

    public static void dice2(String[] args) {
        System.out.println(dice());

        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                dist[i + j] += 1.0;
            }

        }

        for (int k = 2; k <= 2 * SIDES; k++) {
            dist[k] /= 36.0;
        }
        System.out.println(Arrays.toString(dist));


        double[] dist2 = new double[12 + 1];
        int N = 8235440;

        for (int i = 0; i < N; i++) {


            Random random = new Random();
            int a = 1 + random.nextInt(6);
            int b = 1 + random.nextInt(6);
            dist2[a + b] += 1.0;
        }
        for (int k = 2; k <= 2 * 6; k++) {
            dist2[k] /= N;
        }

        int index = 0;
        for (int i = 0; i < dist.length; i++) {
            int m = (int) (dist[i] * 1000);
            int n = (int) (dist2[i] * 1000);
            if (m != n) {
                break;
            }
            index = i;
        }
        System.out.println(index);
        if (index == dist2.length - 1) {
            System.out.println(Arrays.toString(dist2));
        }
    }


    public static void Point2D() {
        int N = 10;
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            //生成坐标-1到1的随机坐标
            double x = StdRandom.uniform() * 2 - 1;
            double y = StdRandom.uniform() * 2 - 1;
            Point point = new Point(x, y);
            points[i] = point;
        }
        List<Point> result = Lists.newArrayList();
        //每两个点相互匹配,该组合算法1.1.31有详细过程
        comb(points, 2, result);
        //计算并输出两点的坐标与两点之间的距离
        for (int i = 0; i < result.size(); i += 2) {
            Point a = result.get(i);
            Point b = result.get(i + 1);
            double dis = Math.sqrt(Math.pow((b.getY() - a.getY()), 2) + Math.pow((b.getX() - a.getX()), 2));
            System.out.println("(" + a.getX() + "," + a.getY() + ")," + "(" + b.getX() + "," + b.getY() + ")" + "dis:" + dis);
        }
    }

    /**
     * 随机打乱
     *
     * @param M
     * @param N
     */
    public static void shuffle(int M, int N) {
        int[] a = new int[M];
        int[][] b = new int[M][M];
        //将a初始化
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                a[j] = j;
            }
            //进行元素交换
            int k = i % M;
            //将a[i]和a[i..N-i]中任意一个元素交换
//                int r = k + StdRandom.uniform(M - k);
            int r = StdRandom.uniform(M);
            b[k][r] += 1;
        }

        for (int i = 0; i < b.length; i++) {
            System.out.print("row" + i + "| ");
            for (int v : b[i]) {
                System.out.print(v + "| ");
            }
            System.out.println();
        }
    }


    public static void Interval1D() {
        int N = 2;
        Interval1D[] interval1D = new Interval1D[N];
        for (int i = 0; i < N; i++) {
            //从命令行获取输入值
            System.out.println("输入点");
            double min = StdIn.readDouble();
            double max = StdIn.readDouble();
            //min和max抽象为一个对象
            interval1D[i] = new Interval1D(min, max);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                //判断是否相交
                if (interval1D[i].intersects(interval1D[j])) {
                    System.out.println(interval1D[i] + "," + interval1D[j]);
                }
            }
        }
    }

    public static void Interval2D() {
        int N = 5;
        double min = 0;
        double max = 1;

        Interval2D[] intervals = new Interval2D[N];
        for (int i = 0; i < N; i++) {
            double x0 = StdRandom.uniform(min, max);
            double x1 = StdRandom.uniform(min, max);
            double y0 = StdRandom.uniform(min, max);
            double y1 = StdRandom.uniform(min, max);
            Interval2D interval = new Interval2D(new Interval1D(getMin(x0, x1), getMax(x0, x1))
                    , new Interval1D(getMin(y0, y1), getMax(y0, y1)));
            intervals[i] = interval;
        }
        //输出包含关系
        int m = 0;
        for (int i = 0; i < intervals.length; i++) {
            intervals[i].draw();
            for (int j = i + 1; j < N; j++) {
                if (intervals[i].intersects(intervals[j])) {
                    m++;
                }
            }
        }
        System.out.println(m);
    }

    public static double getMin(double a, double b) {
        return a >= b ? b : a;
    }

    public static double getMax(double a, double b) {
        return a >= b ? a : b;
    }


    public static void circularRotation(String[] args) {
        String s = "GACTGAC";
        String t = "TGACGAC";

        if (s.length() == t.length() && (s + s).indexOf(t) != -1) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public static String mystery(String s) {
        int N = s.length();
        if (N <=1) return s;
        String a = s.substring(0,N/2);
        String b = s.substring(N/2,N);
        return mystery(b)+mystery(a);
    }

    public static class Counter{
        private final String name;
        private int count;
        public Counter(String id){
            name = id;
        }

        public void increment() {
            count ++;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int tally() {
            return count;
        }

        public String toString() {
            return count +" "+ name;
        }
    }

    public static int rank(int key, int[] a,Counter counter) {
        int hitLow = 0;
        int hitHigh = 0;
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int midInt = (low + high) / 2;
            if (key < a[midInt]) high = midInt - 1;
            else if (key > a[midInt]) low = midInt + 1;
            else {
                hitLow = hitHigh = midInt;
                //获得重复数据最低位
                while (key == a[hitLow-1]) {
                    hitLow --;
                }
                //获得重复数据最高位
                while (key == a[hitHigh+1]){
                    hitHigh ++;
                }
                //两者相减就是重复数据
                counter.setCount(hitHigh-hitLow+1);

                return hitHigh;
            }
        }
        return -1;
    }


    public static void main3(String[] args) {
        int N = 10;
        int max = 20;
        VisualCounter counter = new VisualCounter(N,max);
        for (int i = 0; i < N; i++) {
            double n = StdRandom.uniform(1.0,3.0);
            if (n>2) {
                counter.increase();
            }else{
                counter.decrease();
            }
        }

        counter.getStatistics();

    }
}
