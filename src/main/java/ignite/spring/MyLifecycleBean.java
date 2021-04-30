package ignite.spring;

import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;

public class MyLifecycleBean implements LifecycleBean {
    @Override public void onLifecycleEvent(LifecycleEventType evt) {
        if (evt == LifecycleEventType.BEFORE_NODE_START) {
            System.out.println("inside lifecycle bean: BEFORE_NODE_START");
        }
        if (evt == LifecycleEventType.AFTER_NODE_STOP) {
            System.out.println("inside lifecycle bean: AFTER_NODE_STOP");
        }
    }
}