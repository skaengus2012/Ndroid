package Ndroid.appFactory.common.androidMvc.view.v4;


import android.support.v4.app.Fragment;

import Ndroid.appFactory.common.androidMvc.view.INxViewComponentInitializeAction;
import Ndroid.appFactory.common.androidMvc.view.NxViewComponentInitializePresenter;

/**
 * Created by Doohyun on 2017. 3. 12..
 */

public class NxViewFragmentInitializePresenter<T extends INxViewComponentInitializeAction>
            extends NxViewComponentInitializePresenter<T>{

    public NxViewFragmentInitializePresenter(T t) {
        super(t);

        if (!(t instanceof Fragment)) {
            throw new RuntimeException("[error] : Fragment input required.");
        }
    }
}
