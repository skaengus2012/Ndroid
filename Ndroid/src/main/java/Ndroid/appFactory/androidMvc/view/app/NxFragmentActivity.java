package Ndroid.appFactory.androidMvc.view.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;

import Ndroid.appFactory.androidMvc.view.INxViewComponentInitializeAction;

/**
 * 마이다스 액티비티 정의
 * <p>
 * <pre>
 *     FragmentActivity 를 사용할 모든 클래스는 이 클래스를 상속받아야함
 * </pre>
 * <p>
 * Created by Doohyun on 2017. 1. 25..
 */
public abstract class NxFragmentActivity extends FragmentActivity implements INxViewComponentInitializeAction {

    private NxViewActivityInitializePresenter nxViewActivityInitializePresenter;

    /**
     * 모든 액티비티는 이 메소드를 확장해야함
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        nxViewActivityInitializePresenter = new NxViewActivityInitializePresenter(this);
        nxViewActivityInitializePresenter.onCreateBehavior();
    }

    /**
     * String resource 를 읽어온다
     *
     * @param resourceId
     * @return
     */
    @NonNull
    public String readString(@StringRes int resourceId) {
        return nxViewActivityInitializePresenter.readString(resourceId);
    }
}
