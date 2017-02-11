package math.algorithm_1_3;

/**
 * @author lifeng
 * @version 2.0 on 17/1/15.
 */
public class RingBuffer<Item> {

    private Item[] array;
    //使用volatile,保持start和end的可见性
    private volatile int start;
    private volatile int end;

    public RingBuffer() {
        this(4);
    }

    public RingBuffer(int size) {
        start = end = 0;
        array = newArray(size);
    }

    @SuppressWarnings("unchecked")
    private Item[] newArray(int size) {
        return (Item[]) new Object[size];
    }

    /**
     * 写入缓冲区
     * 每次写入前判断缓冲区是否已满.
     * 缓冲区已满时返回false
     *
     * @param item
     * @return
     */
    public boolean write(Item item) {
        if (isFull()) {
            return false;
        }

        array[end] = item;
        end = (end + 1) % array.length;
        return true;
    }

    //取余法判断是否写满缓冲区
    public boolean isFull() {
        return (end + 1) % array.length == start;
    }


    public boolean isEmpty() {
        return end == start;
    }


    /**
     * 读取缓冲区
     * 每次读取时判断缓冲区是否为空
     * 缓冲区为空时返回null
     *
     * @return
     */
    public Item read() {
        if (isEmpty()) return null;

        Item item = array[start];
        start = (start + 1) % array.length;
        return item;


    }


    //测试用例
    public static void main(String[] args) throws InterruptedException {
        final RingBuffer<Integer> queue = new RingBuffer<>(4);

        //b负责读取,a负责写入.两者互不干扰.
        Thread b = new Thread() {
            @Override
            public void run() {

                while (true) {
                    Integer i;
                    if ((i = queue.read()) != null) {
                        System.out.println(i);
                    }


                }
            }
        };


        Thread a = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {

                    while (true) {

                        if (queue.write(i)) {
                            System.out.println("写入" + i);
                            break;
                        }
                    }
                }


            }

        };

        a.start();
        b.start();


    }
}
