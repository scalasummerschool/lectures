package exercise1

object HigherOrder {

  val plus: Int => Int => Int = a => b => a + b
  val multiply: Int => Int => Int = a => b => a * b

  // a) write a function which takes an `f: Int => Int => Int`, its parameters `a` and `b`
  //    and a multiplication factor `n` and returns n * f(a)(b). Let's call it `nTimes`.



  def testNTimes(f: (Int => Int => Int), a: Int, b: Int, n: Int): Int = n

  // b) write an anonymous function, a function without identifier, `f` for `nTimes`:
  //         nTimes(a => b => ???)



  // c) make `nTimes` generic of its parameter/result type (type parameter)


  
  // d) use generic `nTimes` with different types using anonymous functions


}
