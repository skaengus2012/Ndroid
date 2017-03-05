package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;

import Ndroid.appFactory.common.modeler.NxModeler;
import Ndroid.appFactory.common.function.extension.supportFunction.NullAbleComparator;

/**
 * Comparator combination Factory
 *
 * <pre>
 *     Support default method in Comparator.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 1..
 */

public class ComparatorFactory<T> extends NxModeler {

    private Boolean nullFirstYn = false;            // null 값 우선순위
    private NullAbleComparator<T> comparator;

    public ComparatorFactory(@NonNull Comparator<T> comparator) {
        NullCheck(comparator);

        this.comparator = new NullAbleComparator<>(comparator, nullFirstYn);
    }

    /**
     * 생성자 정의
     *
     * <pre>
     *     null 우선순위를 정할 수 있음.
     * </pre>
     *
     * @param comparator
     * @param nullFirstYn
     */
    public ComparatorFactory(
            @NonNull Comparator<T> comparator
            , @NonNull Boolean nullFirstYn) {
        NullCheck(comparator);
        NullCheck(nullFirstYn);

        this.nullFirstYn = nullFirstYn;

        this.comparator = new NullAbleComparator<>(comparator, nullFirstYn);
    }

    /**
     * null 값을 우선순위 높게 잡는다.
     *
     * @return
     */
    public ComparatorFactory<T> nullsFirst() {
        return new ComparatorFactory<>(comparator, true);
    }

    /**
     * null 값을 우선순위 낮게 잡는다.
     *
     * @return
     */
    public ComparatorFactory<T> nullsLast() {
        return new ComparatorFactory<>(comparator, false);
    }

    /**
     * 정렬 방식 변경.
     *
     * <pre>
     *     오름차순 -> 내림차순
     *     내림차순 -> 오름차순
     * </pre>
     *
     * @return
     */
    public ComparatorFactory<T> reversed(){
        return new ComparatorFactory<>(Collections.reverseOrder(comparator), !nullFirstYn);
    }

    /**
     * 비교 진행에서 다음 조건을 추가시킨다.
     *
     * @param thenComparator
     * @return
     */
    public ComparatorFactory<T> thenComparing(@NonNull Comparator<T> thenComparator) {
        NullCheck(thenComparator);

        return new ComparatorFactory<>((c1, c2) -> {
            int res = comparator.compare(c1, c2);
            return (res != 0) ? res : thenComparator.compare(c1, c2);
        }, nullFirstYn);
    }

    /**
     * Comparator 출력.
     *
     * @return
     */
    public Comparator<T> getComparator() {
        return comparator;
    }
}
