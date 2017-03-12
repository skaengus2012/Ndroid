package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.function.IBinaryOperator;
import Ndroid.appFactory.common.function.exceptionLambda.IExBinaryOperator;

/**
 * Created by Doohyun on 2017. 3. 12..
 */

public class BinaryOperatorFactory<T> extends BiFunctionFactory<T, T, T> {

    public BinaryOperatorFactory(@NonNull IBinaryOperator<T> iBinaryOperator) {
        super(iBinaryOperator);
    }

    /**
     * Return Exception able UnaryOperator.
     *
     * @return
     */
    public IExBinaryOperator<T> getEx() {
        return (t1, t2) -> get().apply(t1, t2);
    }
}
