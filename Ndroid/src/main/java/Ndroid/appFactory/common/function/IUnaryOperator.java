package Ndroid.appFactory.common.function;

/**
 * Java8 UnaryOperator 정의
 *
 * <pre>
 *     java 8 의 UnaryOperator 은 >=24 부터 사용 가능
 * </pre>
 *
 * Created by Doohyun on 2017. 3. 5..
 */
@FunctionalInterface
public interface IUnaryOperator<T> extends IFunction<T, T> {

    /**
     * 본인 자체를 내보내는 기능 정의.
     * @param <T>
     * @return
     */
    static <T> IUnaryOperator<T> identity() {
        return t -> t;
    }
}
