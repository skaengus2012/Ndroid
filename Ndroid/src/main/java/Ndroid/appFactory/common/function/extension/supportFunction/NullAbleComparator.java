package Ndroid.appFactory.common.function.extension.supportFunction;

import android.support.annotation.NonNull;

import java.util.Comparator;

import Ndroid.appFactory.common.modeler.NxModeler;

/**
 * Support null key comparator
 *
 * Created by Doohyun on 2017. 3. 2..
 */

public class NullAbleComparator<T> extends NxModeler implements Comparator<T> {
    private Boolean nullFirst = false;
    private Comparator<T> real;

    public NullAbleComparator(@NonNull Comparator<T> real) {
        NullCheck(real);
        this.real = real;
    }

    public NullAbleComparator(@NonNull Comparator<T> real, @NonNull Boolean nullFirst) {
        NullCheck(real);
        NullCheck(nullFirst);

        this.real = real;
        this.nullFirst = nullFirst;
    }

    public Comparator<T> getReal(){
        return real;
    }

    @Override
    public int compare(T a, T b) {
        if (a == null) {
            return (b == null) ? 0 : (nullFirst ? -1 : 1);
        } else if (b == null) {
            return nullFirst ? 1: -1;
        } else {
            return (real == null) ? 0 : real.compare(a, b);
        }
    }
}
