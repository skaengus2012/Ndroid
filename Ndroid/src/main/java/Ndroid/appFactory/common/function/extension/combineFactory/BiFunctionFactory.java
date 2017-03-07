package Ndroid.appFactory.common.function.extension.combineFactory;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;
import Ndroid.appFactory.common.function.IBiFunction;
import Ndroid.appFactory.common.function.IFunction;

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

public class BiFunctionFactory<T, U, R> extends NxModeler {
    private IBiFunction<T, U, R> biFunction;

    public BiFunctionFactory(IBiFunction<T, U, R> biFunction) {
        this.biFunction = biFunction;
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
    public <V> BiFunctionFactory<T, U, V> andThen(IFunction<? super R, ? extends V> after) {
        NullCheck(after);

        return new BiFunctionFactory<>((T t, U u) -> after.apply(biFunction.apply(t, u)));
    }

    /**
     * Return final combination BiFunction
     *
     * @return
     */
    public IBiFunction<T, U, R> getFunction() {
        return biFunction;
    }
}
