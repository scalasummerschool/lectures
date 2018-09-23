package exercise5

import cats.Applicative

object ApplicativesSolution {

  implicit val listApp = new Applicative[List] {

    def pure[A](a: A): List[A] = List(a)

    def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] = 
      for {
        a <- fa
        f <- ff
      } yield f(a)
  }

  def listApplicative[A, B](ff: List[A => B], fa: List[A]): List[B] =
    Applicative[List].ap(ff)(fa)

  def applyApp(f: Int => Int => String, x: List[Int], y: List[Int]): List[String] = {
    val ap = Applicative[List]

    ap.ap(ap.map(x)(f))(y)
  }
}
