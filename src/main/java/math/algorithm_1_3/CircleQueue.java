package math.algorithm_1_3;

/**
 * @author lifeng
 * @version 2.0 on 17/1/10.
 */
public class CircleQueue<Item> {

    private Node last;

    public class Node {
        Item item;
        Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    /**
     * 入列操作
     *
     * @param item 泛型对象
     */
    public void enqueue(Item item) {
        //刚开始last为空,那么将其初始化,并指向自己
        Node node = new Node(item);
        if (last == null) {
            last = node;
            last.next = last;
        } else {
            //得到last原来指向的节点,并指向新的节点
            Node origin = last.next;
            last.next = node;
            //新的节点再指向原来的节点,形成一个闭环
            node.next = origin;

            //新的节点成为last节点
            last = node;
        }
    }

    public Item dequeue() {
        //如果last为空,直接抛出异常
        if (last == null) {
            throw new NullPointerException("queue is empty");
        }
        Node first = last.next;


        if (first == last) {
            //如果first与last相等,说明last.next = last,即链表中只剩一个结点
            //将其置空
            last = null;
        } else {
            //不然更新last的后置结点,即first结点
            last.next = first.next;
        }
        return first.item;

    }



}
