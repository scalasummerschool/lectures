package xtictactoe

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Game extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    def go(board: Logic.Game, player: Logic.Player): IO[Unit] = {
      val winnerOpt = Logic.result(board)

      winnerOpt match {
        case Some(Logic.PlayerWon(player)) =>
          if (player == Logic.Player1)
            Console.println("Player 1 won")
          else
            Console.println("Player 2 won")

        case Some(Logic.Draw) =>
          Console.println("It's a draw!")

        case None =>
          def inputLoop: IO[Logic.Game] = {
            for {
              column <- Console.askValidatedInt("At what column do you want to put your next piece?")
              row <- Console.askValidatedInt(s"At what row in column ${column} do you want to put your next piece?")

              newBoard <- Logic.updateBoard(board, player, row, column) match {
                case Left(msg) => Console.println(msg) >> inputLoop
                case Right(newColumns) => IO(board.copy(board = newColumns))
              }
            } yield newBoard
          }

          for {
            _ <- Console.println("") >> Console.println(player.toString + ", it's your turn.")
            newBoard <- inputLoop
            _ <- Console.println(Logic.view(newBoard))

            newPlayer = player match {
              case Logic.Player1 => Logic.Player2
              case Logic.Player2 => Logic.Player1
            }

            _ <- go(newBoard, newPlayer)
          } yield ()
      }
    }

    val mainAction = for {
      _ <- Console.println("Hey there. Let's play X-XOXO")
      size <- Console.askValidatedInt("What's the board size?")
      winSize <- Console.askValidatedInt("What's the number of winning pieces?", i =>
        if (i <= size) Right(i)
        else Left("It cannot be bigger than the board size.")
      )

      _ <- go(Logic.initBoard(Some(size), Some(winSize)), Logic.Player1)
    } yield ()

    mainAction.as(ExitCode.Success)
  }
}
