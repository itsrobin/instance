package math.algorithm_1_3;

import java.util.*;

/**
 * @author lifeng
 * @version 2.0 on 17/1/12.
 * 详情请见
 */
public class Deque<Item> implements Iterable<Item>{

    private Node<Item> first;
    private Node<Item> last;
    private int size;


    private static class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;

        public Node(Node<Item> prev, Node<Item> next, Item item) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 创建空双向队列
     */
    public Deque() {
        first = last = null;
    }

    /**
     * 双向队列是否为空
     * @return
     */
    public boolean isEmpty() {

        return first==null;
    }

    /**
     * 双栈队列中元素的数量
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 向左端添加一个元素
     * @param item 元素
     */
    public void pushLeft(Item item) {
        final Node<Item> f = first;
        Node<Item> n = new Node<>(null,f,item);
        first = n;
        if (f == null) {
            last = n;
        } else{
            f.prev  = n;
        }
        size++;
    }

    /**
     * 向右端添加一个元素
     * @param item 元素
     */
    public void pushRight(Item item) {
        final Node<Item> l = last;
        Node<Item> n = new Node<>(l,null,item);
        last = n;
        if (l == null) {
            first = n;
        } else{
            l.next  = n;
        }
        size++;
    }

    /**
     * 从左端删除一个元素
     */
    public Item popLeft() {
        if (first == null){
            last = null;
            return null;
        } else {
            final Node<Item> f = first;
            final Node<Item> n = f.next;
            Item item = f.item;
            f.item = null;
            f.next = null;
            first = n;
            if (n == null) {
                last = null;
            } else {
                first.prev = null;
            }
            size--;
            return item;
        }

    }

    /**
     * 从右端删除一个元素
     */
    public Item popRight() {
        if (last == null){
            first = null;
            return null;
        } else {
            final Node<Item> l = last;
            final Node<Item> p = l.prev;
            Item item = l.item;
            l.item = null;
            l.prev = null;
            last = p;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
            size--;
            return item;
        }
    }

    /**
     * 根据下标寻找结点
     *
     * @param index 下标,从0开始
     * @return
     */
   private Node<Item> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<Item> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<Item> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> lastReturned;
        private Node<Item> next;
        private int nextIndex;

        DequeIterator(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }
        @Override
        public boolean hasNext() {
            return nextIndex<size;
        }

        @Override
        public Item next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {

        }
    }


    @Override
    public Iterator iterator() {
        return new DequeIterator(0);
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.pushLeft(2);
        deque.pushLeft(3);
        deque.pushLeft(4);
        deque.pushLeft(5);
        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }
}
