package exercise1

object HigherOrderSolution {

  def nTimes(f: (Int, Int) => Int, a: Int, b: Int, n: Int): Int = n * f(a, b)
}
