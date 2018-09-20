import PresentationUtil._
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object TypeClassesIncarnationsLecture extends JSApp {
  import Enumeration._

  val overview = chapter(
    chapterSlide(
      <.h1("Type classes in practice")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable("Introduction to the cats project"),
        Item.fadeIn("Eq"),
        Item.fadeIn("Ord"),
        Item.fadeIn("Monoid"),
        Item.fadeIn("Functor"),
        Item.fadeIn("Applicative"),
        Item.fadeIn("Monad")
      )
    ),

    noHeaderSlide(
      <.h2("You have a question?"),
      <.h3("Ask it right away!")
    )
  )

  val catsIntro = chapter(
    chapterSlide(
      <.h2("Introduction to the cats project")
    ),

    slide(
      "Cats is a scala library for \"categorical\" programming",
      <.br,
      <.p("Find it at: https://typelevel.org/cats/"),
      <.br,
      Enumeration(
        Item.stable("A set of useful abstractions for functional programming in scala"),
        Item.stable("Uses type classes to implement those abstractions"),
        Item.stable("Syntax to use type classes more conveniently"),
        Item.stable("Additional tooling to scrap boilderplate and scrutinize instances")
      )
    )
  )

  val typeClassEq = chapter(
    chapterSlide(
      <.h2("The Eq type class")
    ),

    slide(
      "Type safe equality",
      <.p("Encoding in type class is sensible since not all types define an equality relation"),
      scalaC(
        """
          |trait Eq[A] {
          |  def eqv(x: A, y: A): Boolean
          |  ...
          |}
        """.stripMargin),
      scalaCFragment(
       """
         | import cats.kernel.Eq
         | import cats.implicits._
         |
         | 42 === 42
         |     ^
         |     |----- Syntax that invokes uses the Eq instances
       """.stripMargin
      ),
      <.br,
      <.p("More information can be found here: https://typelevel.org/cats/typeclasses/eq.html")
    ))

  val typeClassOrder = chapter(
    chapterSlide(
      <.h2("The Order type class")
    )
  )

  val typeClassMonoid = chapter(
    chapterSlide(
      <.h2("The monoid type class")
    )
  )

  val typeClassFunctor = chapter(
    chapterSlide(
      <.h2("The functor type class")
    )
  )

  val typeClassApplicative = chapter(
    chapterSlide(
      <.h2("The applicative type class")
    )
  )

  val typeClassMonad = chapter(
    chapterSlide(
      <.h2("The monad type class")
    )
  )

  val summary = chapter(
    chapterSlide(
      <.h2("Type class incarnations")
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
          catsIntro,
          typeClassEq,
          typeClassOrder,
          typeClassMonoid,
          typeClassApplicative,
          typeClassMonad
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
