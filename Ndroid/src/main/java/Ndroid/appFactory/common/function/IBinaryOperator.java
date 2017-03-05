package Ndroid.appFactory.common.function;

import android.support.annotation.NonNull;

import java.util.Comparator;

import Ndroid.appFactory.common.modeler.NxModeler;

/**
 * JAVA8 BinaryOperator Support
 *
 * <pre>
 *     The Original BinaryOperator in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
public interface IBinaryOperator<T> extends IBiFunction<T,T,T> {

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
