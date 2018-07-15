@file:JvmName("MockHelper")
package Ndroid.appFactory.model

/**
 * For Extension Test
 *
 * @author Doohyun
 */

/**
 * print [obj]
 *
 * @param obj
 */
fun printSomething(obj : Any) = println(obj)

/**
 * Extension User
 *
 * print User.name
 */
fun User.printName() = print(name)