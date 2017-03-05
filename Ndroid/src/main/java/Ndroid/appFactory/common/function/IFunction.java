package Ndroid.appFactory.common.function;

import android.support.annotation.NonNull;

/**
 * JAVA8 Function Support
 *
 * <pre>
 *     The Original Function in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
@FunctionalInterface
public interface IFunction<T, R> {
    R apply(@NonNull T t);
}
