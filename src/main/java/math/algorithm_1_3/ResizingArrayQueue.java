package math.algorithm_1_3;

import com.algs4.stdlib.StdIn;

import java.util.Iterator;

/**
 * @author lifeng
 * @version 2.0 on 17/1/9.
 */
public class ResizingArrayQueue<Item> implements Iterable<Item>{
    private Item[] array;
    private int head;
    private int tail;

    public ResizingArrayQueue(int capacity) {
        head = tail = 0;
        array = newArray(capacity);
    }

    public ResizingArrayQueue() {
        this(8);
    }


    /**
     * 将原有数组复制到全新的数组中
     *
     * @param newSize 新数组的大小
     */
    public void resize(int newSize) {

        Item[] newArray = newArray(newSize);
        resize(newArray, 0);

    }

    /**
     * 将原有数组复制到全新的数组中,需要指定数组在新数组的开始位置
     *
     * @param newArray 全新的数组
     * @param start    数据项开始复制的下标
     */
    public void resize(Item[] newArray, int start) {

        for (int i = head; i < tail; i++) {
            newArray[start++] = array[i];
        }
        array = newArray;
        head = 0;
        tail = start;
    }

    @SuppressWarnings("unchecked")
    private Item[] newArray(int size) {
        return (Item[]) new Object[size];
    }

    public void enqueue(Item item) {
        array[tail++] = item;
        if (tail == array.length) {
            resize(array.length << 1);
        }
    }

    public Item dequeue() {
        if (head == tail) {
            return null;
        }
        Item item = array[head];
        array[head] = null;
        head++;

        if (head == tail) {
            head = tail = 0;
        }
        if (array.length >>2 >= 1 && size() == array.length >>2) {
            resize(array.length >> 1);
        }

        return item;
    }

    public int size() {
        return tail - head;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 从左边插入数据开销比较大
     *
     * 有三种情况
     *  1.之前有出列操作,所以可以很轻松将元素加入数组首部
     *  数组首部已有元素,pushLeft需要将数组内的元素都右移
     *      2.新数组长度不变,调用resize进行右移操作
     *      3.如果达到将要扩容的阶段,那么将新数组长度增大,再执行右移操作
     * @param item
     */
    public void pushLeft(Item item) {

        //如果之前有出列操作,那么head肯定不为0,也不需要将数组内的其他元素向右移位
        if (head != 0) {
            array[--head] = item;

        } else {//其他元素必须向右移位
            Item[] newArray;
            int newSize = 0;
            //达到即将扩容的阶段,新数组的长度应该翻倍
            if (size() + 1 == array.length) {
                newSize = array.length << 1;
            } else {
                //正常不变
                newSize = array.length;
            }
            newArray = newArray(newSize);
            //第一项就是添加的元素
            newArray[0] = item;
            resize(newArray, 1);
        }


        if (size() == array.length) {
            resize(2 * array.length);
        }


        //进入即将扩容阶段,需要扩容
        if (size() - 1 == array.length) {
        }
    }

    public void pushRight(Item item) {
        enqueue(item);
    }

    public Item popLeft() {
        return dequeue();
    }

    public Item popRight() {
        if (tail ==head) {
            return null;
        }
        Item item = array[--tail];
       array[tail] = null;
        if (head == tail) {
            head = tail = 0;
        }
        if (array.length >>2 >= 1 && size() == array.length >>2) {
            resize(array.length >> 1);
        }
        return item;
    }

    public Item delete(int k) {
        if (k > size()) {
            throw new IndexOutOfBoundsException("没有这个元素");
        }

        int index = head+k;
        Item item = array[index];
        //将元素左移
        for (int i = index; i < tail-1; i++) {
            array[i] = array[i+1];
        }
        array[--tail] = null;
        return item;
    }

    //测试
    public static void main(String[] args) {
        int n = 9;
        int k = 7;
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<>();
        for (int i = 0; i < n; i++) {
            queue.enqueue(StdIn.readString());
        }
        queue.pushLeft("0");
        queue.pushLeft("-1");

        for (int i = 0; i < n; i++) {
            String s = queue.dequeue();
            if (i == n - k) {
                System.out.println(s);
                break;
            }
        }

        queue.pushLeft("xx");
        queue.pushRight("xx");

        queue.popLeft();
        queue.popRight();
        queue.popRight();
        queue.popRight();
        queue.popRight();

        for (String s : queue) {
            System.out.println(s);
        }

    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<Item> {
        int i = head;
        @Override
        public boolean hasNext() {
            return i<tail;
        }

        @Override
        public Item next() {
            return array[i++];
        }
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public Item[] getArray() {
        return array;
    }
}
