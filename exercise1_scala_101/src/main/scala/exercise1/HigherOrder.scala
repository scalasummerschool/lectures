package exercise1

object HigherOrder {

  val plus: (Int, Int) => Int = _ + _
  val multiply: (Int, Int) => Int = _ * _

  /* a) write a function which takes an `f: Int => Int => Int`, its parameters `a` and `b`
   *    and a multiplication factor `n` and returns n * f(a)(b). Let's call it `nTimes`.
   */



  def testNTimes(f: (Int, Int) => Int, a: Int, b: Int, n: Int): Int = n

  /* b) write an anonymous function, a function without identifier (a => b => ???), `f` for nTimes which:
   *          if (a > b) a else b
   */
  def testAnonymousNTimes(a: Int, b: Int, n: Int): Int = a
}
