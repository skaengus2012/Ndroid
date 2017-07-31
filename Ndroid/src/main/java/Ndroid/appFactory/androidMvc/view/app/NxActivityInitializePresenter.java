package Ndroid.appFactory.androidMvc.view.app;


import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import Ndroid.appFactory.androidMvc.presenter.NxPresenter;
import Ndroid.appFactory.util.weakRef.NxActivityWeakReference;
import Ndroid.appFactory.util.weakRef.NxWeakReference;

/**
 * 뷰애 대한 컴포넌트를 실제 실행하는 소비자 클래스
 * <pre>
 *     뷰에 대한 초기화 작업을 수행함
 * </pre>
 * <p>
 * Created by Doohyun on 2017. 1. 25..
 */

public final class NxActivityInitializePresenter<T extends INxActivityInitializeAction> extends NxPresenter<T> {

    private NxActivityWeakReference<Activity> nxActivityWeakReference;

    /**
     * 액티비티의 생명주기와 안맞을 수 있음.
     *
     * @param iMidasViewComponentAction
     */
    public NxActivityInitializePresenter(T iMidasViewComponentAction) {
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

    /**
     * View 들의 binding 목적으로 사용하는 메소드
     * <p>
     * <pre>
     *     Binding 후 해야할 일은 이 곳에 정의!
     * </pre>
     */
    public void onCreateBehavior() {
        run(INxActivityInitializeAction::initView);
    }

    /**
     * 지정된 Layout Id 출력
     *
     * @return
     */
    @LayoutRes
    public final int getLayoutResourceId() {
        return call(INxActivityInitializeAction::getLayoutResourceId);
    }

    /**
     * WeakReference 구현.
     *
     * @param t
     * @return
     */
    @Override
    protected NxWeakReference<T> createWeakReference(T t) {
        return new NxWeakReference<>(t);
    }
}
