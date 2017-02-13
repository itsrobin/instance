package math.algorithm_1_4;

/**
 * @author lifeng
 * @createTime 2017/2/10.
 * @updateTime 2017/2/10
 */
public class BinarySearchEnhance_1_4_11 {

   static int searchFirstPos(int A[], int n, int target)
    {
        if(n <= 0) return -1;
        int low = 0, high = n-1;
        while(low < high)
        {
            int mid = low+((high-low)>>1);
            if(A[mid] < target)
                low = mid+1;
            else // A[mid] >= target
                high = mid;
        }
    /*
    循环过程中，当low大于0时，A[low-1]是小于target的，因为A[mid] < target时，
    low=mid+1；当high小于n-1时，A[high]是大于等于target的，因为A[mid] >= target时，
    high = mid；循环结束时，low 等于 high，所以，如果A[low](A[high])等于target，
    那么low(high)就是target出现的最小位置，否则target在数组中不存在。
    */
        if(A[low] != target)
            return -1;
        else
            return low;
    }

   static int rank(int a[],int n,int data, boolean isLeft)
    {
        //isLeft标记值是否在左边
        if(a==null || n<=0)
            return -1;
        int begin=0;
        int end=n-1;
        int last=-1;
        while(begin <=end)
        {
            int mid=(begin+end)/2;
            if(a[mid] > data)
                end=mid-1;
            else if(a[mid] < data)
                begin=mid+1;
            else
            {
                last=mid;
                if(isLeft)
                    end=mid-1;
                else
                    begin=mid+1;
            }
        }
        return  last;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 3, 3, 3, 4, 5, 6};
        int right = rank(a, a.length, 3, false);
        int left = rank(a, a.length, 3, true);
        System.out.println(right - left + 1);
    }

}
