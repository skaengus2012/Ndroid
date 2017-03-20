package Ndroid.appFactory.util.business;

import Ndroid.appFactory.util.koreanText.KoreanTextMatcher;
import Njava.modeler.NxModeler;
import io.reactivex.annotations.NonNull;

/**
 * Define StringUtil For Android.
 *
 * Created by Doohyun on 2017. 3. 21..
 */

public class StringUtil extends Njava.util.business.StringUtil {

    /**
     * 한국어 초성 Matcher 를 출력.
     *
     * @param pattern
     * @return
     */
    public static KoreanTextMatcher GetRegexMatcher(@NonNull String pattern) {
        NxModeler.NullCheck(pattern);

        return new KoreanTextMatcher(pattern);
    }
}
