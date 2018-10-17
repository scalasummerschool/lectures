package exercise3

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop._

object SequenceSpec extends Properties("sequence") {

  property("last element") = forAll { seq: Seq[Int] =>
    Sequence.testLastElement(seq) =? SequenceSolution.lastElement(seq)
  }

  property("zip") = forAll { (a: Seq[Int], b: Seq[Int]) =>
    Sequence.testZip(a, b) =? SequenceSolution.zip(a, b)
  }

  property("for all") = forAll { seq: Seq[Int] =>
    Sequence.testForAll(seq)(_ > 3) =? SequenceSolution.forAllElements(seq)(_ > 3)
  }

  property("palindrom") = forAll(palindromeGen) { seq =>
    Sequence.testPalindrom(seq) =? SequenceSolution.palindrom(seq)
  }

  property("flatMap") = forAll { seq: Seq[Int] =>
    Sequence.testFlatMap(seq)(a => Seq(a, a + 1, a + 2)) =? SequenceSolution.flatMap(seq)(a => Seq(a, a + 1, a + 2))
  }

  val palindromeGen = for {
    isPalindrom <- Gen.oneOf(true, false)
    seq         <- Gen.sequence[Seq[Int], Int](List(Gen.posNum[Int]))
  } yield {
    if (isPalindrom) {
      val half = seq.take(seq.length / 2)

      half ++ half.reverse
    }
    else
      seq
  }
}
