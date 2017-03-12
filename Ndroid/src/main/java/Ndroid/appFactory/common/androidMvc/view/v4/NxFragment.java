package Ndroid.appFactory.common.androidMvc.view.v4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Ndroid.appFactory.common.androidMvc.view.INxViewComponentInitializeAction;

/**
 * 마이다스 Fragment 정의
 *
 * <pre>
 *      Fragment 를 사용할 모든 클래스는 이 클래스를 상속받아야함
 * </pre>
 *
 * Created by Doohyun on 2017. 2. 5..
 */

public abstract class NxFragment extends Fragment implements INxViewComponentInitializeAction {

    private NxViewFragmentInitializePresenter nxViewFragmentInitializePresenter;

    /**
     * 공통적으로 할 일 정의
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater
                            , ViewGroup container
                            , Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutResourceId(), container, getAttachToRoot());
        nxViewFragmentInitializePresenter = new NxViewFragmentInitializePresenter(this);
        nxViewFragmentInitializePresenter.onCreateBehavior();

        return view;
    }

    /**
     * ViewGroup Root 를 사용할 것인가?
     *
     * <pre>
     *     Default 값 : false
     * </pre>
     *
     * @return
     */
    public boolean getAttachToRoot() {
        return false;
    }
}
