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

public class FunctionInterfaceFactoryTest {

    @Test
    public void runTest() {
        comparatorFactoryTest();
    }

    public void comparatorFactoryTest() {
        List<SubjectRelation> subjectRelationList = Arrays.asList(
                new SubjectRelation(1, 1001, "Doohyun Nam", 1)
                , new SubjectRelation(1, 1002, "Dolkin", 2)
                , new SubjectRelation(1, 1003, "hshawng", 1)
                , new SubjectRelation(1, 1004, "spKwon", 1)
                , new SubjectRelation(2, 1005, "Other Person1", 3)
                , new SubjectRelation(2, 1006, null, 4)
        );

        // 회사 순번으로 내림차순 정렬 후, 이름으로 오름정렬 (null last).
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
     * predicate Factory 에 대한 테스트 정의.
     */
    public void predicateFactoryTest(){
        {
            // a >= 5 && a < 10 || a == 0

            IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).
                    and(null).
                    or(a -> a == 0).
                    getPredicate();

            System.out.println(predicate.test(6));
            System.out.println(predicate.test(0));
            System.out.println(predicate.test(20));
        }

        {
            // (a >= 5 && a < 10) || (a >= 200 && a < 300)
            IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder(
                    LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).and(a -> a < 10).getPredicate()).
                    or(LambdaUtil.PredicateBuilder((Integer a) -> a >= 200).and(a -> a < 300).getPredicate()).
                    negative().
                    getPredicate();

            System.out.println(predicate.test(6));
            System.out.println(predicate.test(0));
            System.out.println(predicate.test(250));
        }
    }
}
