package exercise4

object Typeclasses {

    // a) Define a type class Reversable which reverses values.

    trait Reversable[T]

    object Reversable {
      def reverse[T: Reversable](a: T): T = ???
    }

    // b) Provide an instance of Reverse for String.



    def testReversableString(str: String) = ???

    // c) Write a type class named Smash that allows to smash values of the same type together.

    trait Smash[T]

    object Smash {
      def smash[T : Smash](a: T, b: T): T = ???
    }

    // d) Provide an instance for Smash for the Int and Double types.
    //    Use addition for the Int type and multiplication for the Double type.



    def testSmashInt(a: Int, b: Int): Int = ???

    def testSmashDouble(a: Double, b: Double): Double = ???


   // e) Provide an instance for Smash for String type such that it concatenates the input.



   def testSmashString(a: String, b: String): String = ???
}
