package xtictactoe

import cats.effect.IO
import cats.implicits._

import scala.io.StdIn
import scala.util.Try

object Console {
  def println(str: String): IO[Unit] = IO { Predef.println(str) }

  def askValidated[T](msg: String, f: String => Either[String, T]): IO[T] = {
    for {
      _ <- println(msg)
      input = StdIn.readLine()

      _ <- IO(System.exit(0)).whenA(input.isEmpty)

      t <- f(input) match {
        case Left(errMsg) => println(errMsg) >> askValidated(msg, f)
        case Right(t) => IO(t)
      }
    } yield t
  }

  def askValidatedInt(msg: String, f: Int => Either[String, Int] = i => Right(i)): IO[Int] = {
    askValidated(msg, { input =>
      Try(input.toInt).toEither.left.map(_ => "Please input a number.").flatMap(f)
    })
  }
}
