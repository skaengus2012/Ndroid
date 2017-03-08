package Ndroid.appFactory.runTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Ndroid.appFactory.common.function.IPredicate;
import Ndroid.appFactory.testVo.SubjectRelation;
import Ndroid.appFactory.util.LambdaUtil;
import io.reactivex.Observable;

/**
 * Function support class Test
 *
 * Created by Doohyun on 2017. 3. 5..
 */

public class LambdaCombineTest {

    @Test
    public void rxTest() {
        // a % 5 == 0 && a > 40 || a < 20
        Observable.range(0, 60).filter(
                LambdaUtil.PredicateBuilder((Integer a) -> a % 5 == 0).
                        and(a -> a > 40).
                        or(a -> a < 20).
                        getRx()).
                subscribe(System.out::println);
    }

    /**
     * Function combination Test!
     */
    @Test
    public void functionFactoryTest() {
        {
            // f(x) = x + 2
            // g(x) = x * 8;
            // f(g(x)) = (x * 8) + 2
            System.out.println(
                    LambdaUtil.FunctionBuilder((Integer a) -> a + 2).
                            compose((Integer a) -> a * 8).
                            getFunction().
                            apply(10));
        }

        {
            // f(x) = x + 2
            // g(x) = x * 8;
            // g(f(x)) = (x + 2) * 8
            System.out.println(
                    LambdaUtil.FunctionBuilder((Integer a) -> a + 2).
                            andThen((Integer a) -> a * 8).
                            getFunction().
                            apply(10));
        }

        {
            // f(x, y) = (x * y) + 10
            // g(x) = x * 10
          System.out.println(
                    LambdaUtil.FunctionBuilder((Integer a, Integer b) -> (a * b) + 10).
                            andThen((Integer c) -> c * 10).
                            get().apply(2, 5));
        }
    }

    /**
     * Comparator combination Test!
     */
    @Test
    public void comparatorFactoryTest() {
        List<SubjectRelation> subjectRelationList = Arrays.asList(
                new SubjectRelation(1, 1001, "Doohyun Nam", 1)
                , new SubjectRelation(1, 1002, "Dolkin", 2)
                , new SubjectRelation(1, 1003, "hshawng", 1)
                , new SubjectRelation(1, 1004, "spKwon", 1)
                , new SubjectRelation(2, 1005, "redCamel", 3)
                , new SubjectRelation(2, 1006, "broDuck", 4)
                , new SubjectRelation(3, 1005, null, 3)
        );

        // order by companySubjectSn, memberName DESC
        Comparator<SubjectRelation> comparator = LambdaUtil.ComparatorBuilder(
                LambdaUtil.CreateKeyComparator(SubjectRelation::getCompanySubjectSn
                        , LambdaUtil.ComparatorBuilder((Integer a, Integer b) -> a.compareTo(b)).reversed()
                                .get())).
                thenComparing(
                        LambdaUtil.CreateKeyComparator(SubjectRelation::getMemberName,
                                LambdaUtil.ComparatorBuilder((String a, String b) -> a.compareTo(b)).
                                        nullsFirst().reversed().get())).get();

        // order by companySubjectSn, memberName DESC
        Collections.sort(subjectRelationList, comparator);
    }

    /**
     * Predicate combination test!
     */
    @Test
    public void predicateFactoryTest(){
        {
            // a >= 5 && a < 10 || a == 0

            IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).
                    and(a -> a < 10).
                    or(a -> a == 0).
                    get();

            System.out.println(predicate.test(6));
            System.out.println(predicate.test(1));
            System.out.println(predicate.test(20));
        }

        {
            // !((a >= 5 && a < 10) || (a >= 200 && a < 300))
            IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder(
                    LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).and(a -> a < 10).get()).
                    or(LambdaUtil.PredicateBuilder((Integer a) -> a >= 200).and(a -> a < 300).get()).
                    negative().
                    get();
        }

        {
            // BiPredicate support!
            LambdaUtil.PredicateBuilder((Integer a, Integer b) -> a + b > 0).and((Integer a,Integer b) -> a >= b).get();
        }
    }
}
