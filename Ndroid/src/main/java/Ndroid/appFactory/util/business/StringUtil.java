package Ndroid.appFactory.util.business;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import Ndroid.appFactory.util.function.MaybeUtil;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * String class 의 유틸 클래스
 * <p>
 * Created by Doohyun on 2017. 3. 19..
 */

public class StringUtil {

    /**
     * String 클래스가 비었는지 확인
     *
     * @param text
     * @return
     */
    @NonNull
    public static boolean IsEmpty(@NonNull Object text) {
        return MaybeUtil.JustNullable(text.toString()).map(str -> str.isEmpty()).blockingGet(true);
    }

    /**
     * UUID 를 출력한다.
     * <p>
     * <pre>
     *     36비트 UUID 를 출력한다. (- 포함.)
     * </pre>
     *
     * @return
     */
    @NonNull
    public static String GetUUID36() {
        return UUID.randomUUID().toString();
    }

    /**
     * UUID 를 출력한다.
     * <p>
     * <pre>
     *     32비트 UUID 를 출력한다. (-제외)
     * </pre>
     *
     * @return
     */
    public static String GetUUID32() {
        return GetUUID36().replaceAll("-", "");
    }

    /**
     * String 을 받아 Character 목록을 출력한다.
     *
     * @param text
     * @return
     */
    public static List<Character> GetCharacterList(@NonNull String text) {
        return MaybeUtil.JustNullable(text).map(param -> Observable.
                range(0, param.length()).
                map(param::charAt).
                collect(() -> new ArrayList<Character>(), (list, c) -> list.add(c))).
                blockingGet(Single.just(new ArrayList<>())).
                blockingGet();
    }
}
