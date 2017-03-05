package Ndroid.appFactory.common.function;

import java.util.Comparator;

import Ndroid.appFactory.common.function.extension.combineFactory.ComparatorFactory;

/**
 * JAVA8 Comparator Support
 *
 * <pre>
 *     Default method in Comparator can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
public interface IComparator<T> extends Comparator<T>{

    /**
     * Create ComparatorFactory for Lambda combination.
     *
     * @param comparator
     * @param <T>
     * @return
     */
    static <T> ComparatorFactory<T> Builder(Comparator<T> comparator){
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
    static <T> ComparatorFactory<T> Builder(Comparator<T> comparator, Boolean nullFirstYn){
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
    static <T, U> Comparator<T> CreateKeyComparator(
            IFunction<? super T, ? extends U> keyExtractor
            , Comparator<? super U> keyComparator) {
        return (T c1, T c2) -> keyComparator.compare(keyExtractor.apply(c1), keyExtractor.apply(c2));
    }
}
