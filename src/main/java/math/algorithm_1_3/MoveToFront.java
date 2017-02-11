package math.algorithm_1_3;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author lifeng
 * @version 2.0 on 17/1/16.
 */
public class MoveToFront {

    /**
     * 字符串去重并返回
     * @param s
     * @return
     */
    public static String rmDuplicate(String s) {

        StringBuilder sb = new StringBuilder(s);
        Set<Character> set = noRepeat(s);
        for (Character character : set) {
            sb.append(character);
        }

        return sb.toString();
    }

    /**
     *利用set的特性去重,返回的set内的字符串是不重复的
     * @param s
     * @return
     */
    public static Set<Character> noRepeat(String s) {
        Set<Character> set = new LinkedHashSet<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set;
    }



    public static void main(String[] args) {
        LinkedListDoubleNode<String> list = new LinkedListDoubleNode<>();
        String s = "dsadasdsadsd"; //假装是输入的字符串
        for (Character character : noRepeat(s)) {
            list.linkLast(character.toString());
        }

        String b = "sadasffefe"; //后续使用的字符串
        for (int i = 0; i < b.length(); i++) {
           String str = String.valueOf(b.charAt(i));
            //如果有这个字符串的话,删除
             list.remove(str);
            //将该字符串加入头部
            list.linkFirst(str);
        }

        for (String s1 : list) {
            System.out.println(s1);
        }

    }
}
