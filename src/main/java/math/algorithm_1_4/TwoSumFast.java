package math.algorithm_1_4;

import org.junit.Assert;
import org.junit.Test;
import search.BinarySearch;

import java.util.Arrays;

/**
 * @author lifeng
 * @createTime 2017/1/28.
 * @updateTime 2017/1/28
 */
public class TwoSumFast {

    /**
     *
     * @param a 数据
     * @param target 2-sum 求和的数
     * @return 命中的数量
     */
    public static int count(int[] a,int target) {
        //计算和为0的整数对的数目
        //a.整个部分:1
        Arrays.sort(a); //b.使用了快速排序,NlogN
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {   //c.N
            if (BinarySearch.rank(target-a[i],a)>1)   //d.logN
                cnt++;  //取决于输入e.x
        }
        return cnt;
    }

    public static int count2(int[] a,int target) {
        int i = 0;
        int j = a.length-1;
        int cnt = 0;
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
    public void test() {
        int[] a = new int[]{2,1,3,2,4,5,6,8,5,8,3,7,0,7};
        int cnt = count(a,3);
        Assert.assertEquals("error",1,cnt);
    }

}
