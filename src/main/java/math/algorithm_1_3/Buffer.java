package math.algorithm_1_3;

/**
 * @author lifeng
 * @version 2.0 on 17/1/17.
 */
public class Buffer {
    private Stack<Character> ready;
    private Stack<Character> backup;

    public Buffer() {
        ready = new Stack<>();
        backup = new Stack<>();
    }

    /**
     * 在光标位置插入字符c
     *
     * @param c
     */
    public void insert(char c) {
        ready.push(c);
    }

    /**
     * 删除并返回光标位置的字符
     *
     * @return 光标位置的字符
     */
    public Character delete() {
        Character ch = ready.pop();
        return ch;
    }

    /**
     * 将光标向左移动k个位置
     *
     * @param k
     */
    public void left(int k) {
        for (int i = 0; i < k; i++) {
            backup.push(ready.pop());

        }
    }

    /**
     * 将光标向右移动k个位置
     *
     * @param k
     */
    public void right(int k) {
        for (int i = 0; i < k; i++) {
            ready.push(backup.pop());
        }
    }

    public String toString() {
        StringBuilder readReady = new StringBuilder();
        for (Character character : ready) {
            readReady.append(character);
        }
        readReady.append("|");

        StringBuilder readBackUp = new StringBuilder();
        for (Character character : backup) {
            readBackUp.append(character);
        }
        readBackUp.reverse();


        return readReady.append(readBackUp).toString();
    }


}
