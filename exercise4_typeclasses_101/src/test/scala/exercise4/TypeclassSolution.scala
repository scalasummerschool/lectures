package exercise4

object TypeclassSolution {

  trait Reversable[A] {

    def reverse(a: A): A
  }
 
  object Reversable {

    implicit val stringReverse = new Reversable[String] {
      override def reverse(a: String): String = a.reverse
    }

    def reverse[A: Reversable](a: A): A = implicitly[Reversable[A]].reverse(a)
  }


  trait Smash[A] {

    def smash(a: A, b: A): A
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

    def smash[A : Smash](a: A, b: A): A = implicitly[Smash[A]].smash(a, b)
  }


  def testReversableString(str: String) = Reversable.reverse(str)
  def testSmashInt(a: Int, b: Int): Int = Smash.smash(a, b)
  def testSmashDouble(a: Double, b: Double): Double = Smash.smash(a, b)
  def testSmashString(a: String, b: String): String = Smash.smash(a, b)
}
