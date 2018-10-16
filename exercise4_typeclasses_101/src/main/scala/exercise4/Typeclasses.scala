package exercise4

object Typeclasses {

  // a) Define a type class Reversable which reverses values.

  trait Reversable[T]

  object Reversable {
    def reverse[T: Reversable](a: T): T = ???
  }

  // b) Provide an instance of Reverse for String.



  // apply your solution-typeclass from (a) here, DO NOT change the signature
  def testReversableString(str: String): String = str

  // c) Define a type class Smash so that it provides a function  to smash values of the same type together.

  trait Smash[T]

  object Smash {
    def smash[T : Smash](a: T, b: T): T = ???
  }

  // d) Provide an instance for Smash for the Int and Double types.
  //    Use addition for the Int type and multiplication for the Double type.



  // apply your solution-typeclass from (d) here, DO NOT change the signature
  def testSmashInt(a: Int, b: Int): Int = a

  // apply your solution-typeclass from (d) here, DO NOT change the signature
  def testSmashDouble(a: Double, b: Double): Double = a


  // e) Provide an instance for Smash for String type such that it concatenates the input.



  // apply your solution-typeclass from (e) here, DO NOT change the signature
  def testSmashString(a: String, b: String): String = a
}
