package Ndroid.appFactory.common.function;

import android.support.annotation.NonNull;

/**
 * JAVA8 BiPredicate Support
 *
 * <pre>
 *     The Original BiPredicate in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
@FunctionalInterface
public interface IBiPredicate<T, R> {
    void test(@NonNull T t, @NonNull R r);
}
