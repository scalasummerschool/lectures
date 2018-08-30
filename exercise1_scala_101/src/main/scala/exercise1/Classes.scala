package exercise1

/* a) Create a class Person with a firstname, lastname, birthyear and address field.
 *    The address itself concist of a street, city and country field. Create an own class for it.
 * b) Add `def age(year: Int): Int` which calculates the persons age.
 * c) Add `def fullname: String` which concatenates the first and last name.
 * d) Add `def move(address: Address): Person` which updates the address field.
 */

object Classes {

  def testAge(birthyear: Int, year: Int): Int = year
  
  def testFullname(firstName: String, lastName: String): String = firstName

  // check if person.address != person.move(newAddress).address
  def testMove(street: String, city: String, country: String): Boolean = false
}
