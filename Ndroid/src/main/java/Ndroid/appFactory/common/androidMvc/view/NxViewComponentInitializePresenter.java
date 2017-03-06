package Ndroid.appFactory.common.androidMvc.view;


import android.support.annotation.LayoutRes;

import Ndroid.appFactory.util.weakRef.NxWeakReference;

/**
 * 뷰애 대한 컴포넌트를 실제 실행하는 소비자 클래스
 * <pre>
 *     뷰에 대한 초기화 작업을 수행함
 * </pre>
 *
 * Created by Doohyun on 2017. 1. 25..
 */

public final class NxViewComponentInitializePresenter<T extends INxViewComponentInitializeAction> {

    private NxWeakReference<T> midasWeakReference;

    /**
     * 액티비티의 생명주기와 안맞을 수 있음.
     *
     * @param iMidasViewComponentAction
     */
    public NxViewComponentInitializePresenter(T iMidasViewComponentAction) {
        midasWeakReference = new NxWeakReference<>(iMidasViewComponentAction);
    }

    /**
     * View 들의 binding 목적으로 사용하는 메소드
     *
     * <pre>
     *     Binding 후 해야할 일은 이 곳에 정의!
     * </pre>
     *
     */
    public void onCreateBehavior(){
        midasWeakReference.run(INxViewComponentInitializeAction::initView);
    }

    /**
     * 지정된 Layout Id 출력
     *
     * @return
     */
    @LayoutRes
    public final int getLayoutResourceId() {
        return midasWeakReference.call(INxViewComponentInitializeAction::getLayoutResourceId);
    }
}
