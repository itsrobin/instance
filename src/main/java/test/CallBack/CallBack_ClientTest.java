package test.CallBack;

/**
 * Created by lifeng on 16/5/4.
 */
public class CallBack_ClientTest {
    CallBack callBack;

    public CallBack_ClientTest(CallBack callBack) {
        this.callBack = callBack;
    }

    public void doSome(){
        callBack.executeTask();
    }

}
