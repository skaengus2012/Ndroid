package Ndroid.appFactory.util.business;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.Map;

import Ndroid.appFactory.common.function.ISupplier;
import Ndroid.appFactory.common.function.exceptionLambda.IExConsumer;
import Ndroid.appFactory.util.function.MaybeUtil;
import io.reactivex.Maybe;

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
    @NonNull
    public static <T> boolean IsEmpty(@Nullable Collection<T> collection) {
        return MaybeUtil.JustNullable(collection).map(Collection::isEmpty).blockingGet(true);
    }

    /**
     * 배열의 빈 상태 확인.
     *
     * @param arrayT
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> boolean IsEmpty(@Nullable T... arrayT) {
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
    @NonNull
    public static <T, R> boolean IsEmpty(@Nullable Map<T, R> map) {
        return MaybeUtil.JustNullable(map).map(Map::isEmpty).blockingGet(true);
    }

    /**
     * If map have key-value, that function will be return valid maybe.
     *
     * @param targetMap
     * @param key
     * @param <T>
     * @param <R>
     * @return
     */
    @NonNull
    public static <T, R> Maybe<R> JustInMap(
            @NonNull Map<T, R> targetMap
            , @NonNull T key) {
        return MaybeUtil.JustNullable(targetMap.get(key));
    }

    /**
     * If maybe value is valid, exConsumer will be run!!
     *
     * @param targetMap
     * @param key
     * @param exConsumer
     * @param <T>
     * @param <R>
     */
    @NonNull
    public static <T, R> void RunMaybeInMap(
            @NonNull Map<T, R> targetMap
            , @NonNull T key
            , @NonNull IExConsumer<R> exConsumer) {
        JustInMap(targetMap, key).subscribe(exConsumer);
    }

    /**
     * If this map not contains key, put key & value.
     *
     * @param map
     * @param key
     * @param iSupplier
     */
    public static <T, R> void PutEmptyKeyValueInMap(@NonNull Map<T, R> map, @NonNull T key, @NonNull ISupplier<R> iSupplier) {
        MaybeUtil.SubscribeEmpty(JustInMap(map, key), () -> map.put(key, iSupplier.accept()));
    }
}
