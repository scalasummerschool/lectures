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
      <.h1("Type Class Incarnations")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable("Categorical Programming"),
        Item.fadeIn("Typelevel Cats"),
        Item.fadeIn("Cats Kernel"),
        Item.fadeIn("Cats Core"),
        Item.fadeIn("Monad Instances")
      )
    ),

    noHeaderSlide(
      <.h2("You have a question?"),
      <.h3("Ask it right away!")
    )
  )

  val categorical = chapter(
    chapterSlide(
      <.h2("Categorical Programming")
    ),

    slide(
      "Categorical Programming",
      <.p("Using concepts from Category Theory to enhance the FP toolbox."),
      <.p(
        ^.cls := "fragment fade-in",
        "But what does that mean in practice?"
      )
    ),

    slide(
      "Categorical Programming: TL;DR",
      <.p("We use type classes to encode certain properties to make our code more generic and reusable.")
    ),

    noHeaderSlide(
      <.h3("Let's have an example"),
      <.br,
      <.h4("Equality")
    ),

    slide(
      "Categorical FP: equality",
      <.p("Scala comes already with an equality method build into its objects. But that allows us to write the following:"),
      <.br,
      scalaC("""
        // equality between different types
        "hello" == 1

        // wrong inequality
        class Person(name: String)

        // yields `false`
        new Person("Gandalf") == new Person("Gandalf")
      """)
    ),

    slide(
      "Categorical FP: equality",
      <.p("We want an equality proof which is strict in its parameter types and tests value equality."),
      <.br,
      scalaC("""
        def eq[A](a: A, b: A): Boolean
      """)
    ),

    slide(
      "Categorical FP: equality",
      <.p("We want that to be a polymorphic property."),
      <.br,
      scalaC("""
        trait Eq[A] {

          def eq(a: A, b: A): Boolean
        }
      """)
    ),

    slide(
      "Categorical FP: equality",
      scalaC("""
        // doesn't compile
        implicitly[Eq[String]].eq("hello", 1)
      """),
      scalaCFragment("""
        implicit val personEq = new Eq[Person] {
          def eq(a: Person, b: Person): Boolean = a.name == b.name
        }

        // yields `true`
        implicitly[Eq[Person]].eq(new Person("Gandalf"), new Person("Gandalf"))
      """)
    ),

    slide(
      "Categorical FP: equality",
      scalaC("""
        // add properties to type parameter
        def isIdentity[A: Eq](a: A)(f: A => A): Boolean = {
          implicitly[Eq[A]].eq(a, f(a))
        }
      """)
    ),

    noHeaderSlide(
      <.h3("You know what?"),
      <.br,
      <.h5("There is a library with usefull type classes already available")
    )
  )

  val catsLibrary = chapter(
    chapterSlide(
      <.h2("Typelevel Cats")
    ),

    slide(
      "Cats",
      <.img(
        ^.alt   := "Cats Logo",
        ^.src   := "./img/cats-logo.png",
        ^.width := "40%"
      )
    ),

    slide(
      "Cats",
      <.p("Cats, short for ", <.strong("Cat"), "egorie", <.strong("s")),
      <.br,
      <.a(
        ^.href := "https://typelevel.org/cats",
        "https://typelevel.org/cats/"
      )
    ),

    slide(
      "Cats",
      Enumeration(
        Item.stable("a set of useful abstractions"),
        Item.fadeIn("syntax to use type classes more conveniently"),
        Item.fadeIn("additional tooling to scrap boilderplate and scrutinize instances"),
        Item.fadeIn("additional tooling to scrap boilderplate and scrutinize instances")
      )
    ),

    noHeaderSlide(
      <.h3("Let's have a look into the project")
    )
  )

  val catsKernel = chapter(
    chapterSlide(
      <.h2("Cats Kernel")
    ),

    slide(
      "Cats Kernel",
      <.a(
        ^.href := "https://github.com/typelevel/cats/tree/master/kernel/src/main/scala/cats/kernel",
        "cats/kernel"
      )
    ),

    noHeaderSlide(
      <.h3("Equality")
    ),

    slide(
      "Cats Kernel: Eq",
      <.p("Equality is already build into the library."),
      <.br,
      scalaC("""
        trait Eq[A] {

          def eqv(x: A, y: A): Boolean

          def neqv(x: A, y: A): Boolean = !eqv(x, v)
        }
      """),
      scalaCFragment("""
        object Eq {

          // resolve instance for type `A`
          def apply[A](implicit ev: Eq[A]): Eq[A] = ev
        }
      """)
    ),

    slide(
      "Cats Kernel: Eq",
      scalaC("""
        import cats.kernel.Eq
        import cats.implicits._

        Eq[Int].eqv(1, 2) == Eq.apply[Int].eqv(1, 2)
                          == intEq.eqv(1, 2)
                          == false
      """),
      scalaCFragment("""
        // doesn't compile
        Eq[Int].eqv("hello", 2) 
      """)
    ),

    slide(
      "Cats Kernel: Eq",
      scalaC("""
        // Cats also comes with some convenient syntax
        1 === 2

        // equal to
        Eq[Int].eqv(1, 2)
      """)
    ),

    slide(
      "Cats Kernel: Eq",
      <.p("There are instances for all primitive types and collections like List, Set, etc."),
      <.br,
      <.p("But that is true for all Kernel type classes.")
    ),

    noHeaderSlide(
      <.h3("After equality follows order")
    ),

    slide(
      "Cats Kernel: Order",
      scalaC("""
        trait Order[A] extends Eq[A] {

          /** Result of comparing `x` with `y`. Returns an Int whose sign is:
            *  - negative iff `x < y`
            *  - zero     iff `x = y`
            *  - positive iff `x > y`
            */
          def compare(x: A, y: A): Int
        }
      """)
    ),

    slide(
      "Cats Kernel: Order",
      scalaC("""
        import cats.kernel.Order

        Order[Int].compare(1, 2) === -1


        import cats.kernel.Comparison.LessThan

        Order[Int].comparison(1, 2) === LessThan
      """)
    ),

    slide(
      "Cats Kernel: Order",
      scalaC("""
        def largestToStr[A: Order](x: A, b: A): String = 
          Order[A].comparison(x, y) match {
            case GreaterThan => s"x: $x"
            case EqualTo     => s"x and y are equal: $x"
            case LessThan    => s"y: $y"
          }


        largestToStr(2, 1) === "x: 2"
      """)
    ),

    noHeaderSlide(
      <.h3("We compared now we combine with Semigroup")
    ),

    slide(
      "Cats Kernel: Semigroup",
      scalaC("""
        trait Semigroup[A] {

          def combine(x: A, y: A): A
        }
      """)
    ),

    slide(
      "Cats Kernel: Semigroup",
      scalaC("""
        import cats.kernel.Semigroup

        Semigroup[Int].combine(1, 2) === 3

        // again we have some convenient syntax
        1 |+| 2 === 3
      """)
    ),

    slide(
      "Cats Kernel: Semigroup",
      scalaC("""
        def largeEnough[A: Semigroup: Order](limit: A)(x: A, y: A): Boolean =
          Order[A].gt(Semigroup[A].combine(x, y), limit)


        val le = largeEnough(5)

        le(1, 2) === false
      """)
    ),

    noHeaderSlide(
      <.h3("But wait, there is more"),
      <.br,
      <.h4("Semigroup follows some laws")
    ),

    slide(
      "Cats Kernel: Semigroup laws",
      scalaC("""
        // associativity
        a |+| (b |+| c) == (a |+| b) |+| c
      """)
    ),

    slide(
      "Cats Kernel: laws",
      <.p("Kernel laws are checked with a special test suit in kernel-laws.")
    ),

    exerciseSlide(
      "Let's Code: Kernel",
      bash("""
        sbt> project typeclasses-incarnations-exercises
        sbt> test:testOnly exercise5.Kernel
      """)
    ),

    noHeaderSlide(
      <.h3("We had type classes for simple types"),
      <.br,
      <.h4("Let's continue with higher order types")
    )
  )

  val catsCore = chapter(
    chapterSlide(
      <.h2("Cats Core")
    ),

    slide(
      "Cats Core",
      <.p("Imagine the following situation:"),
      <.br,
      scalaC("""
        case class Person(name: String)

        def names(persons: List[Person]): List[String] = 
          persons.map(_.name)
      """)
    ),

    slide(
      "Cats Core",
      <.p("But what if the context is unknown or you have make this function generic?")
    ),

    slide(
      "Cats Core",
      <.p("`F[_]` is too generic. No operations are atteched."),
      <.br,
      scalaC("""
        def names[F[_]](persons: F[Person]): F[String] = ???
      """)
    ),

    noHeaderSlide(
      <.h3("Functor")
    ),

    slide(
      "Cats Core: Functor",
      scalaC("""
        trait Functor[F[_]] {

          def map[A, B](fa: F[A])(f: A => B): F[B]
        }
      """)
    ),

    slide(
      "Cats Core",
      <.a(
        ^.href := "https://github.com/typelevel/cats/tree/master/core/src/main/scala/cats",
        "cats/core"
      )
    ),

    slide(
      "Cats Core: Functor",
      scalaC("""
        import cats.Functor
        import cats.implicits._

        Functor[List].map(List(1, 2))(_ + 1) === List(2, 3)
      """)
    ),

    slide(
      "Cats Core: Functor",
      scalaC("""
        // it comes again with some convenient syntax
        // `fmap` is from Cats
        List(1, 2).fmap(_ + 1) === List(2, 3)

        // `map` is build into `List`
        List(1, 2).map(_ + 1)  === List(2, 3)
      """)
    ),

    slide(
      "Cats Core: Functor",
      scalaC("""
        def names[F[_]: Functor](persons: F[Person]): F[String] = 
          Functor[F].map(persons)(_.name)


        names(List(Person("Gandalf"))) === List("Gandalf")
        names(Some(Person("Gandalf"))) === Some("Gandalf")
      """)
    ),

    noHeaderSlide(
      <.h3("We have laws against")
    ),

    slide(
      "Cats Core: Functor laws",
      scalaC("""
        // F[_]: Functor
        val f: F[A]
        val g: F[A]

        // composition
        f.map(g).map(h) == f.map(g.compose(h))

        // identity
        f.map(identity) == f
      """)
    ),

    slide(
      "Cats Core: Functor composition",
      <.p("We can compose Functors as needed."),
      <.br,
      scalaC("""
        implicit val listOpt = Functor[List].compose[Option]

        List(Some(1))).fmap(_ + 1) === List(Some(2))
      """),
      scalaCFragment("""
        names(List(Some(Person("Gandalf")))) === List(Some("Gandalf"))
      """)
    ),

    slide(
      "Cats Core: Functor instances",
      <.p("There are instances for Scala ADTs. That is also true for other core type classes.")
    ),

    exerciseSlide(
      "Let's Code: Functors",
      bash("""
        sbt> project typeclasses-incarnations-exercises
        sbt> test:testOnly exercise5.FunctorsSpec
      """)
    ),

    noHeaderSlide(
      <.h3("But I have a mapping function with more than one parameter!"),
      <.br,
      <.h3(
        ^.cls := "fragment fade-in",
        "Applicative"
      )
    ),

    slide(
      "Cats Core: Applicative",
      scalaC("""
        val combine: Int => Int => Int = a => b => a + b

        // only allows functions with arity 1
        Some(1).fmap(combine) === Some(b => 1 + b)
      """)
    ),

    slide(
      "Cats Core: Applicative",
      scalaC("""
        trait Applicative[F] extends Functor[F] {

          def pure[A](a: A): F[A]

          def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]
        }
      """)
    ),

    slide(
      "Cats Core: Applicative",
      scalaC("""
        import cats.Applicative

        // lift a value
        Applicative[Option].pure(1) === Some(1)

        // apply a function in context `F[_]`
        Applicative[Option].ap[Int, Int](Some(_ + 1))(Some(1)) === Some(2)
      """)
    ),

    slide(
      "Cats Core: Applicative",
      scalaC("""
        val combine: Int => Int => Int = a => b => a + b

        // only allows functions with arity 1
        Some(1).fmap(combine) === Some(b => 1 + b)

        // but there is a trick
        Some(1).fmap(combine).ap(Some(2)) === Some(3)
      """),
      scalaCFragment("""
        // convenient syntax
        Some(1).fmap(combine) <*> Some(2) === Some(3)
      """)
    ),

    slide(
      "Cats Core: Applicative laws",
      scalaC("""
        // homomorphism
        pure(f) <*> pure(x) === pure(f(x))

        pure((a: Int) => a + 1) <*> pure(2) === pure(2 + 1)

        // interchange
        ff <*> pure(x) === pure(g => g(x)) <*> ff

        Some((a: Int) => a + 1) <*> pure(2) === {
          pure((g: Int => Int) => g(2)) <*> Some(_ + 1)
        }
      """)
    ),

    slide(
      "Cats Core: Applicative laws",
      scalaC("""
        // composition
        pure(composition) <*> g <*> f <*> x === {
          g <*> f <*> x
        }

        pure(composition) <*> Some((a: Int) => a.toString) <*> Some((a: Int) => a + 1) <*> Some(1) === {
          Some((a: Int) => a.toString) <*> Some((a: Int) => a + 1) <*> Some(1)
        }

        // identity
        pure(identity) <*> x === x

        pure(identity) <*> Some(1) === Some(1)
      """)
    ),

    slide(
      "Cats Core: Applicative",
      <.p("It is a Functor, therefore, you can compose it."),
      <.br,
      scalaC("""
        implicit val listOpt = Applicative[List].compose[Option]

        listOpt.ap[Int, Int](List(Some(_ + 1)))(List(Some(1))) === {
          List(Some(2))
        }
      """)
    ),

    exerciseSlide(
      "Let's Code: Applicatives",
      bash("""
        sbt> project typeclasses-incarnations-exercises
        sbt> test:testOnly exercise5.ApplicativesSpec
      """)
    ),

    noHeaderSlide(
      <.h3("But apply effectfull functions to my values"),
      <.br,
      <.h4("Monad")
    ),

    slide(
      "Cats Core: Monad",
      scalaC("""
        // expects: "name: <person_name>"
        def parse[F[_]](raw: F[String]): F[Person]

        val r = Some("name: Gandalf").fmap(parse)

        r === Some(Some(Person("Gandalf")))
      """)
    ),

    slide(
      "Cats Core: Monad",
      scalaC("""
        trait Monad[F[_]] extends Applicative[F] {

          def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
        }
      """)
    ),

    slide(
      "Cats Core: Monad",
      scalaC("""
        val r = Monad[Option].flatMap(Some((1.0, 2.0))) { case (a, b) => 
          if (b > 0.0) Some(a / b)
          else         None
        }

        r === Some(0.5)
      """)
    ),

    slide(
      "Cats Core: Monad",
      scalaC("""
        // expect: "name: <person_name>"
        def parse[F[_]](raw: String): F[Person]

        val r = Monad[Option]
          .flatMap(Some("name: Gandalf"))(parse)
          .fmap(p => Person(p.name.toUpperCase))


        r === Some(Person("GANDALF"))
      """)
    ),

    slide(
      "Cats Core: Monad laws",
      scalaC("""
        // left identity
        pure(a).flatMap(f) == pure(f(a))

        // right identity
        fa.flatMap(pure) === fa

        // associativity
        (pure(a).flatMap(f)).flatMap(g) === {
          pure(a).flatMap(a => f(a).flatMap(g))
        }
      """)
    ),

    exerciseSlide(
      "Let's Code: Monads",
      bash("""
        sbt> project typeclasses-incarnations-exercises
        sbt> test:testOnly exercise5.MonadsSpec
      """)
    ),

    noHeaderSlide(
      <.h3("What is with Monad composition?"),
      <.br,
      <.h4(
        ^.cls := "fragment fade-in",
        "They aren't composable"
      )
    )
  )

  val monadTransformers = chapter(
    chapterSlide(
      <.h2("Monad Transformers")
    ),

    slide(
      "Monad Transformers",
      <.p("Using Monad Transformers we are able to simulate composition.")
    ),

    slide(
      "Monad Transformers",
      scalaC("""
        // doesn't work
        implicit val listOpt = Monad[List].compose[Option]
      """),
      scalaCFragment("""
        // but
        case class ListOption[A](value: List[Option[A]])

        implicit val listOpt = new Monad[List[Option]] {

          def flatMap[B](f: A => List[Option[B]]): List[Option[B]] = 
            value.flatMap { 
              case Some(v) => f(v)
              case None    => List(None)
            }
        }
      """)
    ),

    slide(
      "Monad Transformers",
      <.p("But now we have to implement every combination, which sucks.")
    ),

    slide(
      "Monad Transformers",
      scalaC("""
        case class OptionT[F[_], A](value: F[Option[A]])

        implicit def optionT[F[_]: Monad] = 
          new Monad[({ type O[A] = OptionT[F, A] })#O] {

            def flatMap[A, B](fa: F[Option[A]])
                             (f: A => F[Option[B]]): F[Option[B]] = 
              Monad[F].flatMap(fa) { 
                case Some(v) => f(v)
                case None    => Monad[F].pure(None)
              }
          }
      """)
    ),

    slide(
      "Monad Transformers",
      scalaC("""
        // expect: "name: <person_name>"
        def parse[F[_]](raw: String): F[Person]

        val r = Monad[OptionT[List]]
          .flatMap(List(Some("name: Gandalf")))(parse)
          .fmap(p => Person(p.name.toUpperCase))


        r === List(Some(Person("GANDALF")))
      """)
    ),

    slide(
      "Monad Transformers",
      <.p("Cats provides some data types which can be used as Monad Transformers."),
      <.br,
      <.a(
        ^.href := "https://github.com/typelevel/cats/tree/master/core/src/main/scala/cats/data",
        "cats/core/data"
      )
    ),

    slide(
      "Monad Tranformers",
      scalaC("""
        // our code becomes
        case class OptionT[F, A](value: F[Option[A]] {

          def flatMap[B](f: A => F[Option[B]])
                        (implicit M: Monad[F]): F[Option[B]] = 
            M.flatMap(value) { 
              case Some(v) => f(v)
              case None    => M.pure(None)
            }

          ...
        }
      """)
    ),

    exerciseSlide(
      "Let's Code: MonadTransformers",
      bash("""
        sbt> project typeclasses-incarnations-exercises
        sbt> test:testOnly exercise5.MonadTransformersSpec
      """)
    )
  )

  val summary = chapter(
    chapterSlide(
      <.h2("Summary")
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
          categorical,
          catsLibrary,
          catsKernel,
          catsCore,
          monadTransformers,
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
