package exercise5

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object ApplicativesSpec extends Properties("Applicative") {

  property("List Applicative") = forAll { fa: List[Int] =>
    val ff: List[Int => Int] = List(_ * 1, _ + 3)

    ApplicativesSolution.listApplicative(ff, fa) == Applicatives.testListApplicative(ff, fa)
  }

  property("apply Applicative") = forAll { (x: List[Int], y: List[Int]) =>
    val f: Int => Int => String = a => b => (a * b).toString

    ApplicativesSolution.applyApp(f, x, y) == Applicatives.testApplyApp(f, x, y)
  }
}
