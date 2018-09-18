package exercise1

/** Write your solutions as stand-alone functions. 
  * 
  * Syntax:
  *   // method
  *   def myFunction(param0: Int, param1: String): Double = // body expression
  * 
  *   // value
  *   val myFunction: (Int, String) => Double (param0, param1) => // body expression
  */
object Functions {

  /* a) Write a function which calculates the area of a
   *    circle r^2 * Math.PI
   */



  def testCircle(r: Double): Double = r
  
  /* b) Write a curried function which calculates the area of a
   *    rectangle a * b.
   */



  def testRectangleCurried(a: Double, b: Double): Double = a

  // c) Write a uncurried `rectangle` function.



  def testRectangleUc(a: Double, b: Double): Double = a
}
