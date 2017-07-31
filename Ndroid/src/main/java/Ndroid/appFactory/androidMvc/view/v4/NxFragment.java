package Ndroid.appFactory.androidMvc.view.v4;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * NXFragment 정의
 *
 * <pre>
 *      Fragment 를 사용할 모든 클래스는 이 클래스를 상속받아야함
 * </pre>
 *
 * Created by Doohyun on 2017. 2. 5..
 */

public abstract class NxFragment extends Fragment {

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
        initView(view);

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

    /**
     * View 들에 대한 binding 작업 수행
     *
     * @param view
     */
    public abstract void initView(View view);

    /**
     * 레이아웃 리소스 id 명세
     *
     * @return
     */
    @LayoutRes
    public abstract int getLayoutResourceId();
}
