package exercise1

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

object ClassesSpec extends Properties("classes") {

  val animal = Gen.oneOf("cat", "parrot", "goldfish", "shark", "lion")
  val food   = Gen.oneOf("meat", "vegetables", "plants", "fruits", "sweets")

  def knownAnimal(name: String): Boolean =
    name == "cat" || name == "parrot" || name == "goldfish"

  property("known animal") = forAll(animal) { name =>
    Classes.testKnownAnimal(name) == knownAnimal(name)
  }

  val foodAndAnimal = for {
    f <- food
    a <- animal
  } yield (f, a)

  def eats(food: String, name: String): Boolean =
    (name == "cat" && food == "meat") || (name == "parrot" && food == "vegetables") || (name == "goldfish" && food == "plants")

  property("eats food") = forAll(foodAndAnimal) { case (food, name) =>
    Classes.testEats(food, name) == eats(food, name)
  }
}
