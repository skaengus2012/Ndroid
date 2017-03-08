package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.function.IBiFunction;
import Ndroid.appFactory.common.function.IFunction;
import io.reactivex.functions.BiFunction;

/**
 * BiFunction combination Factory
 *
 * <pre>
 *     Support default method in Predicate.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 7..
 */

public class BiFunctionFactory<T, U, R> extends CombineFactory<IBiFunction<T, U, R>, BiFunction<T, U, R>> {

    public BiFunctionFactory(IBiFunction<T, U, R> biFunction) {
        super(biFunction);
    }

    /**
     * Return Rx style BiFunction.
     *
     * @return
     */
    @Override
    public BiFunction<T, U, R> getRx() {
        return (T t, U u) -> get().apply(t, u);
    }

    /**
     * <pre>
     *     example (iFunction) : f(x)
     *     param (after) : g(x)
     *     return : g(f(x))
     * </pre>
     *
     * @param after
     * @param <V>
     * @return
     */
    public <V> BiFunctionFactory<T, U, V> andThen(@NonNull IFunction<? super R, ? extends V> after) {
        NullCheck(after);

        return new BiFunctionFactory<>((T t, U u) -> after.apply(get().apply(t, u)));
    }
}
