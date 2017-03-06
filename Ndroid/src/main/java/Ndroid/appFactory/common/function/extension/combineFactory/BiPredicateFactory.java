package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;
import Ndroid.appFactory.common.function.IBiPredicate;

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

public class BiPredicateFactory<T, U> extends NxModeler{

    private IBiPredicate<T, U> biPredicate;

    public BiPredicateFactory(@NonNull IBiPredicate<T, U> biPredicate) {
        NullCheck(biPredicate);

        this.biPredicate = biPredicate;
    }

    /**
     * Predicate 에 or 상태를 추가한다.
     *
     * @param other
     * @return
     */
    public BiPredicateFactory<T, U> or(IBiPredicate<? super T, ? super U> other) {
        NullCheck(other);

        return new BiPredicateFactory<>((T t, U u) -> biPredicate.test(t, u) || other.test(t, u));
    }

    /**
     * predicate 에 and 상태를 추가한다.
     *
     * @param other
     * @return
     */
    public BiPredicateFactory<T, U> and(IBiPredicate<? super T, ? super U> other) {
        NullCheck(other);

        return new BiPredicateFactory<>((T t, U u) -> biPredicate.test(t, u) && other.test(t, u));
    }

    /**
     * negative 상태 predicate 를 생성한다.
     *
     * @return
     */
    public BiPredicateFactory<T, U> negate() {
        return new BiPredicateFactory<>((T t, U u) -> !biPredicate.test(t, u));
    }

    /**
     * BiPredicate 를 출력한다.
     *
     * @return
     */
    public IBiPredicate<T, U> getPredicate() {
        return biPredicate;
    }
}
