package Ndroid.appFactory.launcher;

import org.junit.Test;

import java.util.HashMap;

import Njava.util.business.ContainerUtil;
import Njava.util.business.StringUtil;
import Njava.util.function.MaybeUtil;
import io.reactivex.Maybe;

/**
 * Maybe Util 관련 기능 테스트를 위한 클래스.
 *
 * Created by Doohyun on 2017. 3. 15..
 */

public class MaybeUtilTest {

    /**
     * Maybe simple Test.
     */
    @Test
    public void runMaybeTutorial() {
        // 빈 Maybe 생성.
        Maybe<String> emptyMaybe = Maybe.empty();
        // 유효 값 Maybe 생성.
        Maybe<String> validMaybe = Maybe.just("Test1");

        // 값이 유효할 때 로그 출력.
        validMaybe.subscribe(System.out::println);

        // 실제 데이터 출력.
        String returnValue = validMaybe.blockingGet("Default Value");
    }

    /**
     * Maybe Util tutorial.
     */
    @Test
    public void runMaybeUtil() {
        String test = "Test", nullValue = null;

        // Null 을 포함할 수 있는 just!
        MaybeUtil.JustNullable(test).subscribe(System.out::println);

        Maybe<String> nullValueMaybe = MaybeUtil.JustNullable(nullValue);
        nullValueMaybe.subscribe(System.out::println);

        // 오직 비어있는 Maybe 일 때만 실행!
        MaybeUtil.SubscribeEmpty(nullValueMaybe, () -> System.out.println("That value is null!!"));

        // Maybe 가 유효하다면, 두번째 param, 아니라면 세번째 param 사용
        MaybeUtil.Subscribe(nullValueMaybe, System.out::println, () -> System.out.println("That value is null!!"));
    }

    /**
     * Map support using maybe!
     */
    @Test
    public void runMapSupport() {
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("key1", 1);
        testMap.put("key2", 1);
        testMap.put("key3", 1);

        // Extract maybe value in Map
        ContainerUtil.JustInMap(testMap, "key1").subscribe(System.out::println);

        // Run maybe, if targetMap have key-value.
        ContainerUtil.RunMaybeInMap(testMap, "key1", System.out::println);
        ContainerUtil.RunMaybeInMap(testMap, "key4", System.out::println);

        // Put value, if key not contain in map.
        ContainerUtil.PutEmptyKeyValueInMap(testMap, "key7", () -> 5);
        ContainerUtil.JustInMap(testMap, "key7").subscribe(System.out::println);

    }

    /**
     * String to boxed object parse support.
     */
    @Test
    public void parseSupport() {
        // Parse Integer
        StringUtil.ParseInteger("1").subscribe(System.out::println);
        StringUtil.ParseInteger("String Text").subscribe(System.out::println);

        // Parse Boolean
        StringUtil.ParseBoolean("false").subscribe(System.out::println);

        // Parse Double
        StringUtil.ParseDouble("0.0111111").subscribe(System.out::println);
    }
}
