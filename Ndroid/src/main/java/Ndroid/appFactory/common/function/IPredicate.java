package Ndroid.appFactory.common.function;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.function.extension.combineFactory.PredicateFactory;

/**
 * JAVA8 Predicate Support
 *
 * <pre>
 *     The Original Predicate in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
@FunctionalInterface
public interface IPredicate<T> {
    boolean test(@NonNull T t);

    /**
     * Predicate 를 조합할 수 있는 빌더 팩토리를 생성한다.
     *
     * @param iPredicate
     * @param <T>
     * @return
     */
    static <T> PredicateFactory<T> Builder(IPredicate<T> iPredicate){
        return new PredicateFactory<>(iPredicate);
    }
}
