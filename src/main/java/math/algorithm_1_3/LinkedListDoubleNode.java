package math.algorithm_1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author lifeng
 * @version 2.0 on 17/1/11.
 */
public class LinkedListDoubleNode<Item> implements Iterable<Item> {
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

    private Node<Item> first;
    private Node<Item> last;

    public LinkedListDoubleNode() {
        first = last = null;
    }

    public LinkedListDoubleNode(LinkedListDoubleNode<Item> copyFrom) {
        for (Item item : copyFrom) {
            this.linkLast(item);
        }
    }

    /**
     * 从首部添加
     *
     * @param item 结点内容
     */
    public void linkFirst(Item item) {
        final Node<Item> f = first;

        Node<Item> node = new Node<Item>(null, f, item);
        first = node;
        if (f == null) {
            last = node;
        } else {
            f.prev = node;
        }
        size++;
    }

    /**
     * 从尾部添加
     *
     * @param item 结点内容
     */
    public void linkLast(Item item) {
        final Node l = last;

        Node<Item> node = new Node<Item>(l, null, item);
        last = node;
        if (l == null) {
            first = node;
        } else {
            l.next = node;
        }
        size++;
    }

    /**
     * 卸下首部结点
     *
     * @return 移除结点的内容
     */
    public Item unlinkFirst() {
        if (first == null) {
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
     * 从首部开始寻找对应的结点并移除
     *
     * @param o 结点的内容
     * @return 是否移除成功
     */
    public boolean unlinkFromFirst(Object o) {
        return remove(o);
    }

    /**
     * 从尾部开始寻找对应的结点并移除
     *
     * @param o 结点的内容
     * @return 是否移除成功
     */
    public boolean unlinkFromLast(Object o) {
        if (o == null) {
            for (Node<Item> n = last; n != null; n = n.prev) {
                if (n.item == null) {
                    unlink(n);
                    return true;
                }
            }
        } else {
            for (Node<Item> n = last; n != null; n = n.prev) {
                if (o.equals(n.item)) {
                    unlink(n);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 移除尾部结点
     *
     * @return 被移除结点的内容
     */
    public Item unlinkLast() {
        if (last == null) {
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
     * 在指定结点之前插入新结点
     *
     * @param n    指定结点
     * @param item 新节点内的对象
     */
    public void linkBefore(Node n, Item item) {
        Node before = n.prev;
        Node newNode = new Node<Item>(before, n, item);
        before.next = newNode;
        n.prev = newNode;
        size++;
    }

    /**
     * 在指定结点之后插入新结点
     *
     * @param n    指定结点
     * @param item 新结点内的对象
     */
    public void linkAfter(Node n, Item item) {
        Node after = n.next;
        Node newNode = new Node<Item>(n, after, item);
        after.prev = newNode;
        n.next = newNode;
        size++;
    }

    /**
     * 删除指定结点,根据o是否为null区别判断
     *
     * @param o 指定结点内的对象
     * @return 是否移除成功
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<Item> n = first; n != null; n = n.next) {
                if (n.item == null) {
                    unlink(n);
                    return true;
                }
            }
        } else {
            for (Node<Item> n = first; n != null; n = n.next) {
                if (o.equals(n.item)) {
                    unlink(n);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 断开指定结点与其他结点的连接
     *
     * @param node
     */
    private void unlink(Node<Item> node) {
        final Node<Item> prev = node.prev;
        final Node<Item> next = node.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.item = null;
        size--;
    }

    /**
     * 根据下标寻找结点
     *
     * @param index 下标,从0开始
     * @return
     */
    public Node<Item> node(int index) {
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

    public Item delete(int k) {
        if (k > size - 1) {
            throw new NoSuchElementException();
        }
        Item item = node(k).item;
        if (remove(item)) {
            return item;
        }
        return null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(0);
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> lastReturned;
        private Node<Item> next;
        private int nextIndex;

        ListIterator(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Item next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

    }

    public static void main(String[] args) {
        LinkedListDoubleNode<Integer> a = new LinkedListDoubleNode<>();
        a.linkFirst(5);
        a.unlinkFirst();
//        a.linkFirst(3);
//        a.linkFirst(1);
//        a.linkLast(7);
//        a.linkLast(8);
//        a.linkBefore(a.node(1),2);
//        a.linkAfter(a.node(2),4);
//        a.unlinkFromFirst(1);
//        a.unlinkFromLast(8);
//
    }
}
