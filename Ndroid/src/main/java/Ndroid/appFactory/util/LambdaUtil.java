package Ndroid.appFactory.util;

import android.support.annotation.NonNull;

import java.util.Comparator;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;
import Ndroid.appFactory.common.function.IBinaryOperator;
import Ndroid.appFactory.common.function.IFunction;
import Ndroid.appFactory.common.function.IPredicate;
import Ndroid.appFactory.common.function.IUnaryOperator;
import Ndroid.appFactory.common.function.extension.combineFactory.ComparatorFactory;
import Ndroid.appFactory.common.function.extension.combineFactory.FunctionFactory;
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
    public static <T> PredicateFactory<T> PredicateBuilder(@NonNull IPredicate<T> iPredicate){
        return new PredicateFactory<>(iPredicate);
    }

    /**
     * Create ComparatorFactory for Lambda combination.
     *
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> ComparatorFactory<T> ComparatorBuilder(@NonNull Comparator<T> comparator){
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
    public static <T> ComparatorFactory<T> ComparatorBuilder(
            @NonNull Comparator<T> comparator
            , @NonNull Boolean nullFirstYn){
        return new ComparatorFactory<>(comparator, nullFirstYn);
    }

    /**
     * Create FunctionFactory for Lambda combination
     *
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> FunctionFactory<T, R> FunctionBuilder(@NonNull IFunction<T, R> function) {
        return new FunctionFactory<>(function);
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
            @NonNull IFunction<? super T, ? extends U> keyExtractor
            , @NonNull Comparator<? super U> keyComparator) {
        return (T c1, T c2) -> keyComparator.compare(keyExtractor.apply(c1), keyExtractor.apply(c2));
    }

    /**
     * Return Identity.
     *
     * @param <T>
     * @return
     */
    public static <T> IUnaryOperator<T> GetIdentity() {
        return t -> t;
    }

    /**
     * Return min value T
     *
     * @param comparator
     * @param <T>
     * @return
     */
    static <T> IBinaryOperator<T> MinBy(@NonNull Comparator<? super T> comparator) {
        NxModeler.NullCheck(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Return max value T
     *
     * @param comparator
     * @param <T>
     * @return
     */
    static <T> IBinaryOperator<T> MaxBy(@NonNull Comparator<? super T> comparator) {
        NxModeler.NullCheck(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}
