package test.java.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by lifeng on 16/9/4.
 */
public class TestNio {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("/Users/lifeng/Work/Code/my/Bi/Instance/src/main/resources/test.txt");
        FileChannel fc = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate( 1024 );

        int r = fc.read( buffer );
        buffer.flip();
        //int b = buffer.get();
        buffer.slice();
        byte[] a = new byte[]{1,2,3,4,5,123,49,32,54};
        buffer.get(a);
        FileOutputStream fOut = new FileOutputStream("test2");
        FileChannel fc2 = fOut.getChannel();
        buffer.flip();
        fc2.write(buffer);

    }
}
