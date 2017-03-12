package Ndroid.appFactory.common.androidMvc.view;


import android.support.annotation.LayoutRes;

import Ndroid.appFactory.common.androidMvc.presenter.NxPresenter;
import Ndroid.appFactory.util.weakRef.NxWeakReference;

/**
 * 뷰애 대한 컴포넌트를 실제 실행하는 소비자 클래스
 * <pre>
 *     뷰에 대한 초기화 작업을 수행함
 * </pre>
 * <p>
 * Created by Doohyun on 2017. 1. 25..
 */

public abstract class NxViewComponentInitializePresenter<T extends INxViewComponentInitializeAction> extends NxPresenter<T> {

    /**
     * 액티비티의 생명주기와 안맞을 수 있음.
     *
     * @param iMidasViewComponentAction
     */
    public NxViewComponentInitializePresenter(T iMidasViewComponentAction) {
        super(iMidasViewComponentAction);
    }

    /**
     * View 들의 binding 목적으로 사용하는 메소드
     * <p>
     * <pre>
     *     Binding 후 해야할 일은 이 곳에 정의!
     * </pre>
     */
    public void onCreateBehavior() {
        run(INxViewComponentInitializeAction::initView);
    }

    /**
     * 지정된 Layout Id 출력
     *
     * @return
     */
    @LayoutRes
    public final int getLayoutResourceId() {
        return call(INxViewComponentInitializeAction::getLayoutResourceId);
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
