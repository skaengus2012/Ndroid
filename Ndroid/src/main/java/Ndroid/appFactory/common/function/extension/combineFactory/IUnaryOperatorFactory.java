package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.function.IUnaryOperator;
import Ndroid.appFactory.common.function.exceptionLambda.IExUnaryOperator;

/**
 * UnaryOperator Builder
 *
 * Created by Doohyun on 2017. 3. 12..
 */

public class IUnaryOperatorFactory<T> extends FunctionFactory<T, T>{

    public IUnaryOperatorFactory(@NonNull IUnaryOperator<T> iUnaryOperator) {
        super(iUnaryOperator);
    }

    /**
     * Return Exception able UnaryOperator.
     *
     * @return
     */
    public IExUnaryOperator<T> getEx() {
        return t -> get().apply(t);
    }
}
