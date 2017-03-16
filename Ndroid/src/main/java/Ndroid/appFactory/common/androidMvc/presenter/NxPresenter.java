package Ndroid.appFactory.common.androidMvc.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;
import Ndroid.appFactory.common.function.IConsumer;
import Ndroid.appFactory.common.function.IFunction;
import Ndroid.appFactory.util.weakRef.NxWeakReference;

/**
 * Presenter 정의
 *
 * <pre>
 *     Activity 와 Modeler 의 중간 커맨드 역할을 해주는 Presenter 정의
 *     Midas 에서 사용하는 모든 앱은 이 Presenter 를 상속받아야함
 *     참고 : http://doohyun.tistory.com/38
 * </pre>
 *
 * Created by Doohyun on 2017. 2. 12..
 */

public abstract class NxPresenter<T> extends NxModeler {

    private NxWeakReference<T> mitActivityWeakReference;

    public NxPresenter(@NonNull T t) {
        NullCheck(t);
        mitActivityWeakReference = createWeakReference(t);
    }

    /**
     * WeakReference 생성
     *
     * <pre>
     *     타입에 맞는 weakReference 생성을 하기 위해, 생성방법은 추상화
     * </pre>
     *
     * @param t
     * @return
     */
    protected abstract NxWeakReference<T> createWeakReference(@NonNull T t);

    /**
     * WeakReference 호출
     *
     * @return
     */
    protected final NxWeakReference<T> getWeakRef(){
        return mitActivityWeakReference;
    }

    /**
     * 약한참조 객체 속 일을 시키기 위한 행위 정의
     *
     * @param consumer
     */
    public final void run(@NonNull IConsumer<T> consumer) {
        NullCheck(consumer);
        mitActivityWeakReference.run(consumer);
    }

    /**
     * 약한참조 객체 속 일을 시키며, 출력물을 얻기 위한 call 정의
     *
     * @param function
     * @return
     */
    public final <R> R call(@NonNull IFunction<T, R> function) {
        NullCheck(function);
        return mitActivityWeakReference.call(function);
    }

    /**
     * 약한참조 객체 속 일을 시키며, 출력물을 얻기 위한 call 정의
     *
     * @param function
     * @param defaultValue
     * @return
     */
    public final <R> R call(@NonNull IFunction<T, R> function, @Nullable R defaultValue) {
        NullCheck(function);
        return mitActivityWeakReference.call(function, defaultValue);
    }
}
