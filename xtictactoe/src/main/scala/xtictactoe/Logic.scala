package xtictactoe

object Logic {

  /**
    * Representation for the players. Can only be `Player1` or `Player2`
    */
  sealed trait Player

  case object Player1 extends Player

  case object Player2 extends Player

  /**
    * Representation of a single field on the game board. Can be `Empty` or occupied by a player via `Piece(player)`
    */
  sealed trait Field

  case object Empty extends Field

  case class Piece(player: Player) extends Field

  /**
    * Final result of a game. Either a player won (`PlayerWon(player)`) or it is a `Draw`
    */
  sealed trait GameResult

  case class PlayerWon(player: Player) extends GameResult

  case object Draw extends GameResult

  /**
    * A column on the game board is represented as a list of `Field`s.
    */
  type Column = List[Field]
  type Board = List[Column]

  /**
    * A `Game` consists of the current state of the board, the size of the board and the number of tiles needed to win.
    *
    * @param board   A list of columns representing the current state of the board
    * @param size    The size of the board. `board` will consist of `size` lists, each of which has `size` elements
    * @param winSize The number of tiles aligned horizontally, vertically or diagonally for a single player.
    */
  case class Game(board: Board, size: Int, winSize: Int)

  /**
    * This method creates and returns a `Game` instance. The contained board should be of the size in `sizeOpt` or
    * set to a reasonable default. Similarly, `winSize` should be taken from the argument or set to a default value
    * (that is smaller than the `size`.
    *
    * @param sizeOpt An option containing the `size` of the board. If `None` a default value will be used.
    * @param winSize An option containing the number of winning tiles. If `None` a default value will be used.
    * @return A new `Game` with board of size `sizeOpt` (or a default value)
    */
  def initBoard(sizeOpt: Option[Int], winSize: Option[Int]): Game = {
    // Here we ignore the input and construct a board of size 2
    // TODO: change the definition to create a board of the size given in `sizeOpt`
    val board = List(List(Empty, Empty), List(Empty, Empty), List(Empty, Empty))
    Game(board, 2, 2)
  }

  /**
    * This method returns a sensible `String` representation of the board.
    *
    * @param game The `Game` of which we want to draw the board (i.e. `game.board`)
    * @return
    */
  def view(game: Game): String = {
    // TODO: Construct a nice and readable representation of the board
    game.board.transpose.mkString("\n")
  }

  /**
    * Returns the result of the game if it is finshed or `None` if it is still running.
    *
    * Possible results are either a won game (by a player) or a draw.
    *
    * @param game The game we want to get a result for
    * @return Either the game's result (won or draw) or `None` if the game is not finished yet.
    */
  def result(game: Game): Option[GameResult] = {
    // TODO: This game will never stop. This should be fixed. Make sure to return the proper result.
    // Hints:
    // - It helps to break the task down. As a first step, check if a player has enough sequential pieces in a column.
    // - It is often better (i.e. more readable and maintainable) to not use indices. Try to use combinators (i.e. methods
    //   of the `List` class). See https://www.scala-lang.org/api/2.12.3/scala/collection/immutable/List.html .
    // - Don't worry about performance!
    // - It suffices to return `Draw` only if no more moves are possible.
    None
  }

  /**
    * Try to compute the game's board state with a piece for `player` set on the field specified by `row` and `column`
    * and return the new board (in a `Right`).
    *
    * There are several cases in which this method cannot provide a new state:
    *   - The row or column value are out of bounds
    *   - The field is already occupied.
    * In any such case, the function will not return the new state but instead provide a helpful error message in a `Left`.
    *
    * @param game  The current state of the game we want to update.
    * @param player The active player (making the move)
    * @param row    The row of the new piece
    * @param column The column of the new piece.
    * @return An `Either` containing either an error message in `Left` or the new board (list of columns) in `Right`.
    */
  def updateBoard(game: Game, player: Player, row: Int, column: Int): Either[String, Board] = {
    // We return the original board here
    // TODO: Implement! Create an updated board or produce an error message.
    Right(game.board)
  }
}
