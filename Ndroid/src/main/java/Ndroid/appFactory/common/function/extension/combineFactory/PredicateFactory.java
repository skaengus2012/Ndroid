package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.function.IPredicate;
import io.reactivex.functions.Predicate;

/**
 * Predicate combination Factory
 *
 * <pre>
 *     Support default method in Predicate.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 1..
 */

public final class PredicateFactory<T> extends CombineFactory<IPredicate<T>, Predicate<T>> {

    public PredicateFactory(@NonNull IPredicate<T> predicate) {
        super(predicate);
    }

    /**
     * Return Rx style Predicate.
     *
     * @return
     */
    @Override
    public Predicate<T> getRx() {
        return (T t) -> get().test(t);
    }

    /**
     * negative 상태 predicate 를 생성한다.
     *
     * @return
     */
    public PredicateFactory<T> negative(){
        return new PredicateFactory<>(t -> !get().test(t));
    }

    /**
     * predicate 에 and 상태를 추가한다.
     *
     * @param andConditionPredicate
     * @return
     */
    public PredicateFactory<T> and(@NonNull IPredicate<T> andConditionPredicate) {
        NullCheck(andConditionPredicate);
        return new PredicateFactory<>(t -> get().test(t) && andConditionPredicate.test(t));
    }

    /**
     * predicate 에 or 상태를 추가한다.
     *
     * @param orConditionPredicate
     * @return
     */
    public PredicateFactory<T> or(@NonNull IPredicate<T> orConditionPredicate) {
        NullCheck(orConditionPredicate);
        return new PredicateFactory<>(t -> get().test(t) || orConditionPredicate.test(t));
    }
}
