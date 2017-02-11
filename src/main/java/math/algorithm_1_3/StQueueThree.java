package math.algorithm_1_3;

/**
 * @author lifeng
 * @version 2.0 on 17/1/18.
 */
public class StQueueThree<Item> {
    private Stack a;
    private Stack b;
    private Stack c;

    public StQueueThree() {
        a = new Stack<>();
        b = a;
        c = new Stack<>();
    }

    public void enqueue(Item item) {
        c.push(item);
        b.push(c);
        c = new Stack<>();
        b.push(c);
        b = c;
    }

    public Item dequeue() {
        if (a.size() == 0) {
            return null;
        }
        c = (Stack) a.pop();
        a = (Stack) a.pop();
        Object obj = a.pop();
        a = c;
        return (Item) obj;
    }

    public static void main(String[] args) {
        StQueueThree<Integer> queue = new StQueueThree<>();
        queue.enqueue(1);
//        System.out.println(queue.dequeue());
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        for (int i = 0; i < 14; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
