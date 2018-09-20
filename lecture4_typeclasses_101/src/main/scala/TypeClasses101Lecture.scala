import PresentationUtil._
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object TypeClasses101Lecture extends JSApp {
  import Enumeration._

  val overview = chapter(
    chapterSlide(
      <.h1("Type classes 101")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable("A motivating example"),
        Item.fadeIn("The expression problem"),
        Item.fadeIn("Type classes as an answer ")
      )
    ),

    noHeaderSlide(
      <.h2("You have a question?"),
      <.h3("Ask it right away!")
    )
  )

  val expressionProblem = chapter(
    chapterSlide(
      <.h2("What problem do type classes solve")
    ),

    slide(
      "Dealing with change",
      <.br,
      scalaC(
        """
          |sealed trait Animal
          |case class Mammal(name: String)
          |case class Fish(name: String, habitat: String)
          |
          |object Animal {
          |  def show(animal: Animal): String = animal match {
          |    case Mammal(name)        => "A mammal with name ${name}"
          |    case Fish(name, habitat) => "A fish named ${name} living at ${habitat}"
          |  }
          |}
          |
        """.stripMargin)
    ),

    slide(
      "Can we add a new Animal and have it shown?",
      <.p("Yes, but we need to update Animal.show"),
      scalaC(
        """
          |sealed trait Animal
          |case class Mammal(name: String)
          |case class Fish(name: String, habitat: String)
          |case class Rodent(name: String, food: String)
          |
          |object Animal {
          |  def show(animal: Animal): String = animal match {
          |    case Mammal(name)        => "A mammal with name ${name}"
          |    case Fish(name, habitat) => "A fish named ${name} living at ${habitat}"
          |    case Rodent(name, food)  => "A rodent named ${name} eating ${food}"
          |  }
          |}
          |
        """.stripMargin),
      <.p("This means a code change for every new animal")
    ),

    slide(
      "Can we add a new function pver existing animals?",
      <.p("Yes, but we need to update the Animal object"),
      scalaC(
        """
          |object Animal {
          |  def isA(a: Animal, b: Animal): Boolean = (a,b) match {
          |    case (Mammal(_), Mammal(_)) | (Fish(_, _), Fish(_, _)) | (Rodent(_,_), Rodent(_, _)) => true
          |    case (Rodent(_,_), Mammal(_)) => true
          |    case _ => false
          |  }
          |}
        """.stripMargin
      ),
      <.p("This means a code change for every new operation")
    ),

    slide(
      "The expression problem",
       <.blockquote(
       """
          |The goal is to define a datatype by cases, where one can add new cases to the datatype and new functions over the datatype,
          |without recompiling existing code, and while retaining static type safety (e.g., no casts).
        """.stripMargin)
    )
  )

  val expressionSolution = chapter(
    chapterSlide(
      <.h2("How do type classes solve the expression problem")
    ),

    slide(
      "The Show type class",
      scalaC(
        """
          |trait Show[A] {
          |  def show(a: A): String
          |}
          |
          |object Show {
          |  def show[A](a: A, showable: Show[A]): String = showable.show(a)
          |}
        """.stripMargin),
      <.br,
      Enumeration(
        Item.fadeIn("Create trait for the operation"),
        Item.fadeIn("Introduce generic parameter A"),
        Item.fadeIn("Since A is all quantified it must support all types, including new ones")
      )
    ),
    slide(
      "Implement Show for Animal",
      scalaCFragment(
        """
          |val animalShow = new Show[Animal] {
          |  def show(a: Animal): String = a.match {
          |    case Mammal(name)        => "A mammal with name ${name}"
          |    case Fish(name, habitat) => "A fish named ${name} living at ${habitat}"
          |    case Rodent(name, food)  => "A rodent named ${name} eating ${food}"|
          |  }
          |}
        """.stripMargin),
    ),
    slide(
      "Use the implementation",
      scalaC(
        """
          | object Show {
          |   def show[A](a: A, showImpl: Show[A]): String = showImpl.show(a)
          | }
          |
          | Show.show(Rodent("Hamster", "Carrots"), animalShow)
        """.stripMargin),
      <.br,
      <.p("The Show object provides an interface method")
    ),

    slide("Can't we make that simpler?",
      <.h5("Make the implementation parameter implicit"),
      <.br,
      scalaC(
        """
          | object Show {
          |   def show[A](a: A)(implicit showImpl: Show[A]): String = showImpl.show(a)
          | }
        """.stripMargin),
      scalaCFragment(
        """
          |implicit val animalShow = new Show[Animal] {
          |  def show(a: Animal): String = a.match {
          |     ...
          |  }
          |}
        """.stripMargin),
      scalaCFragment(
        """
          | Show.show(Rodent("Hamster", "Carrots"))
        """.stripMargin)
    ),
    noHeaderSlide(
      <.p("Different syntax, same thing"),
      scalaC(
        """
          | object Show {
          |   def show[A : Show](a: A): String = implicitly[Show[A]].show(a)
          | }
        """.stripMargin)
    ),
    noHeaderSlide(
      <.p("But can we add new operations?"),
      scalaC(
        """
          | trait Eq[T] {
          |   def eq(lhs: T, rhs: T): Boolean
          | }
          |
          | object Eq {
          |   def def[T: Eq](lhs: T, rhs: T): Boolen = implicitly[Eq[T]].eq(lhs, rhs)
          | }
        """.stripMargin),
      scalaCFragment(
        """
           | val animalEq = new Eq[Animal] {
           |   def eq(a: Animal, b: Animal): Boolean = a == b
           | }
        """.stripMargin),
      scalaCFragment(
        """
          | Eq.eq(Rodent("Hamster", "Carrots"), Mammal("Mouse"))
        """.stripMargin)
    )
  )

  val summary = chapter(
    chapterSlide(
      <.h2("Type classes 101 summary"),
      <.p("Type classes are a mechanism to write extensible and adaptable code."),
      Enumeration(
        Item.fadeIn("Operations and types can be added without changing existing code"),
        Item.fadeIn("Type classes provide polymorphic implementations ad hoc, also called duck tying"),
        Item.fadeIn("Type classes use implicit resolution to find instances")
      )
    )
  )

  val Show = ScalaComponent
    .builder[Unit]("Slideshow")
    .renderStatic(
      <.div(
        ^.cls := "reveal",
        <.div(
          ^.cls := "slides",
          overview,
          expressionProblem,
          expressionSolution,
          summary
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
