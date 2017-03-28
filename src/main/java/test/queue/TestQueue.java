package test.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author lifeng
 * @version 1.0 on 2017/2/21.
 */
public class TestQueue {
   static SynchronousQueue<String> queue = new SynchronousQueue<>();
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    queue.take();
                    System.out.println(123);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        t.start();

        System.out.println(queue.offer("s"));
        System.out.println(queue.offer("s"));
        System.out.println(queue.offer("s"));
        System.out.println(queue.offer("s"));
        System.out.println(queue.offer("s"));
        System.out.println(queue.offer("s"));


        System.out.println(123);

    }




}
