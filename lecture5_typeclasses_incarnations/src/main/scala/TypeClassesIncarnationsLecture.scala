import PresentationUtil.{slide, _}
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
      <.h3("What you will learn in this lecture"),
      <.br,
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
      "Welcome cats",
      <.h3("Cats is a scala library for \"categorical\" programming"),
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
      <.h3("Sensible type-safe equality via Eq"),
      <.p(<.small("More information can be found here: https://typelevel.org/cats/typeclasses/eq.html")),
      <.br,
      scalaC(
        """
          |trait Eq[A] {
          |  def eqv(x: A, y: A): Boolean
          |  // more 
          |}
        """.stripMargin),
   
      ),
    slide("Type safe equality",
      <.h3("Use it easily"),
      scalaC(
       """
         | import cats.kernel.Eq
         | import cats.implicits._
         |  
         | Eq[Int].eqv(10, 20)
         | //  ^    ^
         | //  |    |---- Use the eqv method
         | //  |
         | //  |----- Summon the instance       
       """.stripMargin
    ),
    <.br,
    scalaCFragment(
      """
        | 42 === 42
        | //  ^
        | //  |----- Syntax that invokes the Eq instances
      """.stripMargin
    )
    ))

  val typeClassOrder = chapter(
    chapterSlide(
      <.h2("The Order type class")
    ),
    slide(
      "Type safe ordering",
      <.h3("Sensible type-safe ordering via Order"),
      <.p(<.small("More information can be found here: https://typelevel.org/cats/typeclasses/order.html")),
      <.br,
      scalaC(
        """
          |trait Order[A] {
          |  def compare(x: A, y: A): Int
          |  // more 
          |}
        """.stripMargin),
   
      ),
    slide("Type safe ordering",
      <.h3("Use it easily"),
      scalaC(
       """
         | import cats.kernel.Order
         | import cats.implicits._
         |  
         | Order[Int].compare(10, 20)
         | //  ^    ^
         | //  |    |---- Use the compare method
         | //  |
         | //  |----- Summon the instance       
       """.stripMargin
    ),
    <.br,
    scalaCFragment(
      """
        | Order[Int].max(10, 20)
        |
        | 42 compare 42
        | //  ^
        | //  |----- Syntax that invokes uses the Order instances
      """.stripMargin
    ))
  )

  val typeClassMonoid = chapter(
    chapterSlide(
      <.h2("The monoid type class")
    ),
    slide(
      "Combine things",
      <.h3("Combine all sorts of things"),
      <.p(<.small("More information can be found here: https://typelevel.org/cats/typeclasses/monoid.html")),
      <.br,
      scalaC(
        """
          |trait Monoid[A] {
          |  def empty: A
          |  def combine(x: A, y: A): A
          |}
        """.stripMargin),
      ),
    slide("Combine things",
      <.h3("Use it easily"),
      scalaC(
       """
         | import cats.kernel.Monoid
         | import cats.implicits._
         |  
         | Monoid[Int].combine(10, 20) 
         | //  ^         ^
         | //  |         |---- Use the combine method
         | //  |
         | //  |----- Summon the instance       
       """.stripMargin
    ),
    <.br,
    scalaCFragment(
      """
        | 42 combine 42
        | //  ^
        | //  |----- Syntax that invokes uses the Monoid instances
      """.stripMargin
    )),
   slide(
     "Obey the laws",
     <.h3("Monoid laws"),
     <.p,
     Enumeration(
       Item.stable("Associativity: a combine (b combine c) == (a combine b) combine c"),
       Item.stable("Identity element: a combine empty == a")
     )
   ),
   slide(
     "All those monoids",
     <.h3("There are many possible monoid instances"),
     <.p,
     Enumeration(
       Item.stable("Int with (+, *)"),
       Item.stable("List[A]"),
       Item.stable("Set[A]"),
       Item.stable("Bloomfilters"),
       Item.stable("Many more ...")
     )
    ),
    slide(
      "Monoids everwhere",
      <.img(
        ^.alt := "Everywhere",
        ^.src := "./img/monoids-monoids-everywhere.jpg"
      )
    )
  ) 

  val typeClassFunctor = chapter(
    chapterSlide(
      <.h2("The functor type class")
    ),
    slide(
      "Represent effects",
      <.h3("Map things and capture effects"),
      <.p(<.small("More information can be found here: https://typelevel.org/cats/typeclasses/functor.html")),
      <.br,
      scalaC(
        """
          |trait Functor[F[_]] {
          | 
          |  def map[A, B](x: F[A])(f: A => B): F[B]
          |}
        """.stripMargin),
      ),
    slide("Represent effects",
      <.h3("Use it easily"),
      scalaC(
       """
         | import cats.Functor
         | import cats.implicits._
         |  
         | Functor[List[Int]].map(List(10, 20, 30), identity) 
         | //  ^               ^
         | //  |               |---- Use the map method
         | //  |
         | //  |----- Summon the instance       
       """.stripMargin
    ),
    <.br,
    scalaCFragment(
      """
        | List(1,3,4) map identity
        | //          ^
        | //          |----- Syntax that invokes uses the Functor instances
      """.stripMargin
    )),
   slide(
     "Obey the laws",
     <.h3("Functor laws"),
     <.p,
     Enumeration(
       Item.stable("Identity:    f.map(identity) == f"),
       Item.stable("Composition: f.map(g).map(h) == f.map(g.compose(h))")
     )
   ),
   slide(
     "All those functors",
     <.h3("There are many possible functor instances"),
     <.p,
     Enumeration(
       Item.stable("List[A]"),
       Item.stable("Set[A]"),
       Item.stable("Option[A]"),
       Item.stable("Either[A, B]"),
       Item.stable("IO[A]"),
       Item.stable("Many more ...")
     )
    )
   ) 

  val typeClassApplicative = chapter(
    chapterSlide(
      <.h2("The applicative type class")
    ),
    slide("Map over things with structure",
      <.h3("Monoidal functors"),
      <.p(<.small("More information can be found here: https://typelevel.org/cats/typeclasses/applicative.html")),
      <.br,
      scalaC(
        """
          |import cats.Functor
          |
          |trait Applicative[F[_]] extends Functor[F] {
          |  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]
          |
          |  def pure[A](a: A): F[A]
          |
          |  def map[A, B](fa: F[A])(f: A => B): F[B] = ap(pure(f))(fa)
          |}
        """.stripMargin)),

    slide("Map over things with structure",
      <.h3("Use it easily"),
      scalaC(
        """
          | import cats.Functor
          | import cats.implicits._
          |
          | Applicative[Option].pure(3) == Some(3)
          | Applicative[Option].ap(Some((_ + 1)), Some(3)) == Some(4)
          | Applicative[Option].ap(None, Some(3)) == None
          | Applicative[Option].ap(Some((_ + 1)), None) == None
          |
        """.stripMargin
      )),
    slide("It's just apply",
      <.h3("The ladder of function application"),
      <.br,
        scalaCFragment(
          """
            | val add1: Int => Int = (_ + 1)
            |
            | // normal application
            | add1(3) == 4
          """.stripMargin),
         scalaCFragment(
         """
           | // functorial application
           | Some(3).map(add1) == Some(4)
         """.stripMargin),
        scalaCFragment(
          """
            | // monoial functorial application
            | Applicative[Option].app(Some(add1), Some(3))
          """.stripMargin
         )
    ),
    slide("More useful operations",
      <.h3("More usefule methods"),
      <.br,
      scalaC(
        """
          |Applicative[Option].map3(Some(1), Some(2), Some(3))(_ + _ + _)
        """.stripMargin),
       <.br,
       scalaCFragment(
          """
            |import cats.instances.list._
            |import cats.syntax.traverse._
            |
            |List(Some(1), Some(3)).sequence == Some(List(1, 3))
          """.stripMargin),
        <.br,
        scalaCFragment(
          """
            |import cats.instances.list._
            |import cats.syntax.traverse._
            |
            |List(1,3,3).map(Some(_)).sequence == Some(List(1,3,3))
            |
            |List(1,3,3).traverse(Some(_)) == Some(List(1,3,3))
          """.stripMargin)
    ),
    slide("Obey the laws",
      <.h3("Applicative functor laws"),
      <.br,
      Enumeration(
        Item.stable("Identity:    ap(pure(identity), v) == v"),
        Item.stable("Homomorphism: ap(f, pure(x)) == pure (f x)"),
        Item.stable("Associativity: ap(ap(a, b), c) == ap(a, ap(b, c)) ")
      )
    ),
    slide(
      "All those applicative functors",
      <.h3("There are quite some applicative functor instances"),
      <.p,
      Enumeration(
        Item.stable("Either[A]"),
        Item.stable("Option[A]"),
        Item.stable("IO[A]")
      )
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
          typeClassApplicative //,
          //typeClassMonad
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
