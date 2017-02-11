package test.JavaConcurrency.overflow;

/**
 * Created by lifeng on 16/7/2.
 */
public class EventSource {
    public void registerListener(EventListener listener) {
        listener.onEvent(null);
    }
}
