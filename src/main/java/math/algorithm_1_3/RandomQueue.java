package math.algorithm_1_3;

import com.algs4.stdlib.StdRandom;

import java.util.Iterator;

/**
 * @author robin
 * @version 1.0 on 17/1/13.
 *          基于动态调整大小的数组实现
 */
public class RandomQueue<Item> extends ResizingArrayQueue<Item>{


    public RandomQueue() {
        super(8);
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public void enqueue(Item item) {
        super.enqueue(item);
    }


//    /**
//     * 根据随机的次数,不断地出列和入列,直到得到合适的元素
//     *
//     * @param ifReturn 最后返回的元素是否入列
//     * @return Item
//     */
//    private Item dequeue(boolean ifReturn) {
//        int t = StdRandom.uniform(1, size() + 1);
//        int n = 1;
//        Item item = super.dequeue();
//        while (n != t) {
//            enqueue(item);
//            item = super.dequeue();
//            n++;
//        }
//        if (ifReturn) {
//            enqueue(item);
//        }
//        return item;
//    }

    /**
     * 取样且不放回
     *
     * @return Item
     */
    public Item dequeue() {
        return dequeue(false);
    }

    /**
     * 取样且放回
     *
     * @return Item
     */
    public Item sample() {
        return dequeue(true);
    }

    public Item dequeue(boolean ifReturn) {
        int tail = getTail() - 1;
        int head = getHead();
        Item[] array = getArray();
        int index = StdRandom.uniform(head, tail + 1);
        Item temp = array[index];
        if (index != tail) {
            array[index] = array[tail];
            array[tail] = temp;
        }
        if (!ifReturn) {
            popRight();
        }

        return temp;
    }

    public Iterator<Item> iterator(){
    return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        int i = getHead();
        Item[] copy;

        RandomQueueIterator() {
            copy = getArray().clone();
            shuffle(copy);
        }

        /**
         * 打乱数组顺序
         * @param array
         */
        private void shuffle(Item[] array) {
            for (int j = 0; j < size(); j++) {
                int exchange = j+ StdRandom.uniform(size() - j);
                Item temp = array[j];
                array[j] = array[exchange];
                array[exchange] = temp;
            }
        }
        @Override
        public boolean hasNext() {
            return i<getTail();
        }

        @Override
        public Item next() {
            return copy[i++];
        }
    }

    public static void main(String[] args) {
        RandomQueue<Integer> queue = new RandomQueue<>();
        for (int i = 1; i < 53; i++) {
            queue.enqueue(i);
        }
        /**
         * 四个人每人发13张
         */
        int[][] a = new int[4][13];

        for (Integer integer : queue) {
            System.out.println(integer);
        }

        for (int i = 0; i < 52; i++) {
            int index_1 = i % 4;
            int index_2 = i % 13;
            Integer n = queue.dequeue();
            a[index_1][index_2] = n;
        }

//        for (int j = 0; j < a.length; j++) {
//
//            System.out.println("选手" + (j + 1) + "的牌组");
//            for (int k = 0; k < a[j].length; k++) {
//                System.out.print(a[j][k] + " ");
//            }
//            System.out.println("");
//        }
    }
}
