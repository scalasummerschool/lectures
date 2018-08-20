package exercise1

object Area {

  // a) write a function which calculates the area of a
  //    circle r^2 * Math.PI



  def testCircle(r: Double): Double = r
  
  // b) write a function which calculates the area of a
  //    rectangle a * b



  def testRectangle(a: Double, b: Double): Double = a

  // c) write a uncurried `rectangle` function



  def testRectangleUc(a: Double, b: Double): Double = a

  // d) use `_` for `circle` and uncurried `rectangle`



  def testRectangleUs(a: Double, b: Double): Double = a

  // e) why can't you use `_` for curried `rectangle`?
}
