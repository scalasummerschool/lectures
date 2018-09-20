package exercise4

object Typeclasses {
    //1) Define a type class Reversable which reverses values
    trait Reversable[T]

    object Reversable {
      def reverse[T: Reversable](a: T): T = ???
    }

    //2) Provide an instance of Reverse for String


    //3) Write a type class named Smash that allows to smash values of the same type type together.
    trait Smash[T]

    object Smash {
      def smash[T : Smash](a: T, b: T): T = ???
    }

    //4) Provide an instance for Smash for the Int and Double types
    // Use addition for the Int type and multiplication for the Double type
    def testSmashInt(a: Int, b: Int): Int = ???

    def testSmashList[T](a: List[T], b: List[T]): String = ???


   //5) Provide and instance for Smash for String type such that it concatenates the input
   def testSmashString(a: String, b: String): String = ???

   //6) Provide an instance for Smash for List[T] which concatenates the reverse of the inputs
   def testSmashListReverse[T](a: List[T], b: List[T]): String = ???
}
