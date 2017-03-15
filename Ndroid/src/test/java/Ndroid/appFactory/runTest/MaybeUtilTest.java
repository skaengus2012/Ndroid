package Ndroid.appFactory.runTest;

import org.junit.Test;

import java.util.HashMap;

import Ndroid.appFactory.util.MaybeUtil;
import io.reactivex.Maybe;

/**
 * Maybe Util 관련 기능 테스트를 위한 클래스.
 *
 * Created by Doohyun on 2017. 3. 15..
 */

public class MaybeUtilTest {

    /**
     * Maybe Util tutorial.
     */
    @Test
    public void runMaybeUtil() {
        String test = "Test", nullValue = null;

        // NullAble just support.

        MaybeUtil.JustNullable(test).subscribe(System.out::println);

        Maybe<String> nullValueMaybe = MaybeUtil.JustNullable(nullValue);

        nullValueMaybe.subscribe(System.out::println);
        MaybeUtil.RunEmptyMaybe(nullValueMaybe, () -> System.out.println("That value is null!!"));
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
        MaybeUtil.JustInMap(testMap, "key1").subscribe(System.out::println);

        // Run maybe, if targetMap have key-value.
        MaybeUtil.RunMaybeInMap(testMap, "key1", System.out::println);
        MaybeUtil.RunMaybeInMap(testMap, "key4", System.out::println);
    }

    /**
     * String to boxed object parse support.
     */
    @Test
    public void parseSupport() {
        MaybeUtil.ParseInteger("1").subscribe(System.out::println);
        MaybeUtil.ParseInteger("String Text").subscribe(System.out::println);
    }
}
