package test.JavaConcurrency.overflow;

import java.awt.*;

/**
 * Created by lifeng on 16/7/2.
 */
public class ThisEscape {
    private String name = "Test";

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                doSomething(event);
            }
        });
    }

    public void doSomething(Event event) {
        System.out.println(name.toString());
    }
}
