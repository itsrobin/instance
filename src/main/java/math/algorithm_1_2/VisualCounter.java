package math.algorithm_1_2;

import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lifeng
 * @version 2.0 on 17/1/3.
 */
public class VisualCounter {
    private int N;  //操作最大次数
    private int max; //最大计数值
    private int opNum;  //当前操作次数
    private int count; //当前计数值
    private List<Integer> record;   //记录计数轨迹


    public VisualCounter(int N, int max) {
        record = new ArrayList<>();
        this.N = N;
        this.max = max;
    }


    public void checkCount() {
        if (Math.abs(count) > max) {
            throw new RuntimeException("计数超过最大值");
        }
        if (opNum > N) {
            throw new RuntimeException("操作超过最大值");
        }
    }

    /**
     * 获得计数值
     *
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * 加法操作,并添加记录
     *
     * @return count
     */
    public int increase() {
        checkCount();
        opNum += 1;
        count += 1;
        record.add(count);
        return count;
    }

    /**
     * 减法操作,并添加记录
     *
     * @return count
     */
    public int decrease() {
        checkCount();
        opNum += 1;
        count -= 1;
        record.add(count);
        return count;
    }

    /**
     * 画出计数的直方图
     */
    public void getStatistics() {
        StdDraw.setXscale(-5, 40);
        StdDraw.setYscale(-25, 25);
        StdDraw.line(0, 0, 100, 0);
        StdDraw.line(0, 0, 0, 100);
        StdDraw.line(0, 0, 0, -100);
        for (int i = 0; i < max; i++) {
            StdDraw.setPenRadius(0.005);
            StdDraw.point(0,i);
            StdDraw.point(0,-i);
        }
        for (int i = 0; i < record.size(); i++) {
            double x = 1 + i;
            double y = record.get(i);

            StdDraw.filledRectangle(x, y, 0.25, Math.abs(y));
        }
    }
}
