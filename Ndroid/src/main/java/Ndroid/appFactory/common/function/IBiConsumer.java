package Ndroid.appFactory.common.function;

import android.support.annotation.NonNull;

/**
 * JAVA8 BiConsumer Support
 *
 * <pre>
 *     The Original BiConsumer in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
@FunctionalInterface
public interface IBiConsumer<T, R> {
    void accept(@NonNull T t, @NonNull R r);
}
