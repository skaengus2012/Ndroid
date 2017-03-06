package Ndroid.appFactory.runTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Ndroid.appFactory.common.function.IPredicate;
import Ndroid.appFactory.testVo.SubjectRelation;
import Ndroid.appFactory.util.LambdaUtil;

/**
 * Function support class Test
 *
 * Created by Doohyun on 2017. 3. 5..
 */

public class LambdaCombineTest {

    @Test
    public void runTest() {
        functionFactoryTest();
    }

    /**
     * Function combination Test!
     */
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
    }

    /**
     * Comparator combination Test!
     */
    public void comparatorFactoryTest() {
        List<SubjectRelation> subjectRelationList = Arrays.asList(
                new SubjectRelation(1, 1001, "Doohyun Nam", 1)
                , new SubjectRelation(1, 1002, "Dolkin", 2)
                , new SubjectRelation(1, 1003, "hshawng", 1)
                , new SubjectRelation(1, 1004, "spKwon", 1)
                , new SubjectRelation(2, 1005, "Other Person1", 3)
                , new SubjectRelation(2, 1006, null, 4)
        );

        // order by companySubjectSn, memberName DESC
        Collections.sort(subjectRelationList, LambdaUtil.ComparatorBuilder(
                        LambdaUtil.CreateKeyComparator(SubjectRelation::getCompanySubjectSn
                                , LambdaUtil.ComparatorBuilder((Integer a, Integer b) -> a.compareTo(b)).reversed()
                                        .getComparator())).
                        thenComparing(
                                LambdaUtil.CreateKeyComparator(SubjectRelation::getMemberName,
                                        LambdaUtil.ComparatorBuilder((String a, String b) -> a.compareTo(b)).
                                                nullsFirst().reversed().getComparator())).getComparator());
    }

    /**
     * Predicate combination test!
     */
    public void predicateFactoryTest(){
        {
            // a >= 5 && a < 10 || a == 0

            IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).
                    and(a -> a < 10).
                    or(a -> a == 0).
                    getPredicate();

            System.out.println(predicate.test(6));
            System.out.println(predicate.test(1));
            System.out.println(predicate.test(20));
        }

        {
            // !((a >= 5 && a < 10) || (a >= 200 && a < 300))
            IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder(
                    LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).and(a -> a < 10).getPredicate()).
                    or(LambdaUtil.PredicateBuilder((Integer a) -> a >= 200).and(a -> a < 300).getPredicate()).
                    negative().
                    getPredicate();

            System.out.println(predicate.test(6));
            System.out.println(predicate.test(0));
            System.out.println(predicate.test(250));
        }

        {
            // BiPredicate support!
            LambdaUtil.PredicateBuilder((Integer a, Integer b) -> a + b > 0).and((Integer a,Integer b) -> a >= b).getPredicate();
        }
    }
}
