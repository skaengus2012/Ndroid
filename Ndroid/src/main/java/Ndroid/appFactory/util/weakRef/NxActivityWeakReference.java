package Ndroid.appFactory.util.weakRef;

import android.app.Activity;

import java.lang.ref.ReferenceQueue;

import Ndroid.appFactory.common.function.IConsumer;

/**
 * Activity 전용 약한참조 객체
 *
 * Created by Doohyun on 2017. 1. 28..
 */

public class NxActivityWeakReference<T extends Activity> extends NxWeakReference<T> {
    /**
     * 단순한 상속에 대한 생성자 제작
     * @param referent
     */
    public NxActivityWeakReference(T referent) {
        super(referent);
    }

    /**
     * 단순한 상속에 대한 생성자 제작
     *
     * @param referent
     * @param q
     */
    public NxActivityWeakReference(T referent, ReferenceQueue<T> q) {
        super(referent, q);
    }

    /**
     * Activity 에서 runOnUiThread 로 실행함
     *
     * @param weakReferenceConsumer
     */
    public void runOnUiThread(IConsumer<T> weakReferenceConsumer) {
        final T activity = this.get();

        if (activity != null) {
            activity.runOnUiThread(() -> {
                weakReferenceConsumer.accept(activity);
            });
        }
    }
}
