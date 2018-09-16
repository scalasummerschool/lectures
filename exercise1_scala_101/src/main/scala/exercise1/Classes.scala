package exercise1

/* This task has no tests. It is an exercise for you to write different class structures.
 * 
 * a) Create a class Animal which has the following fields:
 *      - name: String (name of the species)
 *      - species: String (e.g. mammal or reptile)
 *      - food: String
 * 
 *    Syntax: class MyClass(val publicField: Int, privateField: String) {
 *              // put other fields and methods here
 *            }
 * 
 * b) Create an Companion Object for Animal and add the following instances as fields:
 *      - cat, mammal, meat
 *      - parrot, bird, vegetables
 *      - goldfish, fish, plants
 * 
 *    Syntax: object MyClass {
 *              // put your static fields and methods here
 *            }
 * 
 * c) Add the following method to Animals:
 *      def eats(food: String): Boolean
 *    
 *     which checks if an animal eats the given food
 * 
 * d) Redefine your class Animal as trait and create case class instances for Mammals, Birds, and Fishs.
 *    do you still need the field `species`?
 * 
 * e) Put the animals from (b) as fields into it and add the following functions to Animals Companion Object:
 *      def knownAnimal(name: String): Boolean  // true if it is the name of one of the three animals we know
 *      def apply(name: String): Option[Animal] // returns one of the three animals matching the name (Some) or nothing (None), see Option below
 * 
 * f) Create a trait Food with the following case objects:
 *      - Meat
 *      - Vegetables
 *      - Plants
 *    and add it to Animal definition. Also add a companion object with an apply method:
 *      def apply(food: String): Option[Food]
 */

sealed trait Option[A] {
 
  def isEmpty: Boolean
}
case class Some[A](a: A) extends Option[A] {
  val isEmpty = false
}
case class None[A]()     extends Option[A] {
  val isEmpty = true
}
