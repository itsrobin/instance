package util;

/**
 * Created by lifeng on 16/6/11.
 */
public class StringUtil {
    /**
     * 替换字符串中特定的字符
     * @param inString
     * @param oldPattern 要被替换的字符
     * @param newPattern 将被替换的字符
     * @return
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        //三个条件有一个不符合就返回原值
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        //位置参数
        int pos = 0; // our position in the old string
        //
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            //分割字符串
            sb.append(inString.substring(pos, index));
            //在字符串后加入新的分隔符
            sb.append(newPattern);
            //重新定位,越过之前被截掉的部分
            pos = index + patLen;
            //从pos的位置开始,找到oldPattern的位置
            index = inString.indexOf(oldPattern, pos);
        }
        //截取并补上最后一段
        sb.append(inString.substring(pos));
        // remember to append any characters to the right of a match
        return sb.toString();
    }

    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }
}
