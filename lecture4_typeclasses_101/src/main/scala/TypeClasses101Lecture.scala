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
      <.h3("What you will learn"),
      Enumeration(
        Item.fadeIn("The problems of dealing with change"),
        Item.fadeIn("The expression problem"),
        Item.fadeIn("Type classes as an answer to the expression problem")
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
        """.stripMargin),
        <.br,
        <.h3(
         ^.cls := "fragment fade-in",
        "Can we add a new Animal and have it shown?")
    ),

    slide(
      "Dealing with change",
      <.h3("Yes, but we have to change the implementation of Animal.show"),
      <.br,
      scalaC(
        """
          |sealed trait Animal
          |case class Mammal(name: String)
          |case class Fish(name: String, habitat: String)
          |case class Rodent(name: String, food: String) // <---- New animal
          |
          |object Animal {
          |  def show(animal: Animal): String = animal match {
          |    case Mammal(name)        => "A mammal with name ${name}"
          |    case Fish(name, habitat) => "A fish named ${name} living at ${habitat}"
          |    case Rodent(name, food)  => "A rodent named ${name} eating ${food}" // <--- new case
          |  }
          |}
          |
        """.stripMargin)
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
        """.stripMargin),
        <.br,
        <.h3(
         ^.cls := "fragment fade-in",
        "Can we add a new function over existing animals?")
    ),

    slide(
      "Dealing with change",
      <.h3("Yes, but we have to change the implementation of Animal"),
      <.br,
      scalaC(
        """
          |object Animal {
          |  // new function over existing animals
          |  def isA(a: Animal, b: Animal): Boolean = (a,b) match { 
          |    case (Mammal(_), Mammal(_)) | (Fish(_, _), Fish(_, _)) | (Rodent(_,_), Rodent(_, _)) => true
          |    case (Rodent(_,_), Mammal(_)) => true
          |    case _ => false
          |  }
          |}
        """.stripMargin
      )
    ),

    slide(
      "The expression problem",
       <.h3("The expression problem"),
       <.blockquote(
       """
          |The goal is to define a datatype by cases, where one can add new cases to the datatype and new functions over the datatype,
          |without recompiling existing code, and while retaining static type safety (e.g., no casts).
        """.stripMargin),
      <.p(<.small("Originally coined by lambda man aka Philip Wadler"))
    )
  )

  val expressionSolution = chapter(
    chapterSlide(
      <.h2("How do type classes solve the expression problem")
    ),

    slide(
      "The Show type class",
      <.h3("The Show type class"),
      <.br,
      scalaC(
        """
          |trait Show[A] {
          |  def show(a: A): String
          |}
          |
          |object Show {
          |  def show[A](a: A, showable: Show[A]): String = showable.show(a)
             //                 ^
             //                 |
             //                 | --- accept the implementation
          |}
        """.stripMargin),
      <.br,
      Enumeration(
        Item.fadeIn("Create trait for the operation"),
        Item.fadeIn("Introduce generic parameter A"),
        Item.fadeIn("Since A is all quantified it must support all types, including new ones")
      )
    ),
    slide("The Show type class",
      <.h3("Instance for Animal"),
      <.br,
      scalaC(
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
    slide("The Show type class",
      <.h3("Give it an interface"),
      <.br,
      scalaC(
        """
          | object Show {
          |   def show[A](a: A, showImpl: Show[A]): String = showImpl.show(a)
          | }
          |
          | Show.show(Rodent("Hamster", "Carrots"), animalShow)
        """.stripMargin)
    ),

    slide("Implicit parameters",
      <.h3("Make the instance parameter implicit"),
      <.br,
      scalaC (
        """
          | object Show {
          |   def show[A](a: A, showImpl: Show[A]): String = showImpl.show(a)
          | }
          |
          | Show.show(Rodent("Hamster", "Carrots"), animalShow)
        """.stripMargin),
      scalaCFragment(
        """
          | object Show {
          |   def show[A](a: A)(implicit showImpl: Show[A]): String = showImpl.show(a)
                                         // ^
                                         // | 
                                         // |- Implicit parameter
          | }
        """.stripMargin),
      scalaCFragment(
        """
          |object Animal {
          |  implicit val animalShow = new Show[Animal] {
          |    def show(a: Animal): String = a.match {
          |       //...
          |    }
          |  }
          |}
        """.stripMargin),
      scalaCFragment(
        """
          | Show.show(Rodent("Hamster", "Carrots"))
        """.stripMargin)
    ),
    slide(
      "Syntax matters",
      <.h3("Different syntax, same thing"),
      scalaC(
        """
          | object Show {
          |   def show[A : Show](a: A): String = implicitly[Show[A]].show(a)
          | }
        """.stripMargin)
    ),
    slide(
      "Adding new functions",
      <.h3("But can we add new operations?"),
      scalaCFragment(
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
        """.stripMargin),
       <.h3(
           ^.cls := "fragment fade-in", "Yes!!")
    )
  )

  val summary = chapter(
    chapterSlide(
      <.h2("Type classes 101 summary")
    ),
    slide(
        "To sum it up",
        <.h3("Type classes are a mechanism to write extensible/polymorphic code without subtyping"),
        <.br,
        Enumeration(
          Item.fadeIn("Operations and types can be added without changing existing code"),
          Item.fadeIn("Type classes provide polymorphic implementations ad hoc, also called duck tying"),
          Item.fadeIn("Type classes use implicit resolution to find instances"),
          Item.fadeIn("Type classes do not require subtyping")
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
