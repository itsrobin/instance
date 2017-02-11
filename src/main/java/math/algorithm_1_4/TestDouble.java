package math.algorithm_1_4;

import com.algs4.stdlib.StdOut;
import com.algs4.stdlib.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @author lifeng
 * @createTime 2017/1/26.
 * @updateTime 2017/1/28.
 */
public class TestDouble {
    public static double timeTrial(int N) {
        //为处理N个随机的六位整数的ThreeSum.count()计时
        int Max = 1000000;
        int a[] = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-Max, Max);
        }
        StopWatch timer = new StopWatch();
        int cnt = ThreeSum.count(a);
        return timer.elapseTime();

    }

    public static void getTime() {
        //打印运行时间的表格
        for (int N = 500; true; N += 500) {
            //打印问题规模为N时程序的用时
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }

    /**
     * 标准图像
     */
    public static void standard() {
        //设置x轴和y轴的范围
        StdDraw.setXscale(-500, 10000);
        StdDraw.setYscale(-1, 10);
        //画出x轴和y轴
        StdDraw.line(0, 0, 10000, 0);
        StdDraw.line(0, 0, 0, 100);
        //设置线条的宽度
        StdDraw.setPenRadius(0.005);
        //在x轴和y轴上描点
        for (int i = 0; i < 10; i++) {
            StdDraw.point(i * 1000, 0);
            StdDraw.point(0, i);
            StdDraw.text(i * 1000, -0.5, i + "k");
            StdDraw.text(-300, i, i + "T");
        }

        double temp_x = 0.0;
        double temp_y = 0.0;

        //计算用时,在页面画出
        for (int N = 1000; true; N += 1000) {
            //打印问题规模为N时程序的用时
            double time = timeTrial(N);
            StdDraw.line(temp_x, temp_y, N, time);
            StdOut.printf("%7d %5.1f\n", N, time);
            temp_x = N;
            temp_y = time;
        }
    }

    /**
     *对数图像
     */
    public static void logarithm() {
        StdDraw.setXscale(-500, 10000);
        StdDraw.setYscale(-1, 10);
        StdDraw.line(0, 0, 10000, 0);
        StdDraw.line(0, 0, 0, 100);
        StdDraw.setPenRadius(0.005);
        int n = 1;
        for (int i = 0; i < 10; i++) {

            StdDraw.point(i * 1000, 0);
            StdDraw.point(0, i);
            //以1,2,4的顺序绘制x轴和y轴上的点
            StdDraw.text(i * 1000, -0.5, n + "k");
            StdDraw.text(-300, i, n + "T");
            n <<= 1;
        }
        double temp_x = 0.0;
        double temp_y = 0.0;


        for (int N = 2; true; N += N) {
            //打印问题规模为N时程序的用时
            double time = timeTrial(N*1000);
            //x轴上的点和实际算出的点不一致,需要使用以2为底的对数进行计算.
            double k = Math.log(N)/Math.log(2);
            double t = Math.log(time)/Math.log(2);
            StdDraw.line(temp_x, temp_y, k*1000, t);
            StdOut.printf("%7d %5.1f\n", N*1000, time);
            temp_x = k*1000;
            temp_y = t;
        }
    }

    public static void main(String[] args) {
        //standard();//标准图像
        logarithm();//对数图像
    }

}
