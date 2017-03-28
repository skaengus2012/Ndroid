package Ndroid.appFactory.androidMvc.view.app;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import Ndroid.appFactory.androidMvc.view.INxViewComponentInitializeAction;
import Ndroid.appFactory.androidMvc.view.NxViewComponentInitializePresenter;
import Ndroid.appFactory.util.weakRef.NxActivityWeakReference;

/**
 * Activity 가 해야할 행위 공통정의
 * <p>
 * Created by Doohyun on 2017. 2. 18..
 */

public final class NxViewActivityInitializePresenter<T extends INxViewComponentInitializeAction>
        extends NxViewComponentInitializePresenter<T> {

    private NxActivityWeakReference<Activity> nxActivityWeakReference;

    public NxViewActivityInitializePresenter(@NonNull T iMidasViewComponentAction) {
        super(iMidasViewComponentAction);

        if (iMidasViewComponentAction instanceof Activity) {
            nxActivityWeakReference = new NxActivityWeakReference<>((Activity) iMidasViewComponentAction);
        } else {
            throw new RuntimeException("[error] : Activity input required.");
        }
    }

    /**
     * String resId 출력!
     *
     * @param resId
     * @return
     */
    @NonNull
    public String readString(@StringRes final int resId) {
        return nxActivityWeakReference.call(activity -> activity.getApplicationContext().getString(resId), "");
    }

    /**
     * Setting RESULT OK
     */
    public void setResultOk() {
        nxActivityWeakReference.runOnUiThread(activity -> activity.setResult(Activity.RESULT_OK));
    }
}
