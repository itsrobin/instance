package math.algorithm_1_3;

/**
 * 通过单向链表实现
 */
public class Steque<Item> {

    private int size;

    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Node<Item> next, Item item) {
            this.item = item;
            this.next = next;
        }

        public Node(Item item) {
            this.item = item;
        }
    }

    /**
     * 从单向链表头部加入
     * @param item
     */
    public void push(Item item) {
        Node<Item> newNode = new Node<>(item);
        if (first == null) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    /**
     * 从单向链表头部弹出
     * @return
     */
    public Item pop() {
        if (first == null) {
            return null;
        } else{
            Node<Item> node = first;
            Node<Item> next = first.next;
            if (first == last) {
                last = next;
            }
            first = next;
            size--;
            return node.item;
        }
    }

    /**
     * 从单向链表尾部加入
     */
    public void enqueue(Item item) {
        Node<Item> newNode = new Node<>(item);
        if (last == null) {
            last = first = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public static void main(String[] args) {
        Steque<Integer> a = new Steque<>();
        a.push(4);
        a.push(3);
        a.push(2);
        a.push(1);
        System.out.println(a.pop());
        a.push(5);
    }
}
