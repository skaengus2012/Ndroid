package Ndroid.appFactory.launcher;

import org.junit.Test;

import Ndroid.appFactory.model.MockHelper;
import Ndroid.appFactory.model.User;

/**
 * Kotlin & Java Support Test
 *
 * @author Doohyun
 */
public class JavaSupportKotlinTest {

    /**
     * Using Static method in Kotlin
     */
    @Test
    public void testStaticMethod() {
        User user = User.create(true, "Doohyun");
        System.out.println(user.getName());
    }

    /**
     * Using Static Property in Kotlin
     */
    @Test
    public void testStaticProperty() {
        System.out.println(String.format("default Name : %s",  User.defaultUser.getName()));
    }

    /**
     * Test Extension method
     */
    @Test
    public void testExtension() {
        MockHelper.printSomething("print something");

        MockHelper.printName(User.create(true, "Doohyun"));
    }
}
