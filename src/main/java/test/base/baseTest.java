package test.base;

/**
 * Created by lifeng on 16/6/11.
 */
public class baseTest {

    public static void main(String[] args) {
        byte[] buf = new byte[]{'g','o','d',' ','m','o','r','n','i','n','g'};
        System.out.println(Integer.toBinaryString((buf[1] & 0xff)));
        System.out.println(Integer.toBinaryString(buf[1]));
        System.out.println(buf[1] & 0xff);
        System.out.println(buf[1]);
    }
}
