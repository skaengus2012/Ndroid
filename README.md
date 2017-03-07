# Ndroid

Ndroid is util library, which is supported <B>Android MVP, Lambda combination</B> in alpha version.

**First**, I suggest <B>android MVP</B> because we can separate data model, control and view.
But we need to share activity instance in presenter and I think that will be induce memory leak in specific situation. So we need to weak reference has-a relation. 

So I made NxPresenter, which is auto weak-ref between activity and presenter. 
And NxActivity and NxModeler are abstract class for comfortable develop.

blog url : http://doohyun.tistory.com/38

<BR/>
**Secondly**, I made <B>Lambda combination</B> util for Android. 
Android support default method in JAVA8 at API >= 24. 
<Br/>
So we can use lambda, but we can not combination default and static method. 
<Br/>
And I tried to find that in Rx, but I could not find.
<Br/>
So I published <B>LambdaUtil</B> for combination. We can build lambda.

blog url : 

Thank you!!

