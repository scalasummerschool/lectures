package exercise2

/** An Option indicates if an operation returned a result or not. This is often used when
  * searching for values or when operations can fail and you don't care about the 
  * reason.
  * 
  * The composition functions `map` and `flatMap` are context sensitive. If the Option
  * is a None they ignore any function application since no value is available. On the 
  * other hand, if it is a Some the functions are applied to transform the value.
  */
sealed trait Option[A] {

  def map[B](f: A => B): Option[B]
  def flatMap[B](f: A => Option[B]): Option[B]
}
case class Some[A](a: A) extends Option[A] {

  def map[B](f: A => B): Option[B] = Some(f(a))
  def flatMap[B](f: A => Option[B]): Option[B] = f(a)
}
case class None[A]()     extends Option[A] {

  def map[B](f: A => B): Option[B] = None()
  def flatMap[B](f: A => Option[B]): Option[B] = None()
}

/** Implement your solutions within the test functions. */
object Compositions {

  // a) Compose the given functions.

  def testCompose[A, B, C, D](f: A => B)
                             (g: B => C)
                             (h: C => D): A => D = ???

  // a) Combose the functions using `map` and `flatMap`.

  def testMapFlatMap[A, B, C, D](f: A => Option[B])
                                (g: B => Option[C])
                                (h: C => D): Option[A] => Option[D] = ???

  // a) Combose the functions using for-comprehension.

  def testForComprehension[A, B, C, D](f: A => Option[B])
                                      (g: B => Option[C])
                                      (h: C => D): Option[A] => Option[D] = ???
}
