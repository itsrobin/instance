package test.String;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Created by lifeng on 16/8/23.
 */
public class Encrypt {
    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    public static String MD5(String str) {
        try {
            MessageDigest sec = MessageDigest.getInstance("MD5");
            sec.update(str.getBytes());
            byte[] bytes = sec.digest();
            return byteToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        MessageDigest sec = MessageDigest.getInstance("MD5");
        sec.update("hello".getBytes());
        byte[] bytes = sec.digest();
        String s = byteToHex(bytes);
        System.out.println(s);
        String b = byteToHex2(bytes);
        System.out.println(b);

    }


    public static String byteToHex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(hex[b >> 4 & 0x0f]);
            sb.append(hex[b & 0x0f]);
        }
        return sb.toString();

    }

    private static String byteToHex2(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
