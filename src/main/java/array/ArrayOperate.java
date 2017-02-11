package array;

import com.algs4.stdlib.StdDraw;
import com.algs4.stdlib.StdRandom;

/**
 * Created by lifeng on 16/6/22.
 */
public class ArrayOperate {
    /**
     * 颠倒数组的顺序
     * @param a 数组
     * @return 被颠倒的数组
     */
    public static int[] invert(int[] a) {
        int N = a.length;
        for (int i = 0; i < N / 2; i++) {
            int temp = a[i];
            a[i] = a[N-i-1];
            a[N-i-1] = temp;
        }
        return a;
    }

    /**
     * 矩阵相乘(方阵),
     * @param a {1,2}
     *          {1,3}
     *
     * @param b {2,1}
     *          {3,1}
     *
     * a的行数应与b的列数相等
     *
     * @return  {
     *            1*2+2*3 , 1*1+2*1     -->    {8,3}
     *            1*2+3*3 , 1*1+3*1            {11,4}
     *                  }
     */
    public static double[][] mult(double[][] a, double[][] b) {
        int a_row = a.length;  //矩阵a的行数
        int b_column = b[0].length; //矩阵b的列数

        double[][] c = new double[a_row][b_column];
        for (int i = 0; i < a_row; i++) {   //a的每行乘以
            //获得一行的数组
            double[] row_array = a[i];
            for (int j = 0; j < b_column; j++) { //b的每列
                double value = 0;
                //计算b每行相同列和a的i行的乘积
                for (int k = 0; k < b.length; k++) {
                    value += row_array[k] * b[k][j];
                }
                c[i][j] = value;
            }
        }
        //新矩阵的行数为a的行数,列数为b的列数
        return  c;
    }


    /**
     * 矩阵转置
     * @param a 要被转置的矩阵
     * @return 被转置的矩阵
     */
    public static int[][] transposeArray(int[][] a) {
        int row = a.length;
        int column = a[0].length;

        int[][] newArray = new int[column][row];

        //将原矩阵的行转换为列,列转换为行
        for (int i = column-1 ; i>=0 ; i--) {
            for (int s = 0;s<row;s++) {
                //column-i-1可以计算最后一列要表示为第一行的索引
                newArray[column-i-1][s] = a[s][i];
            }
        }

        //输出原始的矩阵
        for (int[] anA : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(anA[j] + " ");
            }
            System.out.println("");
        }

        //输出转置的矩阵
        for (int[] aNewArray : newArray) {
            for (int j = 0; j < newArray[0].length; j++) {
                System.out.print(aNewArray[j] + " ");
            }
            System.out.println("");
        }
        return newArray;
    }


    public static void drawRandConn(int N, double p)
    {
        StdDraw.setCanvasSize(1024, 1024);
        StdDraw.setScale(-1.0, 1.0);
        StdDraw.setPenRadius(.015);

        double[][] d = new double[N][2];
        for (int i = 0; i < N; i++)
        {
            d[i][0] = Math.cos(2 * Math.PI * i / N);
            d[i][1] = Math.sin(2 * Math.PI * i / N);
            StdDraw.point(d[i][0], d[i][1]);
        }

        StdDraw.setPenRadius();

        for (int i = 0; i < N - 1; i++)
            for (int j = i + 1; j < N; j++)
                if (StdRandom.bernoulli(p))
                    StdDraw.line(d[i][0], d[i][1], d[j][0], d[j][1]);
    }

    public static void main(String[] args)
    {
        int N = 64;
//        double p = Double.parseDouble(args[1]);
       double p =1;

        drawRandConn(N, p);
    }

    public static int mystery(int a, int b)  {
        if (b ==0) return 1;
        if (b% 2==0) return mystery(a*a,b/2);
        return mystery(a*a,b/2)+a;
    }

    public double[] mult(double[][] a, double[] x) {
        double[] result = new double[x.length];
        //将向量当做一维矩阵;
        for (int i = 0; i < a.length; i++) {
            double cal = 0;
            for (int j = 0; j < x.length; j++) {
                cal += a[i][j] * x[j];
            }
            result[i] = cal;
        }

        return result;

    }

    public double[] mult2(double[][] a, double[] x) {
        double[][] matrix_x = new double[1][1];
        matrix_x[0] =x;
       double[][] result = mult(a,matrix_x);
        return result[0];
    }




}
