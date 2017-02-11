package search;

import com.algs4.stdlib.StdRandom;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifeng on 16/6/19.
 */
public class BinarySearch {

    public static int rank(int key, int[] a) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int midInt = (low + high) / 2;
            if (key < a[midInt]) high = midInt - 1;
            else if (key > a[midInt]) low = midInt + 1;
            else {
                while (key == a[midInt]) {
                    midInt += 1;
                }
                return --midInt;

            }
        }

        return -1;
    }


    private static Map<Integer, Integer> hash(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            Integer num = null;
            if ((num = map.get(i)) == null) {
                map.put(i, 1);
            } else {
                num += 1;
                map.put(i, num);
            }
        }
        return map;
    }

    public static int count(int[] a, int key) {
        Map<Integer, Integer> map = hash(a);
        Integer num = map.get(key);
        if (num == null) return 0;
        return num;
    }

    public static void erXiangShi(String[] args) {
        int[] array = new int[]{1000,10000,100000,1000000};
        double T = 1000;



            for (int N : array) {
                double num =0;
                int[] a = new int[N];
                int[] b = new int[N];

                for (int i = 0; i < N; i++) {
                    a[i] = 100000 + StdRandom.uniform(900000);
                    b[i] = 100000 + StdRandom.uniform(900000);
                }

                for (int j = 0; j < T; j++) {
                for (int i : a) {
                    int index = rank(i, b);
                    if (index != -1) {
                        num++;
                    }
                }
            }
                System.out.println("N:"+N+" avg:"+num/T);
        }

    }
}
