package Ndroid.appFactory.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

import Ndroid.appFactory.common.function.exceptionLambda.IExConsumer;
import Ndroid.appFactory.common.function.exceptionLambda.IExFunction;
import io.reactivex.Maybe;

/**
 * Rx Maybe Support.
 *
 * <pre>
 *     Rx.Maybe is not strong compared java8.optional.
 *
 *     So, I support Rx.Maybe using MaybeUtil.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 15..
 */

public class MaybeUtil {

    /**
     * Create Maybe null able.
     *
     * <pre>
     *     If t is empty, create empty maybe.
     * </pre>
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Maybe<T> JustNullable(@Nullable T t) {
        return (t == null) ? Maybe.empty() : Maybe.just(t);
    }

    /**
     * If maybe is empty, Runnable will be excute.
     *
     * @param t
     * @param run
     * @param <T>
     */
    public static <T> void RunEmptyMaybe(
            @NonNull Maybe<T> t
            , @NonNull Runnable run) {
        t.isEmpty().subscribe(emptyYn -> {
            if (emptyYn) {
                run.run();
            }
        });
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
    public static <T, R> Maybe<R> JustInMap(
            @NonNull Map<T, R> targetMap
            , @NonNull T key) {
        return JustNullable(targetMap.get(key));
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
    public static <T, R> void RunMaybeInMap(
            @NonNull Map<T, R> targetMap
            , @NonNull T key
            , @NonNull IExConsumer<R> exConsumer) {
        JustInMap(targetMap, key).subscribe(exConsumer);
    }

    /**
     * Parse, boxed object!
     *
     * @param stringValue
     * @param function
     * @param <T>
     * @return
     */
    private static <T> Maybe<T> ParseBoxedObject(
            @NonNull String stringValue,
            @NonNull IExFunction<String, T> function) {
        try {
            return Maybe.just(function.apply(stringValue));
        } catch (Exception e) {
            return Maybe.empty();
        }
    }

    /**
     * Boolean parse!
     *
     * @param stringValue
     * @return
     */
    public static Maybe<Boolean> ParseBoolean(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, Boolean::parseBoolean);
    }

    /**
     * Integer parse!
     *
     * @param stringValue
     * @return
     */
    public static Maybe<Integer> ParseInteger(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, Integer::parseInt);
    }

    /**
     * Long parse!
     *
     * @param stringValue
     * @return
     */
    public static Maybe<Long> ParseLong(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, Long::parseLong);
    }

    /**
     * Double parse!
     *
     * @param stringValue
     * @return
     */
    public static Maybe<Double> ParseDouble(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, Double::parseDouble);
    }

    /**
     * Float parse!
     *
     * @param stringValue
     * @return
     */
    public static Maybe<Float> ParseFloat(@NonNull String stringValue) {
        return ParseBoxedObject(stringValue, Float::parseFloat);
    }
}
