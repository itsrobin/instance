package test.concurrentTest;

import java.util.concurrent.locks.LockSupport;

/**
 * @author lifeng
 * @version 2.0 on 16/11/20.
 */
public class TestLockSupport {

    public static void main(String[] args)  {
        Thread a = new Thread() {
            @Override
            public void run() {
                System.out.println("wait for permit");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                }
                LockSupport.park();
                System.out.println("block.");
            }
        };
        a.start();
        System.out.println(a.isInterrupted());
//        a.interrupt();
//        System.out.println(a.isInterrupted());
//        a.interrupt();
//
//        System.out.println(a.isInterrupted());
        a.interrupt();
        System.out.println(a.isInterrupted());
        System.out.println(a.isInterrupted());
        System.out.println(a.isInterrupted());
        System.out.println(a.isInterrupted());
        System.out.println(a.isInterrupted());
//        LockSupport.park(Thread.currentThread());


    }




}
