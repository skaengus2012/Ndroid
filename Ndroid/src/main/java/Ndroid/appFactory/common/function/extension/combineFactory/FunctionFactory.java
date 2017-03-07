package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;
import Ndroid.appFactory.common.function.IFunction;
import io.reactivex.functions.Function;

/**
 * Function combination Factory
 *
 * <pre>
 *     Support default method in Predicate.
 *     The original default method in JAVA8 can use api >= 24
 *     So, I made builder class for support that.
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 1..
 */

public class FunctionFactory<T, R> extends NxModeler {
    private IFunction<T, R> iFunction;

    public FunctionFactory(@NonNull IFunction<T, R> iFunction) {
        NullCheck(iFunction);
        this.iFunction = iFunction;
    }

    /**
     * Function combine : compose
     *
     * <pre>
     *     example
     *          instance (iFunction) : f(x)
     *          param (before) : g(x)
     *          return : f(g(x))
     * </pre>
     *
     * @param before
     * @param <V>
     * @return
     */
    public <V> FunctionFactory<V, R> compose(@NonNull IFunction<? super V, ? extends T> before) {
        NullCheck(before);

        return new FunctionFactory<>((V v) -> iFunction.apply(before.apply(v)));
    }

    /**
     * Function combine : andThen
     *
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
    public <V> FunctionFactory<T, V> andThen(IFunction<? super R, ? extends V> after) {
        NullCheck(after);

        return new FunctionFactory<>((T t) -> after.apply(iFunction.apply(t)));
    }

    /**
     * Return Final combination function.
     *
     * @return
     */
    public IFunction<T, R> getFunction(){
        return iFunction;
    }

    /**
     * Return Rx Function
     *
     * <pre>
     *     Rx Observable support lambda.
     * </pre>
     *
     * @return
     */
    public Function<T, R> getRxFunction() {
        return (T t) -> iFunction.apply(t);
    }
}
