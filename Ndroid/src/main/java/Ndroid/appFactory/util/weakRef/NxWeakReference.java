package Ndroid.appFactory.util.weakRef;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import Njava.function.IConsumer;
import Njava.function.IFunction;
import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;


/**
 * WeakReference 를 조금 더 적극적으로 사용하기 위해 상속
 *
 * Created by Doohyun on 2017. 1. 26..
 */

public class NxWeakReference<T> extends WeakReference<T> {

    /**
     * 단순한 상속에 대한 생성자 제작
     * @param referent
     */
    public NxWeakReference(T referent) {
        super(referent);
    }

    /**
     * 단순한 상속에 대한 생성자 제작
     * @param referent
     * @param q
     */
    public NxWeakReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

    /**
     * 약한 참조 속 실제 객체의 사용
     * @param weakReferenceConsumer
     */
    public void run(IConsumer<T> weakReferenceConsumer) {
        T t = this.get();

        if (t != null) {
            weakReferenceConsumer.accept(t);
        }
    }

    /**
     * 약한 참조 속 실제 객체의 사용
     *
     * <pre>
     *     call back 의 개념 사용
     * </pre>
     *
     * @param weakReferenceFunction
     */
    public <R> Maybe<R> call(IFunction<T, R> weakReferenceFunction) {
        R result = null;

        T t = this.get();

        if (t != null) {
            result = weakReferenceFunction.apply(t);
        }

        return MaybeUtil.JustNullable(result);
    }

    /**
     * 약한 참조 속 실제 객체의 사용
     *
     * <pre>
     *     call back 의 개념 사용
     * </pre>
     *
     * @param weakReferenceFunction
     */
    public <R> R call(IFunction<T, R> weakReferenceFunction, R defaultValue) {
        final Maybe<R> result = call(weakReferenceFunction);

        return result.blockingGet(defaultValue);
    }
}
