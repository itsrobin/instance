package test.javaIO.file;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * Created by lifeng on 16/7/12.
 */
public class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }

    public static void main(String[] args) throws IOException {
        int g= (-1 | 3);
        System.out.println(g);
        Enumeration<URL> enums  = ClassLoader.getSystemResources("/");
        File file = new File("/Users/lifeng/Work/Code/my/Bi/Instance/target/classes/test.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        int i = 0;
        BufferedInputStream bif = new BufferedInputStream(fileInputStream,6);
        int d;
        bif.read();
        bif.mark(5);
        //bif.close();
        while ((d=bif.read()) !=-1){
            System.out.println(d);
        };
        InputStreamReader isr = new InputStreamReader(fileInputStream);

        System.out.println(isr.getEncoding());

        byte a = (byte)253;
        System.out.println(a & 0xff);
        int off=1;
        int len=2;



    }
}
