package Ndroid.appFactory.util.business;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import Njava.function.exceptionLambda.IExConsumer;
import Njava.util.business.CheckUtil;
import Njava.util.function.MaybeUtil;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


/**
 * Builder, For more async processing using Rx.
 * <p>
 * Created by Doohyun on 2017. 4. 2..
 */
public final class AsyncBuilder<T> {

    private static final String LOG_NAME = "Rx Builder";

    @NonNull
    private FlowableOnSubscribe<T> flowAbleOnSubscribe;

    private Maybe<IExConsumer<T>> nextConsumerMaybe = Maybe.empty();
    private Maybe<Action> completeRunnableMaybe = Maybe.empty();
    private Maybe<IExConsumer<Throwable>> exceptionConsumerMaybe = Maybe.empty();

    // default io & androidSchedulers.
    private Scheduler subscribeOnScheduler, observeOnScheduler;

    private AsyncBuilder(@NonNull FlowableOnSubscribe<T> flowAbleOnSubscribe) {
        this.flowAbleOnSubscribe = flowAbleOnSubscribe;

        nextConsumerMaybe = Maybe.empty();
        completeRunnableMaybe = Maybe.empty();
        exceptionConsumerMaybe = Maybe.empty();

        subscribeOnScheduler = Schedulers.io();
        observeOnScheduler = AndroidSchedulers.mainThread();
    }

    /**
     * Create RxAsyncBuilder
     *
     * @param flowAbleOnSubscribe
     * @param <T>
     * @return
     */
    public static <T> AsyncBuilder<T> Create(@NonNull FlowableOnSubscribe<T> flowAbleOnSubscribe) {
        CheckUtil.NullCheck(flowAbleOnSubscribe, "[ERROR] : flowAbleOnSubscribe param is null", RuntimeException.class);
        return new AsyncBuilder<>(flowAbleOnSubscribe);
    }

    /**
     * Create RxAsyncBuilder (using Runnable)
     *
     * @param runnable
     * @return
     */
    public static AsyncBuilder<Void> Create(@Nullable Runnable runnable) {
        return new AsyncBuilder<>((FlowableEmitter<Void> emitter) -> {
                                        MaybeUtil.JustNullable(runnable).subscribe(Runnable::run);
                                        emitter.onComplete();
                                    });
    }

    /**
     * Setting next.
     *
     * @param nextConsumer
     * @return
     */
    public AsyncBuilder<T> doOnNext(@Nullable IExConsumer<T> nextConsumer) {
        nextConsumerMaybe = MaybeUtil.JustNullable(nextConsumer);
        return this;
    }

    /**
     * Setting complete runnable.
     *
     * @param completeRunnable
     * @return
     */
    public AsyncBuilder<T> doOnComplete(@Nullable Action completeRunnable) {
        completeRunnableMaybe = MaybeUtil.JustNullable(completeRunnable);
        return this;
    }

    /**
     * Setting error.
     *
     * @param exceptionIExConsumer
     * @return
     */
    public AsyncBuilder<T> doOnError(@Nullable IExConsumer<Throwable> exceptionIExConsumer) {
        exceptionConsumerMaybe = MaybeUtil.JustNullable(exceptionIExConsumer);
        return this;
    }

    /**
     * Setting subscribeOnScheduler with IO
     *
     * @return
     */
    public AsyncBuilder<T> Io() {
        subscribeOnScheduler = Schedulers.io();
        return this;
    }

    /**
     * Setting subscribeOnScheduler with trampoline.
     *
     * @return
     */
    public AsyncBuilder<T> TrampoLine() {
        subscribeOnScheduler = Schedulers.trampoline();
        return this;
    }

    /**
     * Execute flowAble.
     * <p>
     * <pre>
     *     Never stop main thread while executing.
     * </pre>
     */
    public void run() {
        Flowable.create(flowAbleOnSubscribe, BackpressureStrategy.BUFFER).
                subscribeOn(subscribeOnScheduler).
                observeOn(observeOnScheduler).
                subscribe(
                        // doOnNext
                        t -> nextConsumerMaybe.subscribe(consumer -> consumer.accept(t))
                        // error
                        , e -> {
                            Log.e(LOG_NAME, "Occur error in RxAsync!!", e);
                            exceptionConsumerMaybe.subscribe(consumer -> consumer.accept(e));
                        }
                        // complete
                        , () -> completeRunnableMaybe.subscribe(Action::run));
    }
}
