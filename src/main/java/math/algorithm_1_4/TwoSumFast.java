package math.algorithm_1_4;

import search.BinarySearch;

import java.util.Arrays;

/**
 * @author lifeng
 * @createTime 2017/1/28.
 * @updateTime 2017/1/28
 */
public class TwoSumFast {

    public static int count(int[] a) {
        //计算和为0的整数对的数目
        //a.整个部分:1
        Arrays.sort(a); //b.使用了快速排序,NlogN
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {   //c.N
            if (BinarySearch.rank(-a[i],a)>1)   //d.logN
                cnt++;  //取决于输入e.x
        }
        return cnt;
    }
}
