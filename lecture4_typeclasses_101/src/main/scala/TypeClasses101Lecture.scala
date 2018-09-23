import PresentationUtil.{Enumeration, _}
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
        Item.fadeIn("Type classes as a way to manage change"),
        Item.fadeIn("The expression problem")
      )
    ),

    noHeaderSlide(
      <.h2("You have a question?"),
      <.h3("Ask it right away!")
    )
  )

  val motivation = chapter(
    chapterSlide(
      <.h2("What problem do type classes solve")
    ),

    slide(
      "Dealing with change",
      <.br,
      scalaC(
        """
          |object Execution {
          |  def resolveFields(fields: List[Field], resolver: (Field) => Option[Json]): List[Option[Json]] = {
          |    fields.map { field =>
          |      resolver(field) match {
          |        case Some(value) => wrapResult(value)
          |        case failure     => failure
          |      }
          |    }
          |  }
          |
          |  def wrapResult(json: Json): Json = ??
          |}
        """.stripMargin),
        <.br,
        <.br,
        <.p(
         ^.cls := "fragment fade-in",
        "You are asked to change the implementation to use Try")
    ),

    slide(
      "Dealing with change",
      <.h3("Try this"),
      <.br,
      scalaC(
        """
          |object Execution {
          |  def resolveFields(fields: List[Field], resolver: (Field) => Try[Json])): List[Try[[[Json]]]] = {
          |    fields.map { field =>
          |      resolver(field) match {
          |        case Success(value) => wrapResult(value)
          |        case failure     => failure
          |      }
          |    }
          |  }
          |  def wrapResult(json: Json): Json = ??
          |}
        """.stripMargin),
        <.br,
        <.p(
        ^.cls := "fragment fade-in",
        "You are asked again to change the implementation ...")
    ),

    slide(
     "Dealing with change",
      <.br,
      scalaC(
        """
          |object Execution {
          |  def resolveFields(fields: List[Field], resolver: (Field) => ErrorOr[Json])): List[ErrorOr[[[Json]]]] = {
          |    fields.map { field =>
          |      resolveSingleField(field) match {
          |        case Broken(value) => wrapResult(value)
          |        case failure     => failure
          |      }
          |    }
          |  }
          |  def wrapResult(json: Json): Json = ??
          |}
        """.stripMargin),
        <.br,
        <.p(
         ^.cls := "fragment fade-in",
        "You know how this continues ... :)")
    ),

    slide(
      "Dealing with change",
      <.h3("The only constant in your code base is its change"),
      <.h5("What does functional programming give to deal with this?")
    )
  )

  val discoverTheTypeClass = chapter(
    chapterSlide(
      <.h2("How do type classes help us with change?")
    ),

    slide(
      "Discover the type class",
      <.h3("Let's take a close look"),
      <.br,
      scalaC(
        """
          |fields.map { field =>
          |  resolver(field) match {
          |    case Success(value) => wrapResult(value) // <--- this changes
          |    case failure        => failure
          |  }
          |}
        """.stripMargin),
      <.br
     ),
    slide(
      "Discover the type class",
      <.h3("Employ wishful thinking"),
      <.br,
      scalaC(
        """
          |fields.map { field =>
          |  whenOk(resolver(field), wrapResult)
          |}
        """.stripMargin),
      <.br
    ),
    slide(
      "Discover the type class",
      <.h3("Different implementations"),
      <.br,
      scalaC(
        """
          |whenOk(val: Option[Json], fn: Json => Json): Option[Json] = ???
          |whenOk(val: Try[Json], fn: Json => Json): Try[Json] = ???
          |whenOk(val: ErrorOr[Json], fn: Json => Json): ErrorOr[Json] = ???
        """.stripMargin),
      <.br
    ),
    slide(
      "Discover the type class",
      <.h3("Extract the essence"),
      <.br,
      scalaC(
        """
          |whenOk(val: Option[Json], fn: Json => Json): Option[Json] = ???
          |whenOk(val: Try[Json], fn: Json => Json): Try[Json] = ???
          |whenOk(val: ErrorOr[Json], fn: Json => Json): ErrorOr[Json] = ???
        """.stripMargin),
      <.br,
      scalaCFragment(
        """
          |whenOk(val: F[Json], fn: (Json) => Json): F[Json]
        """.stripMargin),
      <.br,
      scalaCFragment(
        """
          |whenOk[A](val: F[A], fn: (A) => A): F[A]
        """.stripMargin),
      <.br,
      scalaCFragment(
        """
          |whenOk[A, B](val: F[A], fn: (A) => B): F[B]
        """.stripMargin)
    ),
    slide(
      "Discover the type class",
      <.h3("Encode it in a type class"),
      <.br,
      scalaC(
        """
          |trait WhenOk[F[_]] {
          |          // ^
          |          // |--- any type constructor
          |
          |  def whenOk[A, B](f: F[A], fn: (A) => B): F[B]
          |}
        """.stripMargin),
      Enumeration(
        Item.fadeIn("Create trait for the operation"),
        Item.fadeIn("Introduce generic parameter F[_]"),
        Item.fadeIn("Since F is universally quantified it must support all types, including new ones")
      )
    ),

    slide(
      "Discover the type class",
      <.h3("Consume the implementation"),
      <.br,
      scalaC(
        """
          |resolveFields(fields: List[Field], resolver: (Field) => Option[Json], impl: WhenOk[Json]): List[Option[Json]] = {
          |   fields.map { field =>
          |     impl.whenOk(resolver(field), wrapResult)
          |   }
          |}
        """.stripMargin)
      ),
    slide(
      "Discover the type class",
      <.h3("Provide type class instances"),
      <.br,
      scalaC(
        """
          |val whenOkOption = new WhenOk[Option] {
          |  def whenOk[A, B](value: Option[A], fn: (A) => B): Option[B] = {
          |    value match {
          |      case Some(v) => fn(v)
          |      case none    => none
          |    }
          |  }
          |}
        """.stripMargin),
      scalaCFragment(
        """
          |resolveFields(myFields, fieldResolver, whenOkOption)
        """.stripMargin)),
     slide(
      "Discover the type class",
      <.h3("That's kind of awesome!"),
      <.br,
      <.img(
        ^.alt := "Yeah",
        ^.src := "./img/yeah.gif"
      )
    ),
    slide(
      "Discover the type class",
      <.h3("Welcome the implicit parameter"),
      <.br,
      scalaC(
        """
          |resolveFields(fields: List[Field], resolver: (Field) => Option[Json])(implicit impl: WhenOk[Option[Json]]): List[Option[Json]] = {
          |   fields.map { field =>
          |     impl.whenOk(resolver(field), wrapResult)
          |   }
          |}
        """.stripMargin),
      <.br,
      scalaCFragment(
        """
          |object Execution {
          |  implicit val whenOkOption = new WhenOk[Option] {
          |    def whenOk[A, B](value: Option[A], fn: (A) => B): Option[B] = {
          |      value match {
          |        case Some(v) => v
          |        case none    => none
          |      }
          |    }
          |  }
          |}
        """.stripMargin)),
    <.br,
    scalaCFragment(
      """
        |resolveFields(myFields, fieldResolver)
      """.stripMargin),
    slide(
    "Discover the type class",
      <.h3("Make it future proof"),
      <.br,
      scalaC(
        """
          |resolveFields(fields: List[Field], resolver: (Field) => Option[Json])(implicit impl: WhenOk[Option[Json]]): List[Option[Json]] = {
          |   fields.map { field =>
          |     impl.whenOk(resolver(field), wrapResult)
          |   }
          |}
        """.stripMargin),
      scalaCFragment(
        """
          |resolveFields[F[_]](fields: List[Field], resolver: (Field) => F[Json], impl: WhenOk[F[Json]]): List[F[Json]] = {
          |   fields.map { field =>
          |     impl.whenOk(resolver(field), wrapResult)
          |   }
          |}
        """.stripMargin)
    ),
    slide("The Show type class",
      <.h3("Syntax matters"),
      <.br,
      scalaC(
        """
          |resolveFields[F[_] : WhenOk](fields: List[Field], resolver: (Field) => F[Json]): List[F[Json]] = {
          |   fields.map { field =>
          |     implicitly[WhenOk[F[Json]]].whenOk(resolveSingleField(field), wrapResult)
          |   }
          |}
        """.stripMargin)
    )
  )

  val expressionProblem = chapter(
    chapterSlide(
      <.h2("Express yourself")
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
    ),
    slide(
      "The expression problem",
      <.h3("Type classes are one solution for the expression problem")
    )
  )

  val summary = chapter(
    chapterSlide(
      <.h2("Type classes 101 summary")
    ),
    slide(
        "To sum it up",
        <.h4("Type classes are a mechanism to write extensible/polymorphic code without subtyping"),
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
          motivation,
          discoverTheTypeClass,
          expressionProblem,
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
