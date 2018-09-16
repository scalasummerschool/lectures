package exercise1

object ClassesSolution {

  sealed trait Food
  case object Meat       extends Food
  case object Vegetables extends Food
  case object Plants     extends Food

  object Food {

    def apply(food: String): Option[Food] = 
      if (food == "meat")            Some(Meat)
      else if (food == "vegetables") Some(Vegetables)
      else if (food == "plants")     Some(Plants)
      else                           None()
  }

  sealed trait Animal {

    val name: String
    val food: Food

    def eats(food: Food): Boolean = food == this.food
  }

  case class Mammal(name: String, food: Food) extends Animal
  case class Bird(name: String, food: Food)   extends Animal
  case class Fish(name: String, food: Food)   extends Animal

  object Animal {

    val cat      = Mammal("cat", Meat)
    val parrot   = Bird("parrot", Vegetables)
    val goldfish = Fish("goldfish", Plants)

    def knownAnimal(name: String): Boolean =
      name == "cat" || name == "parrot" || name == "goldfish"

    def apply(name: String): Option[Animal] = 
      if (name == cat.name)           Some(cat)
      else if (name == parrot.name)   Some(parrot)
      else if (name == goldfish.name) Some(goldfish)
      else                            None()
  }
}
