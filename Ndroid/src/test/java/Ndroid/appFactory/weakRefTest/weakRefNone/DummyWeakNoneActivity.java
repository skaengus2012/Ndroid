package Ndroid.appFactory.weakRefTest.weakRefNone;

/**
 * 편의 상 실제 액티비티가 아닌 액티비티가 있다고 가정.
 * Created by Doohyun on 2017. 2. 22..
 */

public class DummyWeakNoneActivity {

    private DummyNonePresenter dummyPresenter;

    public DummyWeakNoneActivity() {
        dummyPresenter = new DummyNonePresenter(this);
    }

    public void activityAction(){
        System.out.println("액티비티 뷰 업데이트 시작!");
    }

    /**
     * 액티비티 인보커.
     */
    public void action(){
        dummyPresenter.runDelayedWork();
    }
}
