package exercise1

/* a) Create a class Animal which has the following fields:
 *      - name: String (name of the species)
 *      - species: String (e.g. mammal or reptile)
 *      - food: String
 * 
 * b) Create instances for the following animals:
 *      - cat, mammal, meat
 *      - parrot, bird, vegetables
 *      - goldfish, fish, plants
 * 
 * c) Add the following method to Animals:
 *      def eats(food: String): Boolean
 *    which checks if an animal eats the given food
 * 
 * d) Define Animal as trait and create case class instances for the Mammals, Birds, and Fishs.
 * 
 * e) Create a Companion Object Animal. Put the animals from (b) as fields into it and add the following functions:
 *      def knownAnimal(name: String): Boolean  // true if it is the name of one of the three animals we know
 *      def apply(name: String): Option[Animal] // returns one of the three animals matching the name or nothing (None), see Option below
 * 
 * f) Create a trait Food with the following case objects:
 *      - Meat
 *      - Vegetables
 *      - Plants
 *    and update the Animal description. Also add a companion object with an apply method:
 *      def apply(food: String): Option[Food]
 * 
 * g) Test your function implementation test functions below.
 */

object Classes {

  def testKnownAnimal(name: String): Boolean = false

  // use apply here to find the instance and then call eats(food) on it
  // return false if no instance could be found
  def testEats(food: String, name: String): Boolean = false
}

sealed trait Option[A] {
 
  def isEmpty: Boolean
}
case class Some[A](a: A) extends Option[A] {
  val isEmpty = false
}
case class None[A]()     extends Option[A] {
  val isEmpty = true
}
