package test.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @author lifeng
 * @version 2.0 on 16/10/24.
 */
public class TestPhantom<T> extends  PhantomReference{
        public static boolean isRun = true;

    /**
     * Creates a new phantom reference that refers to the given object and
     * is registered with the given queue.
     * <p>
     * <p> It is possible to create a phantom reference with a <tt>null</tt>
     * queue, but such a reference is completely useless: Its <tt>get</tt>
     * method will always return null and, since it does not have a queue, it
     * will never be enqueued.
     *
     * @param referent the object the new phantom reference will refer to
     * @param q        the queue with which the reference is to be registered,
     */
    public TestPhantom(Object referent, ReferenceQueue q) {
        super(referent, q);
    }

    public static void main(String[] args) throws Exception {
            String abc = new String("abc");
            final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
            new Thread() {
                public void run() {
                    while (isRun) {
                        Reference reference = referenceQueue.poll();
                        if (reference != null) {
                           Object s =  reference.get();
//                            try {
//                                Field rereferent = Reference.class
//                                        .getDeclaredField("referent");
//                                rereferent.setAccessible(true);
//                                Object result = rereferent.get(reference);
//                                System.out.println("gc will collect："
//                                        + result.getClass() + "@"
//                                        + result.hashCode() + "\t"
//                                        + (String) result);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            System.out.println(s);
                        }
                    }
                }
            }.start();
//            SoftReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
            abc = null;
            Thread.currentThread().sleep(3000);
            System.gc();
            Thread.currentThread().sleep(3000);
            isRun = false;
        }
    }
