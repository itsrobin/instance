package math;

import java.util.List;

/**
 * @author lifeng
 * @version 2.0 on 16/12/24.
 */
public class MyComb {

    public static void main(String[] args) {
        String[] keys = new String[]{"1", "2", "3", "4", "5"};
        comb(keys, 3, null);


    }

    /**
     * @param keys   数据源,这里是一个数组
     * @param n      组合的项数
     * @param result
     */
    public static void comb(String[] keys, int n, List<String> result) {
        /**
         * 初始时默认组合的字符是数组的前n项.假设数组长度为5(1,2,3,4,5),n为3
         * 那么pos为(0,1,2),即默认组合为1,2,3,也可以理解为 1,1,1,0,0(1代表被组合的项)
         */
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = i;
        }

        while (true) {
            boolean isMove = false;
            for (int po : pos) {
                System.out.print(keys[po] + " ");
            }
            System.out.println();
            /**
             * 现在我们要做的是要返回的组合全部罗列出来,从(1,1,1,0,0)一直到(0,0,1,1,1)的组合
             * 1.(1,1,1,0,0) -> pos(0,1,2);
             * 就是我们需要返回的全部组合.需要最右边的1依次向右推进那么就变成了
             * 2.(1,1,0,1,0) -> pos(0,1,3);
             *
             * 3.(1,1,0,0,1) -> pos(0,1,4);
             * 已经到底,那么倒数第二位开始推进,初始状态是这样
             * 4.(1,0,1,1,0) -> pos(0,2,3);
             *
             * 5.(1,0,1,0,1) -> pos(0,2,4);
             * 6.(1,0,0,1,1) -> pos(0,3,4);
             * 接下来是倒数第三位右推,初始状态
             * 7.(0,1,1,1,0) -> pos(1,2,3);
             *
             * 8.(0,1,1,0,1) -> pos(1,2,4);
             * 9.(0,1,0,1,1) -> pos(1,3,4);
             * 10.(0,0,1,1,1) -> pos(2,3,4);
             * 总共有10种,和C(5,3)的结果一致
             */

            for (int i = n - 1; i >= 0; i--) {
                if (pos[i] < keys.length - n + i) {
                    pos[i]++;

                    //每次i的值变更后,都需要重置i之后的选位的值,例如在第3步,i为2,pos[i]为4
                    //但是之后i为1,那么之后的pos[2]应该重置为2+1
                    for (int j = i + 1; j < n; j++) {
                        pos[j] = pos[i] + j - i;

                    }
                    isMove = true;
                    break;
                }
            }

            if (!isMove) {
                break;
            }
        }


    }
}
