package test.testVolatile;

import java.io.*;

/**
 * Created by lifeng on 16/7/28.
 */
public class Value {
     InputStream in = new InputStream() {
         @Override
         public int read() throws IOException {
             return 0;
         }
     };

    public Value() throws FileNotFoundException {
    }

    public void getValue() {
        int b=123;
        int a = b;
        b=1;
        System.out.println(a);
        InputStream in  = this.in;
        this.in = null;
        if (in != null){
            System.out.println(123);
        }

    }
    public static void main(String[] args) throws FileNotFoundException {
        Value value = new Value();
        value.getValue();

    }
}
