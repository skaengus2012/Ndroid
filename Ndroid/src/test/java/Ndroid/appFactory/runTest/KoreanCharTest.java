package Ndroid.appFactory.runTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Ndroid.appFactory.util.business.koreanChar.KoreanTextMatch;
import Ndroid.appFactory.util.business.koreanChar.KoreanTextMatcher;
import io.reactivex.Observable;

/**
 * 초성검색 사용 라이브러리 테스트
 *
 * Created by Doohyun on 2017. 3. 19..
 */

public class KoreanCharTest {

    /**
     * 초성검색 테스트
     */
    @Test
    public void run() {
        List<String> textList = Arrays.asList("남두현", "테스트", "ndh 테스터");

        KoreanTextMatcher matcherTest = new KoreanTextMatcher("ㅌㅅ");

        Observable.fromIterable(textList).
                map(text -> matcherTest.matches(text)).
                subscribe(matches -> Observable.fromIterable(matches).subscribe(
                        m -> System.out.println(m.index() + " " + (m.index() + m.length()))));

/**
        KoreanTextMatcher matcher = new KoreanTextMatcher("ㄱㅇ");
        for (KoreanTextMatch match : matcher.matches(text)) {
            spannedText.setSpan(new ForegroundColorSpan(Color.RED),
                    match.index(), match.index() + match.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
 */
    }
}
