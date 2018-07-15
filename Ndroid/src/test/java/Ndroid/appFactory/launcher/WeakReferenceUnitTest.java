package Ndroid.appFactory.launcher;

import org.junit.Test;

import java.lang.ref.WeakReference;

import Ndroid.appFactory.weakref.apply.DummyActivity;
import Ndroid.appFactory.weakref.none.DummyWeakNoneActivity;

/**
 * Ndroid MVP android context memory leak test!
 *
 * Created by Doohyun on 2017. 3. 7..
 */

public class WeakReferenceUnitTest {

    /**
     * WeakReference 적용 MVP!
     *
     * @throws Exception
     */
    @Test
    public void weakReferenceTest() throws Exception {
        DummyActivity dummyActivity = new DummyActivity();
        WeakReference<DummyActivity> dummyActivityWeakReference = new WeakReference<>(dummyActivity);

        System.out.println("weakReferenceTest 테스트 시작.");
        dummyActivity.action();

        dummyActivity = null;
        Thread.sleep(3000);
        System.gc();
        System.out.println("가비지 컬렉팅 시작");

        Thread.sleep(1000);

        System.out.println("객체는 사라졌나요?" + (dummyActivityWeakReference.get() == null));
    }

    /**
     * WeakReference 미적용 MVP!
     *
     * @throws Exception
     */
    @Test
    public void weakReferenceNoneTest() throws Exception {
        DummyWeakNoneActivity dummyActivity = new DummyWeakNoneActivity();
        WeakReference<DummyWeakNoneActivity> dummyActivityWeakReference = new WeakReference<>(dummyActivity);

        System.out.println("weakReferenceNoneTest 테스트 시작.");
        dummyActivity.action();

        dummyActivity = null;
        Thread.sleep(3000);
        System.gc();
        System.out.println("가비지 컬렉팅 시작");

        Thread.sleep(1000);

        System.out.println("객체는 사라졌나요?" + (dummyActivityWeakReference.get() == null));
    }
}
