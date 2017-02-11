package test.javaIO.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by lifeng on 16/8/6.
 */
public class ByteArrayInputStreamTest {
    public static void main(String[] args) throws IOException {
        byte[] srcArray = new byte[1024];   //源
        byte[] destArray = new byte[512];
        ByteArrayInputStream bis = new ByteArrayInputStream(srcArray);
        int returnByte = bis.read();        //返回实际读取的字节
        int readNumber = bis.read(destArray); //返回读取并写入了多少字节
        int readNumber2 = bis.read(destArray,0,destArray.length); //返回读取并写入了多少字节
    }
}
