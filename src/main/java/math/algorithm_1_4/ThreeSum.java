package math.algorithm_1_4;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lifeng
 * @version 2.0 on 17/1/20.
 */
public class ThreeSum {
    /**
     * 暴力的3-sum算法
     *
     * @param a 数组
     * @return
     */
    public static int count(int[] a, int target) {
        int N = a.length;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if ((long) a[i] + a[j] + a[k] == target) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }



    public static int count2(int[] a, int target) {
        Arrays.sort(a);
        int cnt = 0;
        int total = 0;
        for (int i : a) {
            total+=twoSumForThreeSun(a,target-i,cnt);
        }

        return 0;
    }

    public static int twoSumForThreeSun(int[] a,int target,int cnt) {
        int i = 0;
        int j = a.length-1;
        Arrays.sort(a); //b.使用了快速排序,NlogN

        while(i < j ){
            int sum = a[i]+a[j];
            if (sum == target) {
                cnt++;
                ++i;
                --j;
            } else if (sum < target){
                ++i;
            }else {
                --j;
            }
        }

        return cnt;
    }

    @Test
    public static void test() {
        int[] a = new int[]{2,1,3,2,4,5,6,8,5,8,3,7,0,7};

    }


}
