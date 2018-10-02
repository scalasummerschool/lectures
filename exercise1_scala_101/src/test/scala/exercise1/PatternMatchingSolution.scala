package exercise1

object PatternMatchingSolution {

  import PatternMatching._

  def intToString(value: Int): String = value match {
    case 1 => "it is one"
    case 2 => "it is two"
    case 3 => "it is three"
    case _ => "what's that"
  }

  def isMaxOrMoritz(kid: String): Boolean = kid match {
    case "Max" | "max"       => true
    case "Moritz" | "moritz" => true
    case _                   => false
  }

  def isEven(value: Int): Boolean = value match {
    case _ if value % 2 == 0 => true
    case _                   => false
  }

  def playerAWins(a: Hand, b: Hand): Result = (a, b) match {
    case (Rock, Scissor)      => Win
    case (Scissor, Paper)     => Win
    case (Paper, Rock)        => Win
    case (ha, hb) if ha == hb => Draw
    case _                    => Lose
  }

  def extractWeight(animal: Animal): Int = animal match {
    case Mammal(_, _, weight) => weight
    case _                    => -1
  }

  def updateFood(animal: Animal): Animal = animal match {
    case Bird(name, _) => Bird(name, Plants)
    case Fish(name, _) => Fish(name, Plants)
    case _             => animal
  }
}
