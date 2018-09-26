package xtictactoe

import cats.effect.IO
import cats.implicits._
import cats.data._

import scala.io.StdIn
import scala.util.Try

object Console {
  def println(str: String): IO[Unit] = IO { Predef.println(str) }

  def askValidated[T](msg: String, f: String => Either[String, T]): OptionT[IO, T] = {
    for {
      _ <- OptionT.liftF(println(msg))
      t <- Option(StdIn.readLine()) match {
        case Some(input) =>
          f(input) match {
            case Left(errMsg) =>
              val msgAct: OptionT[IO, Unit] = OptionT.liftF(println(errMsg))
              msgAct.flatMap(_ => askValidated(msg, f))
            case Right(t) => OptionT.fromOption[IO](Some(t))
          }

        case None =>
          OptionT.fromOption[IO](None)
      }
    } yield t
  }

  def askValidatedInt(msg: String, f: Int => Either[String, Int] = i => Right(i)): OptionT[IO, Int] = {
    askValidated(msg, { input =>
      Try(input.toInt).toEither.left.map(_ => "Please input a number.").flatMap(f)
    })
  }
}
