package exercise1

/* a) Create a class Person with a firstname, lastname, birthyear and address field.
 *    The address itself concist of a street, city and country field. Create an own class for it.
 */

object Classes {

  // b) Add `def age(year: Int): Int` which calculates the persons age.



  def testAge(birthyear: Int, year: Int): Int = year

  // c) Add `def fullname: String` which concatenates the first and last name.



  def testFullname(firstName: String, lastName: String): String = firstName

  /* d) Add `def move(address: Address): Person` which updates the address field.
   *    check if person.address != person.move(newAddress).address
   */


  def testMove(street: String, city: String, country: String): Boolean = false
}
