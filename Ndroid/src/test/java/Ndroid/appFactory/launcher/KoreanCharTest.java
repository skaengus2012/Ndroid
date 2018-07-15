package Ndroid.appFactory.launcher;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import Ndroid.appFactory.util.business.StringUtil;
import Ndroid.appFactory.util.koreanText.KoreanTextMatcher;
import io.reactivex.Observable;

/**
 * 초성검색 사용 라이브러리 테스트
 * <p>
 * Created by Doohyun on 2017. 3. 19..
 */

public class KoreanCharTest {

    /**
     * 초성검색 테스트
     *
     *  KoreanTextMatcher matcher = StringUtil.GetRegexMatcher("ㄱㅇ");

         for (KoreanTextMatch match : matcher.matches(text)) {
             spannedText.setSpan(new ForegroundColorSpan(Color.RED),
             match.index(), match.index() + match.length(),
             Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
     */
    @Test
    public void run() {
        List<String> textList = Arrays.asList("남두현12", "테스트", "ndh 테스터");

        KoreanTextMatcher matcherTest = StringUtil.GetRegexMatcher("두현");

        // 매치가 되는 스트링의 위치를 찾음.
        Observable.fromIterable(textList).
                map(text -> matcherTest.matches(text)).
                subscribe(matches -> Observable.fromIterable(matches).subscribe(m ->
                        System.out.println(m.index() + " " + (m.index() + m.length()))));
    }
}
