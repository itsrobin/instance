package test.JavaConcurrency.overflow;

/**
 * Created by lifeng on 16/7/2.
 */
public class Client {
    public static void main(String[] args)  {
        EventSource eventSource = new EventSource();
        new ThisEscape(eventSource);
    }
}
