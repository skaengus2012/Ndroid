package Ndroid.appFactory.weakRefTest.weakRefApply;

import Ndroid.appFactory.weakRefTest.DummyModeler;
import Ndroid.appFactory.util.weakRef.NxWeakReference;

/**
 * 일을 위임하기를 좋아하는 프리젠터
 *
 * Created by Doohyun on 2017. 2. 22..
 */

public class DummyPresenter {
    private NxWeakReference<DummyActivity> mitWeakReference;

    public DummyPresenter(DummyActivity dummyActivity){
        mitWeakReference = new NxWeakReference<>(dummyActivity);
    }

    /**
     * Acitivty 가 스레드로 일을 시킨다고 가정.
     */
    public void runDelayedWork() {

        new Thread(() -> {
            try {
                DummyModeler.GetInstance().delayedRun();
                mitWeakReference.run(DummyActivity::activityAction);
            } catch (Exception e) {
                System.out.println("에러");
                e.printStackTrace();
            }
        }).start();
    }
}
