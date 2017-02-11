package math.algorithm_1_4;

/**
 * @author lifeng
 * @createTime 2017/1/30.
 * @updateTime 2017/1/30
 */
public class TestIncrease {
    public static void main(String[] args) {
        int sum=0;
        for(int i=1;i<1024;i*=2) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        System.out.println(sum);
        System.out.println(Math.pow(2,Math.ceil(Math.log(1024)/Math.log(2))));

    }

}
