package exercise1

object FunctionsSolution {

  def circle(r: Double): Double = r * r * Math.PI

  def rectangleCurried(a: Double)(b: Double): Double = a * b

  def rectangleUncurried(a: Double, b: Double): Double = rectangleCurried(a)(b)

  val rectangleUnderscore: (Double, Double) => Double = _ * _
}
