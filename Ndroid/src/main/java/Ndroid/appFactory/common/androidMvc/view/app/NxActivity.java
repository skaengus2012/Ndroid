package Ndroid.appFactory.common.androidMvc.view.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import Ndroid.appFactory.common.androidMvc.view.INxViewComponentInitializeAction;

/**
 * 마이다스 액티비티 정의
 *
 * <pre>
 *     Activity 를 사용할 모든 클래스는 이 클래스를 상속받아야함
 * </pre>
 * Created by Doohyun on 2017. 1. 25..
 */
public abstract class NxActivity extends AppCompatActivity implements INxViewComponentInitializeAction {

    private NxViewActivityInitializePresenter nxViewActivityInitializePresenter;

    /**
     * 모든 액티비티는 이 메소드를 확장해야함
     *
     * @param savedInstanceState
     *
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
    public String readString(@StringRes int resourceId){
        return nxViewActivityInitializePresenter.readString(resourceId);
    }
}
