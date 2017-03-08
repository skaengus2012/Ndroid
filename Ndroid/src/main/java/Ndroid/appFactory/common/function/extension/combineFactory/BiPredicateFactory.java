package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.function.IBiPredicate;
import io.reactivex.functions.BiPredicate;

/**
 * BiPredicate combination Factory
 *
 * <pre>
 *     Support default method in Predicate.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 7..
 */

public final class BiPredicateFactory<T, U> extends CombineFactory<IBiPredicate<T, U>, BiPredicate<T, U>>{

    public BiPredicateFactory(@NonNull IBiPredicate<T, U> biPredicate) {
        super(biPredicate);
    }

    /**
     * Return Rx style BiPredicate.
     *
     * @return
     */
    @Override
    public BiPredicate<T, U> getRx() {
        return (T t, U u) -> get().test(t, u);
    }

    /**
     * Predicate 에 or 상태를 추가한다.
     *
     * @param other
     * @return
     */
    public BiPredicateFactory<T, U> or(@NonNull IBiPredicate<? super T, ? super U> other) {
        NullCheck(other);

        return new BiPredicateFactory<>((T t, U u) -> get().test(t, u) || other.test(t, u));
    }

    /**
     * predicate 에 and 상태를 추가한다.
     *
     * @param other
     * @return
     */
    public BiPredicateFactory<T, U> and(@NonNull IBiPredicate<? super T, ? super U> other) {
        NullCheck(other);

        return new BiPredicateFactory<>((T t, U u) -> get().test(t, u) && other.test(t, u));
    }

    /**
     * negative 상태 predicate 를 생성한다.
     *
     * @return
     */
    public BiPredicateFactory<T, U> negate() {
        return new BiPredicateFactory<>((T t, U u) -> !get().test(t, u));
    }
}
