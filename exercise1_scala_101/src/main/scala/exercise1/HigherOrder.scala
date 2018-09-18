package exercise1

/** Write your solutions in stand-alone function. */
object HigherOrder {

  val plus: (Int, Int) => Int = _ + _
  val multiply: (Int, Int) => Int = _ * _

  /* a) Write a function which takes an `f: (Int, Int) => Int`, its parameters `a` and `b`
   *    and a multiplication factor `n` and returns n * f(a, b). Let's call it `nTimes`.
   */



  def testNTimes(f: (Int, Int) => Int, a: Int, b: Int, n: Int): Int = n

  /* b) Write an anonymous function, a function without identifier ((a, b) => ???), `f` applied to `nTimes` which
   *    does the following:
   *          if (a > b) a else b
   */
  def testAnonymousNTimes(a: Int, b: Int, n: Int): Int = a
}
