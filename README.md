# Ndroid

Ndroid is util library, which is supported <B>Android MVP, Lambda combination</B> in alpha version.

**First**, I suggest <B>android MVP</B> because we can separate data model, control and view.<br/>
But we need to share activity instance in presenter and I think that will be induce memory leak in specific situation. <br/>So we need to weak reference has-a relation. 
<br/><br/>
So I made NxPresenter, which is auto weak-ref between activity and presenter. 
And I support NxActivity & NxModeler, which are abstract class for comfortable develop.
<br/><br/>
<B>blog url</B> : http://doohyun.tistory.com/38<br/>
<B>example</B> : https://github.com/skaengus2012/Ndroid#android-mvp
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
<B>blog url</B> : http://doohyun.tistory.com/45<br/>
<B>example</B> : https://github.com/skaengus2012/Ndroid#lambda-combination
<br/><br/>
And I will make new function for this library. Please check support Function.<br/>
<B>Index</B> : https://github.com/skaengus2012/Ndroid#support-function<br/><br/>
Thank you!!
<br/><br/>

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
	compile 'com.github.skaengus2012:Ndroid:v0.0.5-alpha'
}
```
<br/>

# Support Function
<B>1. Android MVP</B> : https://github.com/skaengus2012/Ndroid#android-mvp<br/>
<B>2. Lambda combination</B> : https://github.com/skaengus2012/Ndroid#lambda-combination<br/>
<B>3. MaybeUtil</B> : l<br/>

# Android MVP

Ndroid support android mvp with considering memory leak.

First, You can find NxActivity, NxPresenter, NxModeler

<H2>NxActivity series</H2>
I defined required abstract method in NxActivity series.

This is sample Activity. We need to separte basic function, and I suggest that.
```java
public class LoginActivity extends NxActivity {

    private EditText editId, editPwd;
    
    // This is presenter for LoginActivity
    private LoginPresenter loginPresenter;
    
    /**
     * Define, your activity layout resource id;
     *
     * @return
     */
    @Override
    public int getLayoutResourceId(){
        return R.layout.activity_login;
    }
    
    /**
     * layout xml binding.
     */
    @Override
    public void initView() {
        editId = (EditText) findViewById(R.id.editId);
        editPwd = (EditText) findViewById(R.id.editPwd);
	}
	
 	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		loginPresenter = new LoginPresenter(this);
	}
	
	....
}
```

<h2>NxPresenter</h2>
This presenter manage activity using <B>WeakReference.</B> <br/>
If you use that, you do not need to consider memory leak. <br/>
This is simple presenter.<br/><br/>
And attention <B>observer</B> in presenter. Implement observer in presenter, not activity.

```java
public class LoginPresenter extends NxActivityPresenter<T> implements ILoginServiceObserver {

	public LoginPresenter(LoginActivity loginActivity) {
        super(loginActivity);
	}
	
	/**
     * Presenter method example.
     *
     * @param view
     */
	 public void clickLoginButton(View view) {
	 	// simple activity action.
	 	run(LoginActivity::setClickDisableLoginButton);	// run(loginActivity -> loginActivity.setClickDisableLoginButton());
		
		// runOnUiThread activity action.
		runOnUiThread(LoginActivity::setClickDisableLoginButton);
	 }
}
```
<h2>NxModeler</h2>
I think that model likes Service, Component in Spring Framework required while devloping app.<br/>
So I made modeler for busines logic.

```java
public class DummyModeler extends NxModeler {

    private DummyModeler() {
    }

    private static final class ManagerHolder {
        private static DummyModeler dummyModeler = new DummyModeler();
    }

    public static DummyModeler GetInstance() {
        return ManagerHolder.dummyModeler;
    }

    public void introduceModelerMethod() {
        // Check example.
        {
            Integer a = 10;
            Check(a != 5);       // throws RuntimeException.
        }

        // NullCheck example.
        {
            Integer a = null;
            NullCheck(a);       // throws RuntimeException.
        }

        // String empty check example.
        {
            String a = "";
            EmptyToStringCheck(a);       // throws RuntimeException.
        }

        // Container empty check example.
        {
            EmptyContainerCheck(new LinkedList<String>());                      // throws RuntimeException.
            EmptyContainerCheck(new Integer[2]);                                // throws RuntimeException.
            EmptyContainerCheck(new HashMap<String, String>());                 // throws RuntimeException.
        }

        {
            Map<String, Object> param = new HashMap<>();
            PutDefualtValueInMap(param, "Doohyun", () -> 2);

            // Not operate put action, because param have key "doohyun"
            PutDefualtValueInMap(param, "Doohyun", () -> 3);
        }
    }
}
```

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
Observable.fromIterable(subjectRelationList).sorted(
	LambdaUtil.ComparatorBuilder(SubjectRelation::getCompanySubjectSn, (Integer a, Integer b) -> a.compareTo(b)).
		thenComparing(
			LambdaUtil.ComparatorBuilder(SubjectRelation::getMemberName
				, LambdaUtil.ComparatorBuilder((String a, String b) -> a.compareTo(b)).
					nullsFirst().
					reversed())
			).getRx()).
		map(SubjectRelation::getMemberSubjectSn).
		subscribe(System.out::println);
		
```
<br/>
Check, <B>ComparatorBuilder</B>. That is comparator, which is comparing between member val in Object.
```java
LambdaUtil.ComparatorBuilder(SubjectRelation::getMemberName,LambdaUtil.
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
But Ndroid needed to support <B>Observable in Rx</B>. So I check <B>getRx()</B> for Rx Lambda support.
```java
// a % 5 == 0 && a > 40 || a < 20
Observable.range(0, 60).filter(
                LambdaUtil.PredicateBuilder((Integer a) -> a % 5 == 0).
                        and(a -> a > 40).
                        or(a -> a < 20).
                        getRx()).
                subscribe(System.out::println);
```

So we do not check exception while using lambda. <br/>
Please check <B>Ndroid.appFactory.common.function</B> pakage. you can use simple lambda.
