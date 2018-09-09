package exercise3

object Maps {

  case class User(name: String, age: Int)

  // a) Given a Seq[User] group them by name (`groupBy`) and return only `name -> averageAge`
  def testGroupUsers(users: Seq[User]): Map[String, Int] = Map.empty

  // b) How many Users with name "Frodos" are in the Map
  def testNumberFrodos(map: Map[String, User]): Int = 0

  // c) Remove all users under age 18
  def testUnderaged(map: Map[String, User]): Map[String, User] = map
}
