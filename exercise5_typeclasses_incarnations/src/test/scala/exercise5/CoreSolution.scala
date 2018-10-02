package exercise5

import cats._

import scala.annotation.tailrec

object CoreSolution {

  import Core.ErrorOr

  implicit val listFunct = new Functor[ErrorOr] {

    def map[A, B](fa: ErrorOr[A])(f: A => B): ErrorOr[B] = fa match {
      case Right(a) => Right(f(a))
      case Left(e)  => Left(e)
    }
  }

  def errorOrFunctor[A, B](fa: ErrorOr[A], f: A => B): ErrorOr[B] = Functor[ErrorOr].map(fa)(f)


  implicit val errorOrApp = new Applicative[ErrorOr] {

    def pure[A](a: A): ErrorOr[A] = Right(a)

    def ap[A, B](ff: ErrorOr[A => B])(fa: ErrorOr[A]): ErrorOr[B] = 
      for {
        a <- fa
        f <- ff
      } yield f(a)
  }

  def errorOrApplicative[A, B](ff: ErrorOr[A => B], fa: ErrorOr[A]): ErrorOr[B] =
    Applicative[ErrorOr].ap(ff)(fa)

  def applyApp(f: Int => Int => String, x: ErrorOr[Int], y: ErrorOr[Int]): ErrorOr[String] = {
    import cats.syntax.all._

    Applicative[ErrorOr].pure(f) <*> x <*> y
  }


  implicit val errorOrMon = new Monad[ErrorOr] {

    def pure[A](a: A): ErrorOr[A] = Right(a)

    def flatMap[A, B](fa: ErrorOr[A])(f: A => ErrorOr[B]): ErrorOr[B] = fa match {
      case Right(a) => f(a)
      case Left(e)  => Left(e)
    }

    @tailrec
    def tailRecM[A, B](a: A)(f: A => ErrorOr[Either[A,B]]): ErrorOr[B] = f(a) match {
      case Right(Right(b)) => Right(b)
      case Right(Left(aa)) => tailRecM(aa)(f)
      case Left(e)         => Left(e)
    }
  }

  def errorOrMonad[A, B](fa: ErrorOr[A], f: A => ErrorOr[B]): ErrorOr[B] = Monad[ErrorOr].flatMap(fa)(f)
}
