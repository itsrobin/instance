package math.algorithm_1_3;

/**
 * @author lifeng
 * @version 2.0 on 17/1/18.
 */
public class StQueueOne<Item> {
    private Stack<Item> stack;

    public StQueueOne() {
        stack = new Stack<Item>();
    }

    public void enqueue(Item item) {
        stack.push(item);
    }

    /**
     * 递归方式先弹出所有的对象,取栈内的最后一个出队,时间复杂度为O(n)
     * @return
     */
    public Item dequeue() {
        if (stack.size() == 1) {
            return stack.pop();
        }
        else{
            Item p = stack.pop();
            Item rest = this.dequeue();
            stack.push(p);
            return rest;
        }
    }

}
