package Ndroid.appFactory.common.function;

import android.support.annotation.NonNull;

/**
 * JAVA8 BiFunction Support
 *
 * <pre>
 *     The Original BiFunction in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
@FunctionalInterface
public interface IBiFunction<T, U, R> {
    R apply(@NonNull T t, @NonNull U u);
}
