package exercise1

/* a) repeat task `Classes.scala` but for case classes */

object CaseClasses {

  def testAge(birthyear: Int, year: Int): Int = year
  
  def testFullname(firstName: String, lastName: String): String = firstName

  // check if person.address != person.move(newAddress).address
  def testMove(street: String, city: String, country: String): Boolean = false
}
