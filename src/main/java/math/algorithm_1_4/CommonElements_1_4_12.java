package math.algorithm_1_4;

import java.util.*;

/**
 * @author lifeng
 * @createTime 2017/2/11.
 * @updateTime 2017/2/11
 */
public class CommonElements_1_4_12 {

    /**
     * 将数组b中的元素全都放入散列表中
     * 遍历a,查找与b相同的元素
     * @param a
     * @param b
     * @return 相同的元素
     */
    public static List<Integer> getCommonElements(int[] a, int[] b) {
        Map<Integer, String> map = new HashMap<>(b.length << 1);
        for (int i : b) {
            map.put(i, "");
        }

        List<Integer> c = new ArrayList<>(map.size() << 1);
        for (int i : a) {
            if (map.get(i) != null) {
                c.add(i);
            }
        }

        return c;
    }

    public static void main(String[] args) {
        int a[] = new int[]{1,2,3,4,5};
        int b[] = new int[]{2,3,4,5,6};

        for (Integer integer : getCommonElements(a,b)) {
            System.out.print(integer+" ");
        }
    }
}
