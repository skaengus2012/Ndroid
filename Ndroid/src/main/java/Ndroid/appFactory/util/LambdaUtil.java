package Ndroid.appFactory.util;

import java.util.Comparator;

import Ndroid.appFactory.common.function.IFunction;
import Ndroid.appFactory.common.function.IPredicate;
import Ndroid.appFactory.common.function.extension.combineFactory.ComparatorFactory;
import Ndroid.appFactory.common.function.extension.combineFactory.PredicateFactory;

/**
 * Lambda Util Define.
 *
 * <pre>
 *     This class is supported default and static method in JAVA8 Lambda.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */

public class LambdaUtil {

    /**
     * Create Predicate Builder
     *
     * @param iPredicate
     * @param <T>
     * @return
     */
    public static <T> PredicateFactory<T> PredicateBuilder(IPredicate<T> iPredicate){
        return new PredicateFactory<>(iPredicate);
    }

    /**
     * Create ComparatorFactory for Lambda combination.
     *
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> ComparatorFactory<T> ComparatorBuilder(Comparator<T> comparator){
        return new ComparatorFactory<>(comparator);
    }

    /**
     * Create ComparatorFactory for Lambda combination.
     *
     * @param comparator
     * @param nullFirstYn
     * @param <T>
     * @return
     */
    public static <T> ComparatorFactory<T> ComparatorBuilder(Comparator<T> comparator, Boolean nullFirstYn){
        return new ComparatorFactory<>(comparator, nullFirstYn);
    }

    /**
     * Create Comparator for support "Object" key compare.
     *
     * @param keyExtractor
     * @param keyComparator
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Comparator<T> CreateKeyComparator(
            IFunction<? super T, ? extends U> keyExtractor
            , Comparator<? super U> keyComparator) {
        return (T c1, T c2) -> keyComparator.compare(keyExtractor.apply(c1), keyExtractor.apply(c2));
    }
}
