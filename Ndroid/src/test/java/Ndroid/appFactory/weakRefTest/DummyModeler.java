package Ndroid.appFactory.weakRefTest;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;

/**
 * 스레드에서 일을 시킨다고 가정하고, 테스트를 수행하는 모델러
 *
 * Created by Doohyun on 2017. 2. 22..
 */

public class DummyModeler extends NxModeler {

    private DummyModeler(){}

    private static final class ManagerHolder {
        private static DummyModeler dummyModeler = new DummyModeler();
    }

    public static DummyModeler GetInstance(){
        return ManagerHolder.dummyModeler;
    }

    /**
     * 시간이 오래걸리는 일을 수행해본다.
     */
    public void delayedRun() throws InterruptedException{
        System.out.println("delayedRun 테스트 시작!!");

        Thread.sleep(5000);

        System.out.println("delayedRun 테스트 끝!!");
    }


}
