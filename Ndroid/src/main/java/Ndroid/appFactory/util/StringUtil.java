package Ndroid.appFactory.util;

import android.support.annotation.NonNull;

/**
 * String class 의 유틸 클래스
 *
 * Created by Doohyun on 2017. 3. 19..
 */

public class StringUtil {

    /**
     * String 클래스가 비었는지 확인
     *
     * @param text
     * @return
     */
    public static boolean IsEmpty(@NonNull Object text) {
        return MaybeUtil.JustNullable(text.toString()).map(str -> str.isEmpty()).blockingGet(true);
    }
}
