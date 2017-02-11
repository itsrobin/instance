package math.algorithm_1_3;

import java.util.Iterator;

/**
 * @author lifeng
 * @version 2.0 on 17/1/4.
 */
public class Stack<Item> implements Iterable<Item> {
    private Item[] array;

    private int size;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        array = (Item[]) new Object[capacity];
    }

    /**
     * 默认的初始化大小
     */
    public Stack() {
        this(50);
    }

    /**
     * 入栈操作
     *
     * @param item 泛型对象
     * @return Item
     */
    public Item push(Item item) {
        array[size++] = item;
        return item;
    }

    /**
     * 出栈操作
     *
     * @return Item
     */
    public Item pop() {
        Item item = peek();
        if (item != null) {
            array[--size] = null;
        }
        return item;
    }

    /**
     * 判断栈是否已满
     *
     * @return boolean
     */
    public boolean isFull() {
        return size == array.length;
    }

    /**
     * 判断栈是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回栈顶对象的引用
     *
     * @return Item
     */
    public Item peek() {
        if (size == 0) return null;
        return array[size - 1];
    }

    /**
     * 返回当前栈的大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 根据给出的stack,复制并返回一个新的栈
     *
     * @param stack  本源数据
     * @param <Item> 泛型
     * @return new stack
     */
    public static <Item> Stack<Item> copy(Stack<Item> stack) {
        int size = stack.size();
        Stack<Item> temp = new Stack<Item>(size);
        for (Item item : stack) {
            temp.push(item);
        }
        Stack<Item> result = new Stack<Item>(size);
        for (Item item : temp) {
            result.push(item);
        }
        return result;
    }

    /**
     * @return 迭代器
     */
    public Iterator<Item> iterator() {
        return new StackIterator();
    }


    private class StackIterator implements Iterator<Item> {

        //栈中的数据是从数组的右边进,右边出.
        private int i = size;


        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return array[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        for (int i = array.length; i > 0; i--) {
//           Item item = array[i - 1];
//            if (item !=null) {
//                builder.append(item.toString()).append(" ");
//            }
//        }
//        return builder.toString();
//    }

}
