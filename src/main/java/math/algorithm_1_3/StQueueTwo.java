package math.algorithm_1_3;

/**
 * @author lifeng
 * @version 2.0 on 17/1/18.
 */
public class StQueueTwo<Item> {
    private Stack<Item> a;
    private Stack<Item> b;

    public StQueueTwo() {
        a = new Stack<Item>();
        b = new Stack<Item>();
    }

    public void push(Item item) {
        b.push(item);
    }

    public Item pop() {
        //如果b内只有一个元素,直接从b冲弹出
        if (b.size() == 1) {
            return b.pop();
        }
        //如果a为空,从将b中的所有数据弹出压入栈中
        if (a.isEmpty()) {
            while (!b.isEmpty()) {
                a.push(b.pop());
            }
        }
        //再次检查a是否为空
        if (a.isEmpty()) {
            return null;
        }
        //弹出a中的数据
        return a.pop();
    }
}
