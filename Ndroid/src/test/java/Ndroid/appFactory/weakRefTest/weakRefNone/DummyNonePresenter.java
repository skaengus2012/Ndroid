package Ndroid.appFactory.weakRefTest.weakRefNone;

import Ndroid.appFactory.weakRefTest.DummyModeler;

/**
 * Created by Doohyun on 2017. 2. 22..
 */

public class DummyNonePresenter {
    private DummyWeakNoneActivity dummyActivity;

    public DummyNonePresenter(DummyWeakNoneActivity dummyActivity){
        this.dummyActivity = dummyActivity;
    }

    /**
     * Acitivty 가 스레드로 일을 시킨다고 가정.
     */
    public void runDelayedWork() {

        new Thread(() -> {
            try {
                DummyModeler.GetInstance().delayedRun();
                dummyActivity.activityAction();
            } catch (Exception e) {
                System.out.println("에러");
                e.printStackTrace();
            }
        }).start();
    }
}
