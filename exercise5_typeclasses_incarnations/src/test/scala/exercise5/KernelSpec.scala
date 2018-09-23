package exercise5

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object KernelSpec extends Properties("kernel") {

  property("String equality") = forAll { (x: String, y: String, eq: Boolean) =>
    if (eq)
      KernelSolution.eqString(x, x) == Kernel.testEqString(x, x)
    else
      KernelSolution.eqString(x, y) == Kernel.testEqString(x, y)
  }

  property("Int comparison") = forAll { (x: Int, y: Int, eq: Boolean) =>
    if (eq)
      KernelSolution.compareInt(x, x) == Kernel.testCompareInt(x, x)
    else
      KernelSolution.compareInt(x, y) == Kernel.testCompareInt(x, y)
  }

  property("List combination") = forAll { (x: List[Int], y: List[Int]) =>
    KernelSolution.combineLists(x, y) == Kernel.testCombineLists(x, y)
  }

  property("arbitrary List combination") = forAll { (x: List[String], y: List[String]) =>
    KernelSolution.combineListsArb(x, y) == Kernel.testCombineListsArb(x, y)
  }
}
