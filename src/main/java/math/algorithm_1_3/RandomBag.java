package math.algorithm_1_3;

import com.algs4.stdlib.StdRandom;

import java.util.Iterator;

/**
 * @author lifeng
 * @version 2.0 on 17/1/13.
 */
public class RandomBag<Item> implements Iterable<Item>{

    private int size;

    private Item[] array;

    public RandomBag() {
        this(10);
    }

    private RandomBag(int size) {
        array = newArray(size);
    }

    @SuppressWarnings("unchecked")
    private Item[] newArray(int size) {
        return (Item[]) new Object[size];
    }

    public boolean isEmpty() {
        return size > 0;
    }

    public int size() {
        return size;
    }

    public void add(Item item) {
        array[size++] = item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<Item> {
        int i;
         Item[] copy;

        BagIterator() {
            copy = array.clone();
            shuffle(copy);
        }

        /**
         * 打乱数组顺序
         * @param array
         */
        private void shuffle(Item[] array) {
            for (int j = 0; j < size; j++) {
                int exchange = j+ StdRandom.uniform(size - j);
                Item temp = array[j];
                array[j] = array[exchange];
                array[exchange] = temp;
            }
        }
        @Override
        public boolean hasNext() {
            return i<size;
        }

        @Override
        public Item next() {
            return copy[i++];
        }
    }

    //测试
    public static void main(String[] args) {
        RandomBag<Integer> bag = new RandomBag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        for (Integer integer : bag) {
            System.out.println(integer);
        }
    }
}
