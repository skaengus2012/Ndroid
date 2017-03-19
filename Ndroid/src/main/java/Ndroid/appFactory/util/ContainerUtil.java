package Ndroid.appFactory.util;

import java.util.Collection;
import java.util.Map;

/**
 * Container 클래스의 기능 지원.
 *
 * Created by Doohyun on 2017. 3. 1..
 */

public class ContainerUtil{

    /**
     * Collection 빈 상태 확인.
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> boolean IsEmpty(Collection<T> collection) {
        return MaybeUtil.JustNullable(collection).map(param -> param.isEmpty()).blockingGet(true);
    }

    /**
     * 배열의 빈 상태 확인.
     *
     * @param arrayT
     * @param <T>
     * @return
     */
    public static <T> boolean IsEmpty(T... arrayT) {
        return MaybeUtil.JustNullable(arrayT).map(param -> param.length == 0).blockingGet(true);
    }

    /**
     * Map 의 빈 상태 확인.
     *
     * @param map
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> boolean IsEmpty(Map<T, R> map) {
        return MaybeUtil.JustNullable(map).map(param -> param.isEmpty()).blockingGet(true);
    }
}
