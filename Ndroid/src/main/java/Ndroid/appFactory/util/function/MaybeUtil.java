package Ndroid.appFactory.util.function;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import Ndroid.appFactory.common.function.exceptionLambda.IExConsumer;
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
    @NonNull
    public static <T> Maybe<T> JustNullable(@Nullable T t) {
        return (t == null) ? Maybe.empty() : Maybe.just(t);
    }

    /**
     * If maybe is valid, consumer param will execute. But Empty maybe will be emptyRunnable;
     *
     * @param maybe
     * @param consumer
     * @param emptyRunnable
     * @param <T>
     */
    @NonNull
    public static <T> void Subscribe(
            @NonNull Maybe<T> maybe
            , @NonNull IExConsumer<T> consumer
            , @NonNull Runnable emptyRunnable) {
        // empty maybe.
        SubscribeEmpty(maybe, emptyRunnable);

        // valid maybe.
        maybe.subscribe(consumer);
    }

    /**
     * If maybe is empty, Runnable will be excute.
     *
     * @param maybe
     * @param run
     * @param <T>
     */
    @NonNull
    public static <T> void SubscribeEmpty(
            @NonNull Maybe<T> maybe
            , @NonNull Runnable run) {
        maybe.isEmpty().subscribe(emptyYn -> {
            if (emptyYn) {
                run.run();
            }
        });
    }
}
