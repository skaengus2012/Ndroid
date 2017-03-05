package Ndroid.appFactory.common.androidMvc.viewComponent.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import Ndroid.appFactory.common.androidMvc.viewComponent.INxViewComponentInitializeAction;
import Ndroid.appFactory.common.androidMvc.viewComponent.NxViewComponentInitializePresenter;
import Ndroid.appFactory.util.weakRef.NxActivityWeakReference;

/**
 * Activity 가 해야할 행위 공통정의
 *
 * Created by Doohyun on 2017. 2. 18..
 */

public final class NxViewCommonActionViewModel<T extends Activity> {

    private NxViewComponentInitializePresenter mitViewComponentInitializePresenter;
    private NxActivityWeakReference<T> midasWeakReference;

    public NxViewCommonActionViewModel(T iMidasViewComponentAction) {
        midasWeakReference = new NxActivityWeakReference<>(iMidasViewComponentAction);

        if (iMidasViewComponentAction instanceof INxViewComponentInitializeAction) {
            mitViewComponentInitializePresenter = new NxViewComponentInitializePresenter(
                    (INxViewComponentInitializeAction) iMidasViewComponentAction);
        } else {
            throw new RuntimeException("[에러] IMitViewComponentInitializeAction 의 구현체가 들어오지 않음.");
        }
    }

    /**
     * onCreate 시, 해야할 일 정의.
     */
    public void onCreate(){
        midasWeakReference.runOnUiThread(activity -> {
            activity.setContentView(mitViewComponentInitializePresenter.getLayoutResourceId());
            mitViewComponentInitializePresenter.onCreateBehavior();
        });
    }

    /**
     * String resId 출력!
     * @param resId
     * @return
     */
    @NonNull
    public String readString(@StringRes final int resId) {
        final String applicationStr =  midasWeakReference.call(activity -> activity.getApplicationContext().getString(resId));

        if (applicationStr == null) {
            return "";
        } else {
            return applicationStr;
        }
    }
}
