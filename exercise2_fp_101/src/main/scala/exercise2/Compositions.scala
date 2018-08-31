package exercise2

sealed trait Either[A, B] {

  def map[C](f: B => C): Either[A, C]
  def flatMap[C](f: B => Either[A, C]): Either[A, C]
}
case class Left[A, B](a: A) extends Either[A, B] {

  def map[C](f: B => C): Either[A, C] = ???
  def flatMap[C](f: B => Either[A, C]): Either[A, C] = ???
}
case class Right[A, B](b: B) extends Either[A, B] {

  def map[C](f: B => C): Either[A, C] = ???
  def flatMap[C](f: B => Either[A, C]): Either[A, C] = ???
}

object Compositions {

  /* a) implement map - be aware, that is should forward errors (left values):
   *         map(Left[String, Int]("boom")(_ + 1) == Left("boom")
   *         map(Right[String, Int](1)(_ + 1)     == Right(2)
   */



  def testMap[A, B, C](e: Either[A, B], f: B => C): Either[A, C] = ???

  /* b) implement flatMap - be aware, that is should forward errors (left values):
   *         flatMap(Left[String, Int]("boom")(a => Right(a)) == Left("boom")
   *         flatMap(Right[String, Int](1)(a => Left("baam")) == Left("baam")
   *         flatMap(Right[String, Int](1)(a => Right(1 + a)) == Right(2)
   */



  def testFlatMap[A, B, C](e: Either[A, B], f: B => Either[A, C]): Either[A, C] = ???

  /* c) use for-comprehension to apply the given functions in order to the Either value
   */

  def testForComprehension[A, B, C, D, E](e: Either[A, B]) 
                                         (f: B => Either[A, C])
                                         (g: C => Either[A, D])
                                         (h: D => E): Either[A, E] = ???
}
