package exercise3

import scala.annotation.tailrec

object SequenceSolution {

  @tailrec
  def lastElement[A](seq: Seq[A]): Option[A] = seq match {
    case last :: Nil => Some(last)
    case _ :: tail   => lastElement(tail)
    case Nil         => None
  }

  def reverse[A](seq: Seq[A]): Seq[A] = seq.foldLeft(Seq.empty[A])((agg, a) => a +: agg)

  @tailrec
  def zip[A](a: Seq[A], b: Seq[A], agg: Seq[(A, A)] = Nil): Seq[(A, A)] = (a, b) match {
    case (ah +: at, bh +: bt) => zip(at, bt, (ah, bh) +: agg)
    case (Nil, _) | (_, Nil)  => reverse(agg)
  }

  def forAllElements[A](seq: Seq[A])(cond: A => Boolean): Boolean = seq.foldLeft(true)((agg, a) => agg && cond(a))

  def palindrom[A](seq: Seq[A]): Boolean = {
    val middle = seq.length / 2 + 1

    if (seq.isEmpty) 
      true
    else
      forAllElements(zip(seq.take(middle), reverse(seq).take(middle)))(pair => pair._1 == pair._2)
  }

  def deduplicate[A](seq: Seq[A]): Seq[A] = reverse(seq.foldLeft(Seq.empty[A]) { (agg, a) =>
    if (agg.contains(a)) agg
    else                 a +: agg
  })

  def flatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] = reverse(seq.foldLeft(Seq.empty[B]) { (agg, a) =>
    val bs = f(a)

    bs ++ agg
  })
}
