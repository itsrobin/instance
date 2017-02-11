package math.algorithm_1_4;

import java.util.Arrays;

/**
 * @author lifeng
 * @createTime 2017/2/5.
 * @updateTime 2017/2/5
 * 编写一个程序,计算输入文件中相等的证书对的数量.
 * 如果你的平方级别的,请继续思考,并用Array.sort()给出一个线性对数级别的答案.
 */
public class NLogN_1_4_8 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 3, 2, 2, 4, 5, 6, 3, 3};
        StopWatch timer = new StopWatch();
        System.out.println(getCount(a));
        System.out.println(timer.elapseTime());
    }

    public static int getCount(int[] a) {
        int cnt = 0;
        Arrays.sort(a);

        for (int i = 0; i < a.length; i++) {
            int j = i;
            while (j < a.length - 1 && a[i] == a[j + 1]) {
                j++;
                cnt++;
            }
            i = j;
        }
        return cnt;
    }
}
