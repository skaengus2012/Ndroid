package Ndroid.appFactory.weakRefTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;

/**
 * 스레드에서 일을 시킨다고 가정하고, 테스트를 수행하는 모델러
 * <p>
 * Created by Doohyun on 2017. 2. 22..
 */

public class DummyModeler extends NxModeler {

    private DummyModeler() {
    }

    private static final class ManagerHolder {
        private static DummyModeler dummyModeler = new DummyModeler();
    }

    public static DummyModeler GetInstance() {
        return ManagerHolder.dummyModeler;
    }

    public void introduceModelerMethod() {
        // Check example.
        {
            Integer a = 10;
            Check(a != 5);       // throws RuntimeException.
        }

        // NullCheck example.
        {
            Integer a = null;
            NullCheck(a);       // throws RuntimeException.
        }

        // String empty check example.
        {
            String a = "";
            EmptyToStringCheck(a);       // throws RuntimeException.
        }

        // Container empty check example.
        {
            EmptyContainerCheck(new LinkedList<String>());                      // throws RuntimeException.
            EmptyContainerCheck(new Integer[2]);                                // throws RuntimeException.
            EmptyContainerCheck(new HashMap<String, String>());                 // throws RuntimeException.
        }

        {
            Map<String, Object> param = new HashMap<>();
            PutDefualtValueInMap(param, "Doohyun", () -> 2);

            // Not operate put action, because param have key "doohyun"
            PutDefualtValueInMap(param, "Doohyun", () -> 3);
        }
    }

    /**
     * 시간이 오래걸리는 일을 수행해본다.
     */
    public void delayedRun() throws InterruptedException {
        System.out.println("delayedRun 테스트 시작!!");

        Thread.sleep(5000);

        System.out.println("delayedRun 테스트 끝!!");
    }

}
