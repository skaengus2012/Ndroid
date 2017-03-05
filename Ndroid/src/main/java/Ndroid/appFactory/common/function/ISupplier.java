package Ndroid.appFactory.common.function;

/**
 * JAVA8 Supplier Support
 *
 * <pre>
 *     The Original Supplier in JAVA8 can use api >= 24
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
@FunctionalInterface
public interface ISupplier<T> {
    T accept();
}
