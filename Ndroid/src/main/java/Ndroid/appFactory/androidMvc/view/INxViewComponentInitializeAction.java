package Ndroid.appFactory.androidMvc.view;

import android.support.annotation.LayoutRes;

/**
 *  안드로이드의 뷰 관련 컴포넌트들이 해야할 목록 정의
 *  Created by Doohyun on 2017. 1. 25..
 */

public interface INxViewComponentInitializeAction {

    /**
     * View 들에 대한 binding 작업 수행
     */
    void initView();

    /**
     * 레이아웃 리소스 id 명세
     *
     * @return
     */
    @LayoutRes
    int getLayoutResourceId();
}
