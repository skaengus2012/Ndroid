package Ndroid.appFactory.common.androidMvc.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import Ndroid.appFactory.common.function.IConsumer;
import Ndroid.appFactory.util.weakRef.NxActivityWeakReference;
import Ndroid.appFactory.util.weakRef.NxWeakReference;


/**
 * Activity 전용 weakReference 를 사용하는 Presenter
 *
 * Created by Doohyun on 2017. 2. 12..
 */

public abstract class NxActivityPresenter<T extends Activity> extends NxPresenter<T> {

    public NxActivityPresenter(@NonNull T activity) {
        super(activity);
    }

    /**
     * Activity 전용 형변환자 사용
     *
     * @param activity
     * @return
     */
    protected final NxWeakReference<T> createWeakReference(@NonNull T activity) {
        NullCheck(activity);
        return new NxActivityWeakReference<>(activity);
    }

    /**
     * UI thread 에서 일을 시키기 위한 consumer 정의.
     *
     * @param consumer
     */
    public final void runOnUiThread(@NonNull IConsumer<T> consumer) {
        NullCheck(consumer);
        ((NxActivityWeakReference) this.getWeakRef()).runOnUiThread(consumer);
    }

    /**
     * <pre>
     *  Activity 의 Back Button 누를 시,
     *  뒤로가기를 수행하도록 처리
     * </pre>
     *
     *  @param view
     */
    public void onBackPressed(@NonNull View view) {
        runOnUiThread(Activity::onBackPressed);
    }
}
