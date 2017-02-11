package math.algorithm_1_4;

/**
 * @author lifeng
 * @version 2.0 on 17/1/20.
 */
public class TestOverflow {

    public static void main(String[] args) {
        int i = Integer.MAX_VALUE;
        int n = Integer.MAX_VALUE;
        int r = Integer.MAX_VALUE;

        System.out.println((long)i+n+r);
    }
}
