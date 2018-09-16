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

  property("groupd users") = forAll { users: Seq[User] =>
    testGroupUsers(users) == MapsSolution.averageAge(users)
  }

  property("number of Frodos") = forAll(userMapGen) { users =>
    testNumberFrodos(users) == MapsSolution.numberOfFrodos(users)
  }

  property("underaged") = forAll(userMapGen) { users =>
    testUnderaged(users) == MapsSolution.underaged(users)
  }

  val userMapGen = for {
    users <- Gen.sequence[List[User], User](List(userGen))
  } yield users.groupBy(_.name).mapValues(_.head)
}
