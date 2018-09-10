package exercise3

import org.scalacheck.Properties
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll

import scala.annotation.tailrec

object MapsSpec extends Properties("Map") {

  import Maps._

  val userGen = for {
    age  <- Gen.choose(0, 2019)
    name <- Gen.oneOf("Gandalf", "Frodo", "Saroman", "Gimli", "Elessar")
  } yield User(name, age)

  implicit val userArb = Arbitrary[User](userGen)

  def averageAge(users: Seq[User]): Map[String, Int] = 
    users
      .groupBy(_.name)
      .mapValues(_.map(_.age).sum / users.length)

  property("groupd users") = forAll { users: Seq[User] =>
    testGroupUsers(users) == averageAge(users)
  }

  def numberOfFrodos(users: Map[String, User]): Int = users.count(_._1 == "Frodo")

  property("number of Frodos") = forAll(userMapGen) { users =>
    testNumberFrodos(users) == numberOfFrodos(users)
  }

  def underaged(users: Map[String, User]): Map[String, User] = users.filter(_._2.age >= 1000)

  property("underaged") = forAll(userMapGen) { users =>
    testUnderaged(users) == underaged(users)
  }

  val userMapGen = for {
    users <- Gen.sequence[List[User], User](List(userGen))
  } yield users.groupBy(_.name).mapValues(_.head)
}
