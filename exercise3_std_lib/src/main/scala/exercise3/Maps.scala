package exercise3

/** Write your solution into the test functions.
  * 
  * https://docs.scala-lang.org/overviews/collections/maps.html
  */
object Maps {

  case class User(name: String, age: Int)

  /* a) Given a Seq[User] group the users by name (`groupBy`) and calculate the average age: `name -> averageAge`
   *    You can implement your solution directly in the test-function. DO NOT change the signature.
   */
  def testGroupUsers(users: Seq[User]): Map[String, Int] = Map.empty

  /* b) Given a `Map[String, User]` from user name to `User`, how many Users with the substring "Baggins" are in the Map?
   *    You can implement your solution directly in the test-function. DO NOT change the signature.
   */
  def testNumberFrodos(map: Map[String, User]): Int = 0

  /* c) Remove all users under age 1000 (Wizards only :).
   *    You can implement your solution directly in the test-function. DO NOT change the signature.
   */
  def testUnderaged(map: Map[String, User]): Map[String, User] = map
}
