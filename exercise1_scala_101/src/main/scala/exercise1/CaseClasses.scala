package exercise1

/* a) Create a Person case class with a firstname, lastname, birthyear and address.
 *    The address itself concist of a street, city and country. Create an own case class for it.
 * b) Add `def age(year: Int): Int` which calculates the persons age.
 * c) Add `def fullname: String` which gives concatenates the first and last name.
 * d) Add `def move(address: Address): Person` which updates the address field.
 */

object CaseClasses {

  def testAge(birthyear: Int, year: Int): Int = year
  
  def testFullname(firstName: String, lastName: String): String = firstName

  // check if person.address != person.move(newAddress).address
  def testMove(street: String, city: String, country: String): Boolean = false
}

/* e) Create trait `Currency` describing the properties:
 *   - `value: Int`: currency value as Int; first two digits are cents: 1.50$ => 150
 *   - `show: String`: function with arity 0 giving a String representation: 150 => "1.50$"
 * f) Create sub classes for Euro, Dollar, etc
 * g) add a `+` method to each subclass defining how to add the respective currency
 */

object Traits {

  // f)
  def testShowEuro(value: Int): String = ""


  // g) add values and show result
  def testPlusEuro(a: Int, b: Int): String = ""

}

