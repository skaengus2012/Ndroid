package Ndroid.appFactory.util;

import android.support.annotation.NonNull;

import java.util.Comparator;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;
import Ndroid.appFactory.common.function.IBiFunction;
import Ndroid.appFactory.common.function.IBiPredicate;
import Ndroid.appFactory.common.function.IFunction;
import Ndroid.appFactory.common.function.IPredicate;
import Ndroid.appFactory.common.function.extension.combineFactory.BiFunctionFactory;
import Ndroid.appFactory.common.function.extension.combineFactory.BiPredicateFactory;
import Ndroid.appFactory.common.function.extension.combineFactory.BinaryOperatorFactory;
import Ndroid.appFactory.common.function.extension.combineFactory.ComparatorFactory;
import Ndroid.appFactory.common.function.extension.combineFactory.FunctionFactory;
import Ndroid.appFactory.common.function.extension.combineFactory.IUnaryOperatorFactory;
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
     * Create BiPredicate Builder
     *
     * @param iBiPredicate
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> BiPredicateFactory<T, U> PredicateBuilder(@NonNull IBiPredicate<T, U> iBiPredicate) {
        return new BiPredicateFactory<>(iBiPredicate);
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
     * Create Comparator for support "Object" key compare.
     *
     * @param keyExtractor
     * @param keyComparator
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> ComparatorFactory<T> ComparatorBuilder(
            @NonNull IFunction<? super T, ? extends U> keyExtractor
            , @NonNull Comparator<? super U> keyComparator) {
        return new ComparatorFactory<>((c1, c2) -> keyComparator.compare(keyExtractor.apply(c1), keyExtractor.apply(c2)));
    }

    /**
     * Create Comparator for support "Object" key compare.
     *
     * <pre>
     *     ComparatorFactory support.
     * </pre>
     *
     * @param keyExtractor
     * @param keyComparator
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> ComparatorFactory<T> ComparatorBuilder(
            @NonNull IFunction<? super T, ? extends U> keyExtractor
            , @NonNull ComparatorFactory<? super U> keyComparator) {
        return ComparatorBuilder(keyExtractor, keyComparator.get());
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
     * Create FunctionFactory for Lambda combination
     *
     * @param function
     * @param <T>
     * @param <U>
     * @param <R>
     * @return
     */
    public static <T, U, R> BiFunctionFactory<T, U, R> FunctionBuilder(@NonNull IBiFunction<T, U, R> function) {
        return new BiFunctionFactory<>(function);
    }

    /**
     * Return Identity.
     *
     * @param <T>
     * @return
     */
    public static <T> IUnaryOperatorFactory<T> GetIdentity() {
        return new IUnaryOperatorFactory<>(t -> t);
    }

    /**
     * Return min value T
     *
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> BinaryOperatorFactory<T> MinBy(@NonNull Comparator<? super T> comparator) {
        return new BinaryOperatorFactory<>((a, b) -> comparator.compare(a, b) <= 0 ? a : b);
    }

    /**
     * Return max value T
     *
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> BinaryOperatorFactory<T> MaxBy(@NonNull Comparator<? super T> comparator) {
        NxModeler.NullCheck(comparator);
        return new BinaryOperatorFactory<>((a, b) -> comparator.compare(a, b) >= 0 ? a : b);
    }
}
