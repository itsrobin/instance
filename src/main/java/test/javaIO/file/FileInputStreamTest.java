package test.javaIO.file;

import java.io.*;

/**
 * Created by lifeng on 16/8/6.
 */
public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream a = new FileInputStream("/Users/lifeng/Work/Code/my/Bi/Instance/target/classes/test.txt");
        File file = new File("/Users/lifeng/Work/Code/my/Bi/Instance/target/classes/test.txt");
        FileInputStream b = new FileInputStream(file);
        BufferedInputStream bif = new BufferedInputStream(b);
        int f;
        byte[] byteArray2 = new byte[bif.available()];
        int i =0;
        while ((bif.read(byteArray2)) != -1) {
            System.out.println(byteArray2[i++]);
            System.out.println(bif.available());
        }


        byte[] byteArray = new byte[8];
        int returnByte = a.read();  //返回实际读取的字节
        int readNumber = a.read(byteArray); //返回读取并写入了多少字节
         readNumber = a.read(byteArray); //返回读取并写入了多少字节
        int readNumber2 = a.read(byteArray,0,byteArray.length); //返回读取并写入了多少字节
    }
}

