package Ndroid.appFactory.androidMvc.view.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

/**
 * 마이다스 액티비티 정의
 *
 * <pre>
 *     Activity 를 사용할 모든 클래스는 이 클래스를 상속받아야함
 * </pre>
 * Created by Doohyun on 2017. 1. 25..
 */
public abstract class NxActivity extends AppCompatActivity implements INxActivityInitializeAction {

    private NxActivityInitializePresenter nxViewActivityInitializePresenter;

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
        nxViewActivityInitializePresenter = new NxActivityInitializePresenter(this);
        nxViewActivityInitializePresenter.onCreateBehavior();
    }

    /**
     * String resource 를 읽어온다
     *
     * @param resourceId
     * @return
     */
    @NonNull
    public final String readString(@StringRes int resourceId){
        return nxViewActivityInitializePresenter.readString(resourceId);
    }

    /**
     * Result OK 세팅 처리.
     */
    public final void setResultOk() {
        nxViewActivityInitializePresenter.setResultOk();
    }
}
