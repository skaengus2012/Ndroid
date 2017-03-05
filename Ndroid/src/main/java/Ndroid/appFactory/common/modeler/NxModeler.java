package Ndroid.appFactory.common.modeler;

import java.util.Collection;
import java.util.Map;

import Ndroid.appFactory.util.ContainerUtil;


/**
 * 모델을 담당하는 클래스는 이곳에 제작한다.
 *
 * <pre>
 *      스프링의 서비스와 같은 그 것!
 *      모든 모델러는 해당 클래스를 상속받아야함!
 *      참고 : http://doohyun.tistory.com/38
 *
 *      단순 MVP 모델에서 사용하기 위한 모델러 역할 포함, 비지니스 로직만 전문으로 사용하는 클래스에서 사용하자!
 * </pre>
 *
 * Created by Doohyun on 2017. 1. 29..
 */

public abstract class NxModeler {

    private static final String ERROR_BAD_ASSESS = "[ERROR] BAD DATA ASSESS";

    /**
     * <pre>
     *     boolean 논리 체크
     * </pre>
     *
     * @param check
     */
    public final static void Check(boolean check) {
        if (!check) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * <pre>
     * 설명 : null 체크
     * </pre>
     *
     * @param obj
     *
     * @modified 2016. 9. 19. by leehw
     */
    public final static void NullCheck(Object obj) {
        if (obj == null) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * <pre>
     * 설명 : toString 공백 체크
     * </pre>
     *
     * @param obj
     * @param message
     */
    public final static void EmptyToStringCheck(Object obj, String message) {

        if (obj == null) {
            // obj 자체 null 체크
            throw new RuntimeException("[예외] 잘못된 접근");
        }

        final String toString = obj.toString();

        if (toString == null || toString.isEmpty()) {
            // toString 체크
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * <pre>
     * 설명 : 리스트 empty 체크
     * </pre>
     *
     * @param list
     */
    public final static <T> void EmptyContainerCheck(Collection<T> list) {
        if (ContainerUtil.IsEmpty(list)) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * <pre>
     * 설명 : 배열 empty 체크
     * </pre>
     *
     * @param array
     */
    public final static <T> void EmptyContainerCheck(T[] array) {
        if (ContainerUtil.IsEmpty(array)) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * <pre>
     * 설명 : 배열 empty 체크
     * </pre>
     *
     * @param dataSet
     * @param message
     */
    public final static <T, R> void EmptyContainerCheck(Map<T, R> dataSet, String message) {
        if (ContainerUtil.IsEmpty(dataSet)) {
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }

    /**
     * <pre>
     * 맵의 key 를 확인하고 기본 값을 넣는다.
     * </pre>
     *
     * @param map
     * @param key
     * @param defaultVale
     */
    public static <T, R> void PutDefualtValueInMap(Map<T, R> map, T key, R defaultVale) {
        try {
            if (!map.containsKey(key)) {
                map.containsKey(defaultVale);
            }
        } catch (NullPointerException e) {
            // map 이 null 일 수 있음.
            throw new RuntimeException(ERROR_BAD_ASSESS);
        }
    }
}
