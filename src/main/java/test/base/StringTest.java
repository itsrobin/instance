package test.base;

/**
 * Created by lifeng on 16/6/11.
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "/Users/lifeng/Movies/eclipse/configuration/org.eclipse.equinox.simpleconfigurator/bundles.info";
        System.out.println(s.indexOf('/',6));
        System.out.println(s.substring(s.indexOf('/',6)));
    }


}
