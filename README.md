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
<B>example</B> : https://github.com/skaengus2012/N-java#lambda-combination
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
    compile 'com.github.skaengus2012:Ndroid:v0.1.5-beta'
}
```
<br/>

# Support Function

<H2>N-java</H2>
<B>1. Lambda combination</B> : https://github.com/skaengus2012/N-java/wiki/N-java-v0.2-wiki#lambda-combination<br/>
<B>2. MaybeUtil</B> : https://github.com/skaengus2012/N-java/wiki/N-java-v0.2-wiki#maybeutil<br/>
<B>3. TimeUtil & TimeBuilder </B> : https://github.com/skaengus2012/N-java/wiki/N-java-v0.2-wiki#timeutil--timebuilder <br/>
<B>4. CheckUtil </B> : https://github.com/skaengus2012/N-java/wiki/N-java-v0.2-wiki#checkutil <br/>

<H2>Ndroid</H2>
<B>1. Android MVP</B> : https://github.com/skaengus2012/Ndroid#android-mvp <br/><br/>

# Android MVP

Ndroid support android mvp with considering memory leak.

First, You can find NxActivity, NxPresenter, NxComponent

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
      public int getLayoutResourceId() {
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
}
```
<H2>NxPresenter</H2>
This presenter manage activity using <B>WeakReference.</B><br/>
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
<h2>NxComponent</h2>
I think that model likes Service, Component in Spring Framework required while devloping app.<br/>
So I made modeler for busines logic.

```java
public class DummyModeler extends NxComponent {

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
