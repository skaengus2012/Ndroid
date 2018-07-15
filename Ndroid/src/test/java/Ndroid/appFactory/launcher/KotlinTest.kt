package Ndroid.appFactory.launcher

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * Test for Kotlin
 *
 * @author Doohyun
 */
class KotlinTest {

    /**
     * Test Hello world
     */
    @Test fun testHelloWorld() = println("Hello Kotlin")

    /**
     * Test Reactive X
     */
    @Test fun testReactiveX() {
        Single.fromCallable{
                    Thread.sleep(1000)
                    "Hello ReactiveX"
                }
                .subscribeOn(Schedulers.trampoline())
                .subscribe { message
                    ->
                    println(message)
                }
    }
}