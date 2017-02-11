package draw;

import com.algs4.stdlib.StdDraw;
import com.algs4.stdlib.StdRandom;

import java.util.Arrays;

/**
 * Created by lifeng on 16/6/24.
 * StdDraw绘图演示
 */
public class StdDrawExample {
    //绘制函数
    public static void drawFunc() {
        int N = 100;
        //设置x轴的范围
        StdDraw.setXscale(0, N);
        //设置y轴的范围
        StdDraw.setYscale(0,N*N);
        //将画笔的粗细半径设为r
        StdDraw.setPenRadius(.01);
        for (int i = 0; i < N; i++) {
            StdDraw.point(i,i);
            StdDraw.point(i,i*i);
            StdDraw.point(i,i*Math.log(i));
        }
    }
    //画出一个随机数组的图
    public static void randomArray() {
        int N = 50;
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.random();
        }
        for (int i = 0; i < N; i++) {
            double x = 2.0 * i / N; //x轴代表一个单位所占用的宽度
            double y = a[i]/2.0; //y轴代表高度
            double rw = 0.5/N; //计算实际宽度
            double rh = a[i]/2.0; //计算高度
            StdDraw.filledRectangle(x,y,rw,rh);
        }
        System.out.println(Arrays.toString(a));
    }

    //画出一个有序数组
    public static void sortedArray() {
        int N = 50;
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.random();
        }
        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            double x = 1.5 * i/N; //每个长方形的x轴的坐标
            double y = a[i]/2.0;  //每个长方形y轴的坐标
            double rw = 0.5/N;  //每个长方形的宽度
            double rh = a[i]/2.0;//每个长方形的长度
            StdDraw.filledRectangle(x,y,rw,rh);
        }
    }

    public static void main(String[] args) {
        sortedArray();
    }
}
