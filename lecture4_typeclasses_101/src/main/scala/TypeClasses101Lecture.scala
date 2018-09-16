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

  val motivatingExample = chapter(
    chapterSlide(
      <.h2("What problem do type classes solve")
    ),

    slide(
      "Show values",
      <.br,
      scalaC(
        """
          |object Show {
          |  def showInt(a: Int): String = s"an Int: ${a}"
          |  def showString(a: String): String = s"a String: ${a}"
          |  def showBool(a: Boolean): String = s"a Boolean: ${a}"
          |}
        """.stripMargin),
      <.h2("Can show be implemented more generically?")
    ),

    noHeaderSlide(
      <.h4("Overloaded method"),
      scalaC("""
        def show(a: ???): String = ???
      """),
      scalaCFragment("""
        object Show {
          def show(a: Int) String = s"an Int: ${a}"
          def show(a: String) String = s"an Int: ${a}"
          def show(a: Boolean) String = s"an Int: ${a}"
        }
      """)
    ),

    noHeaderSlide(
      <.h4("But, what if we want to show a new type?"),
      scalaC("""
        Show.show(Mammal("Hamster"))
      """),
      <.br,
      <.h5(
        ^.cls := "fragment fade-in",
        "We can't do that without changing the Show object"
      )
    ),

    slide(
     "Show pretty",
      <.br,
      <.h5("Add a new operation showPretty"),
      scalaC(
        """
          Show.showPretty(1)
          Show.showPretty("A string")
          Show.showPretty(Mammal("Hamster")
        """.stripMargin),
      <.br,
      <.h5(
        ^.cls := "fragment fade-in",
        "We can't do that without changing the Show object"
      ),
    slide(
      "Expression problem",
      <.br,
      <.blockquote(
        """
          |The goal is to define a datatype by cases, where one can add new cases to the datatype and new functions over the datatype,
          |without recompiling existing code, and while retaining static type safety (e.g., no casts).
        """.stripMargin)
      )
    )
  )

  val expressionProblem = chapterSlide(
    <.h2("The expression problem"),
    noHeaderSlide(
      <.blockquote(
       """
          |The goal is to define a datatype by cases, where one can add new cases to the datatype and new functions over the datatype,
          |without recompiling existing code, and while retaining static type safety (e.g., no casts).
        """.stripMargin
      )
    ),
    slide(
      "Show is an instance of the expression problem",
      <.p("We want to support more types"),
      <.p("We want to add more operations (showPretty)"),
      <.p("We do not want to change the Show object")
    )
  )

  val expressionSolution = chapterSlide(
    <.h2("How do type classes solve the expression problem"),
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
      <.p("Introduce a generic parameter A instead of concrete overloads.")
    ),
    noHeaderSlide(
      scalaCFragment(
        """
          | val intShow = new Show[Int] {
          |   def show(a: Int): String = s"an Int: ${a}"
          | }
          |
          | val stringShow = new Show[String] {
          |   def show(a: String): String = s"a String: ${a}"
          | }
          | // ....
        """.stripMargin),
      <.p("Provide implementations")
    ),
    noHeaderSlide(
      scalaC(
        """
          | object Show {
          |   def show[A](a: A, showImpl: Show[A]): String = showImpl.show(a)
          | }
          |
          | Show.show(3, intShow)
        """.stripMargin),
      <.p("Provide interface method")
    ),

    slide("Can't we make that simpler?",
      <.h5("Make the implementation parameter implicit"),
      scalaC(
        """
          | object Show {
          |   def show[A](a: A)(implicit showImpl: Show[A]): String = showImpl.show(a)
          | }
        """.stripMargin),
      scalaCFragment(
        """
          | implicit val intShow = new Show[Int] {
          |   def show(a: Int): String = s"an Int: ${a}"
          | }
        """.stripMargin),
      scalaCFragment(
        """
          | Show.show(3)
        """.stripMargin)
    ),
    noHeaderSlide(
      <.p("Different syntax, same thing"),
      scalaC(
        """
          | object Show {
          |   def show[A : Show](a: A): String = implicitely[Show[A]].show(a)
          | }
        """.stripMargin)
    ),
    noHeaderSlide(
      <.p("New types can easily be supported"),
      scalaC(
        """
          | sealed trait Shape
          | case class Circle(d: Int) extends Shape
          | case class Square(l: Int) extends Shape
        """.stripMargin),
      scalaCFragment(
        """
          | object Shape {
          |   implicit val shapeShow = new Show[Shape] {
          |     def show(a: Shape): String = a match {
          |       case Circle(d) => "a circle with diameter: ${d}"
          |       case Square(l) => "a square with length: ${l}"
          |     }
          |   }
          | }
        """.stripMargin),
      scalaCFragment(
        """
          | Show.show(Circe(3))
        """.stripMargin)
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
          motivatingExample
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
