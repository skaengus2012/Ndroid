package Ndroid.appFactory.common.function.extension.combineFactory;

import android.support.annotation.NonNull;

import Ndroid.appFactory.common.androidMvc.model.NxModeler;

/**
 * Lambda combine Factory define
 *
 * Created by Doohyun on 2017. 3. 8..
 */
public abstract class CombineFactory<T, V> extends NxModeler {
    private T lambda;

    public CombineFactory(@NonNull T lambda) {
        NullCheck(lambda);
        this.lambda = lambda;
    }

    /**
     * Return Rx style Lambda
     *
     * @return
     */
    public abstract V getRx();

    /**
     * Return simple Lambda
     *
     * @return
     */
    public final T get() {
        return lambda;
    }
}
