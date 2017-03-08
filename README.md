=# Ndroid

Ndroid is util library, which is supported <B>Android MVP, Lambda combination</B> in alpha version.

**First**, I suggest <B>android MVP</B> because we can separate data model, control and view.<br/>
But we need to share activity instance in presenter and I think that will be induce memory leak in specific situation. <br/>So we need to weak reference has-a relation. 
<br/><br/>
So I made NxPresenter, which is auto weak-ref between activity and presenter. 
And I support NxActivity & NxModeler, which are abstract class for comfortable develop.
<br/><br/>
<B>blog url</B> : http://doohyun.tistory.com/38
<br/><br/>
**Secondly**, I made <B>Lambda combination</B> util for Android. 
Android support default method in JAVA8 at API >= 24. 
<Br/>
So we can use lambda, but we can not combination default and static method. 
<Br/>
And I tried to find that in Rx, but I could not find.
<Br/>
<Br/>
So I published <B>LambdaUtil</B> for combination. We can build lambda.
<Br/>
<Br/>
<B>blog url</B> : 
<br/><br/>
Thank you!!
<br/>

# Getting started

<B>STEP1</B> : Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

<B>STEP2</B> : Add the dependency:<br/>
```gradle
dependencies {
	compile 'com.github.skaengus2012:Ndroid:v0.0.4.1-alpha'
}
```
<br/>

# Lambda combination

I made builder for lambda because combination method in lambda can use at api >= 24.<br/>
So <B>Ndroid</B> support default combination method using factory & builder pattern.<br/>
Please reference next.

<H2>Predicate Example.</H2>
```java
// a >= 5 && a < 10 || a == 0

IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).
                    				and(a -> a < 10).
                    				or(a -> a == 0).
                   				get();
		    
// !((a >= 5 && a < 10) || (a >= 200 && a < 300))
IPredicate<Integer> predicate = LambdaUtil.PredicateBuilder(
                    LambdaUtil.PredicateBuilder((Integer a) -> a >= 5).and(a -> a < 10).get()).
                    or(LambdaUtil.PredicateBuilder((Integer a) -> a >= 200).and(a -> a < 300).get()).
                    negative().
                    get();
		    
// BiPredicate support!
LambdaUtil.PredicateBuilder((Integer a, Integer b) -> a + b > 0).and((Integer a,Integer b) -> a >= b).get();

```

<H2>Comparator Example</H2>
ComparatorBuilder support <B>null</B> value.<br> We can control null priority using ComparatorBuilder.
```java
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

```
<br/>
Check, <B>keyComparator</B>. That is comparator, which is comparing between member val in Object.
```java
LambdaUtil.CreateKeyComparator(SubjectRelation::getMemberName,LambdaUtil.
			ComparatorBuilder((String a, String b) -> a.compareTo(b)).
				nullsFirst().
				reversed().
				get());
				
```

<H2>Function Example</H2>
```java
{
	// f(x) = x + 2
	// g(x) = x * 8;
	// f(g(x)) = (x * 8) + 2
	LambdaUtil.FunctionBuilder((Integer a) -> a + 2).
                   compose((Integer a) -> a * 8).
                   get().
                   apply(10);
}

{
	// f(x) = x + 2
	// g(x) = x * 8;
	// g(f(x)) = (x + 2) * 8
	LambdaUtil.FunctionBuilder((Integer a) -> a + 2).
                   andThen((Integer a) -> a * 8).
                   get().
                   apply(10);
}

{
	// f(x, y) = (x * y) + 10
	// g(x) = x * 10
	LambdaUtil.FunctionBuilder((Integer a, Integer b) -> (a * b) + 10).
                   andThen((Integer c) -> c * 10).
                   get().
		   apply(2, 5));
}
```

<H2>Rx Lambda supprt</H2>
I hate RxJava2 lambda because they made method with Exception in functional interface.<br/><br/>
So I support functional interface method not including Exception.<br/><br/>
But this library need to support Observable in Rx. So I check <B>getRx()</B> for Rx Lambda support.<br/><br/>
```java
// a % 5 == 0 && a > 40 || a < 20

Observable.range(0, 60).filter(
                LambdaUtil.PredicateBuilder((Integer a) -> a % 5 == 0).
                        and(a -> a > 40).
                        or(a -> a < 20).
                        getRx()).
                subscribe(System.out::println);
```
