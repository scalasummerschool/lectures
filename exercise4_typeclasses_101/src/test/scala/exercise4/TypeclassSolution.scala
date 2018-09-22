package exercise4

object TypeclassSolution {
  //1) Define a type class Reversable which reverses values
  trait Reversable[T] {
    def reverse(a: T): T
  }

  object Reversable {
    implicit val stringReverse = new Reversable[String] {
      override def reverse(a: String): String = a.reverse
    }

    def reverse[T: Reversable](a: T): T = implicitly[Reversable[T]].reverse(a)
  }

  //2) Provide an instance of Reverse for String

  def testReversableString(str: String) = Reversable.reverse(str)


  //3) Write a type class named Smash that allows to smash values of the same type type together.
  trait Smash[T] {
    def smash(a: T, b: T): T
  }

  object Smash {
    implicit val smashInt = new Smash[Int] {
      override def smash(a: Int, b: Int): Int = a + b
    }

    implicit val smashDouble = new Smash[Double] {
      override def smash(a: Double, b: Double): Double = a * b
    }

    implicit val smashString = new Smash[String] {
      override def smash(a: String, b: String): String = a ++ b
    }

    implicit val smashList = new Smash[List[_]] {
      override def smash(a: List[_], b: List[_]): List[_] = a ++ b
    }

    def smash[T : Smash](a: T, b: T): T = implicitly[Smash[T]].smash(a, b)
  }

  //4) Provide an instance for Smash for the Int and Double types
  // Use addition for the Int type and multiplication for the Double type
  def testSmashInt(a: Int, b: Int): Int = Smash.smash(a, b)

  def testSmashDouble(a: Double, b: Double): Double = Smash.smash(a, b)

  //5) Provide and instance for Smash for String type such that it concatenates the input
  def testSmashString(a: String, b: String): String = Smash.smash(a, b)


  //6) Implement the method so that it consumes reversable and smashable things
  def testSmashReverse[T](a: T, b: T)(rev: Reversable[T], smash: Smash[T]): T = {
    Smash.smash(Reversable.reverse(a)(rev), Reversable.reverse(b)(rev))(smash)
  }
}
