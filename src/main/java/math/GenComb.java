package math;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lifeng
 * @version 2.0 on 16/12/24.
 */
public class GenComb {
    public static void main(String[] args) {
        String src_str = "1,2,3,4,5";

        ArrayList<String> result = new ArrayList<String>();

        long start = new Date().getTime();
        GenCom(src_str, ",", 3, result);
        long end = new Date().getTime() - start;

        System.out.println("Com:" + result.size() + ":" + end);
        for (String s : result) {
            System.out.println(s);
        }
        result.clear();

        start = new Date().getTime();
        GenPerm(src_str, ",", 2, result);
        end = new Date().getTime() - start;

        System.out.println("Perm:" + result.size() + ":" + end);

        /*
        for (int i=0; i<result.size(); ++i)
        {
            System.out.println( (i+1) + ": " + result.get(i));
        }
        */
    }

    //组合算法
    /*
     * 实际移位
     *   111000
     *   110100
     *   110010
     *   110001
     *   101100
     *   101010
     *   101001
     *   100110
     *   100101
     *   100011
     *   011100
     *   .......
     */
    public static void GenCom(String srcStr, String sep, int n, List<String> result) {
        String[] str_list = srcStr.split(sep);

        //选号位
        int[] pos = new int[n];

        //选不出来
        if (str_list.length < n || str_list.length <= 0 || n <= 0) {
            return;
        }

        //初始化前n是选号位
        for (int i = 0; i < n; i++) {
            pos[i] = i;
        }

        //循环处理
        while (true) {
//            //1.生成选择数据
//            StringBuffer buff = new StringBuffer();
//            for (int i = 0; i < n; i++) {
//                if (i > 0) {
//                    buff.append(sep);
//                }
//
//                buff.append(str_list[pos[i]]);
//            }
//
//            result.add(buff.toString());


            for (int po : pos) {
                System.out.print(str_list[po]+" ");
            }
            System.out.println();

            //2.进位
            //从选号位最右边开始，选择第一个可以右移的位置进行进位

            boolean is_move = false;

            for (int i = n - 1; i >= 0; i--) {
                if (pos[i] < str_list.length - n + i)    //可以进位
                {
                    pos[i]++;   //选位右移

                    //所有右边的选号全部归位
                    for (int k = i + 1; k < n; ++k) {
                        pos[k] = pos[i] + k - i;
                    }
                    is_move = true;

                    break;
                }
            }

            if (!is_move)   //没有成功移位,到头了
            {
                break;
            }
        }
    }

    //排列算法
    public static void GenPerm(String srcStr, String sep, int n, List<String> result) {
        String[] str_list = srcStr.split(sep);

        //选号位
        int[] pos = new int[n];

        //选不出来
        if (str_list.length < n || str_list.length <= 0 || n <= 0) {
            return;
        }

        //初始化前n是选号位
        for (int i = 0; i < n; i++) {
            pos[i] = i;
        }

        //先算组合，对组合计算全排列

        //循环处理
        while (true) {
            //pos为选择位，pos_为排列位
            //生成排列数据
            int[] pos_ = new int[n];
            for (int i = 0; i < n; i++) {
                pos_[i] = pos[i];
            }

            //阶乘,排列个数
            int total = factorial(n);

            //生成选好号位的排列
            for (int i = 0, p = 0; i < total; i++) {
                //生成选择数据
                StringBuffer buff = new StringBuffer();
                for (int k = 0; k < n; k++) {
                    if (k > 0) {
                        buff.append(sep);
                    }

                    buff.append(str_list[pos_[k]]);
                }

                result.add(buff.toString());

                if (p == (n - 1)) {
                    p = 0;
                }

                if (n > 1) {
                    int tmp = pos_[p];
                    pos_[p] = pos_[p + 1];
                    pos_[p + 1] = tmp;
                }

                p++;
            }

            boolean is_move = false;

            //从选号位最右边起，进位
            for (int i = n - 1; i >= 0; i--) {
                if (pos[i] < str_list.length - n + i)    //可以进位
                {
                    pos[i]++;   //选位右移

                    //所有右边的选号全部归位
                    for (int k = i + 1; k < n; ++k) {
                        pos[k] = pos[i] + k - i;
                    }
                    is_move = true;

                    break;
                }
            }

            if (!is_move)   //没有成功移位,到头了
            {
                break;
            }
        }
    }

    //允许重号排列算法
    //不用移位挑选组合号码
    public static void GenPosPerm(String srcStr, String sep, int n, List<String> result) {
        String[] str_list = srcStr.split(sep);

        //选号位
        int[] pos = new int[n];

        //选不出来
        if (str_list.length <= 0 || n <= 0) {
            return;
        }

        //初始化前n是选号位,缺省都是第一个
        for (int i = 0; i < n; i++) {
            pos[i] = 0;
        }

        //循环处理
        while (true) {
            //生成选择数据
            StringBuffer buff = new StringBuffer();
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    buff.append(sep);
                }

                buff.append(str_list[pos[i]]);
            }

            result.add(buff.toString());

            boolean is_move = false;

            //从右边起，进位
            for (int i = n - 1; i >= 0; i--) {
                pos[i]++;   //选位右移

                if (pos[i] == str_list.length)  //前位需要进位
                {
                    pos[i] = 0;

                    if (i > 0) {
                        if (pos[i - 1] + 1 < str_list.length)    //进位OK
                        {
                            pos[i - 1]++;
                            is_move = true;
                            break;
                        }
                    } else    //无法进位
                    {
                        break;
                    }
                } else    //本位+1 OK
                {
                    is_move = true;
                    break;
                }
            }

            if (!is_move)   //没有成功移位,到头了
            {
                break;
            }
        }
    }

    //允许重号组合算法
    //和乐透组合区别 选号位可以重复
    /*
     * 实际移位
     *   30000
     *   21000
     *   20100
     *   20010
     *   20001
     *   12000
     *   11100
     *   11010
     *   11001
     *   10200
     *   10110
     *   10101
     *   10020
     *   10011
     *   03000
     *   02100
     *   02010
     *   .......
     */
    public static void GenRepCom(String srcStr, String sep, int n, List<String> result) {
        String[] str_list = srcStr.split(sep);

        //选号位
        int[] pos = new int[n];

        //选不出来
        if (str_list.length < n || str_list.length <= 0 || n <= 0) {
            return;
        }

        //初始化首位是选号位
        for (int i = 0; i < n; i++) {
            pos[i] = 0;
        }

        //循环处理
        while (true) {
            //生成选择数据
            StringBuffer buff = new StringBuffer();
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    buff.append(sep);
                }

                buff.append(str_list[pos[i]]);
            }

            result.add(buff.toString());    //生成号码

            //进位
            //从选号位最右边起，进位
            boolean is_move = false;

            for (int i = n - 1; i >= 0; i--) {
                if (pos[i] < (str_list.length - 1))    //可以进位
                {
                    pos[i]++;   //选位右移

                    //所有右边的选号全部归位
                    //归至相同位置
                    for (int k = i + 1; k < n; ++k) {
                        pos[k] = pos[i];
                    }
                    is_move = true;

                    break;
                }
            }

            if (!is_move)   //没有成功移位,到头了
            {
                break;
            }
        }
    }

    //求阶乘
    private static int factorial(int n) {
        int res = 1;
        while (n >= 2) res = res * (n--);
        return res;
    }

    //乐透型概率的倒数, 总几开几选几
    //周期
    public static float circle(int zong, int kai, int xuan) {
        if (xuan > zong) {
            return 0;
        }

        if (xuan <= kai) {
            return Combination(zong, xuan) / Combination(kai, xuan);
        } else {
            return Combination(zong, xuan) / Combination(zong - kai, xuan - kai);
        }
    }

    //组合算法
    private static float Combination(int Am, int An) {
        int li = 1;
        int j = 1;
        for (int i = Am - An + 1; i <= Am; i++) {
            li = li * i / (j++);
        }
        return li;
    }
}
