
import PresentationUtil._
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object SideEffectsLecture extends JSApp {

  import Enumeration._

  val overview = chapter(
    chapterSlide(
      <.h1("Side Effects")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable("Side Effects"),
        Item.fadeIn("Pros/Cons"),
        Item.fadeIn("Functional Side Effects"),
        Item.fadeIn("Side Effects in Code"),
      )
    )
  )

  val introduction = chapter(
    chapterSlide(
      <.h2("Side Effects"),
      <.h3("What Now?")
    ),

    slide(
      "What are side effects",
      <.h3("effects outside of the local scope of a function")
    ),

    slide(
      "Side effects: setting a var",
      scalaC("""
        var x = 0

        def inc(): Int = {
          x = x + 1
          x
        }
      """)
    ),

    slide(
      "Side effects: interacting with the OS",
      scalaC("""
        println("Hello")
        readLine()
      """)
    )
  )

  val pros = chapter(
    chapterSlide(
      <.h2("Side Effects: Pros"),
      <.h3("But Why?")
    ),

    slide(
      "Side effects: pros",
      Enumeration(
        Item.stable("no need - theoretically speaking"),
        Item.stable("not practical to work without"),
        Enumeration(
          Item.stable("interact with \"world\" (read files, send network request)"),
          Item.stable("performance of update vs copy")
        )
      )
    ),

    noHeaderSlide(
      <.h3("Ah, Ok.")
    )
  )

  val cons = chapter(
    chapterSlide(
      <.h2("Side Effects: Cons"),
      <.h3("So Why Not?")
    ),

    slide(
      "Side effects: cons",
      <.h3("loss of referential transparency"),
      scalaC("""
        f(g(), g()) != { val a = g(); f(a, a) }
      """)
    ),

    slide(
      "Side effects: cons",
      <.h3("common source of misunderstandings/bugs"),
      scalaC("""
        seq.grouped(3)
        !=
        {
          val g = seq.grouped(3)
          val s = seq.size
          g
        }
      """)
    ),

    slide(
      "Side effects: cons",
      <.h3("loss of optimization opportunities"),
      scalaC("""
        var cnt = 0
        for (el <- seq) {
          cnt = cnt + 1
        }
      """),
      <.h4("vs"),
      scalaC("""
        seq.reduce((l, r) => l + r)
      """)
    )
  )

  val functional = chapter(
    chapterSlide(
      <.h2("Functional Side Effects"),
      <.h3("Interesting! So What Now?")
    ),

    slide(
      "Functional side effects",
      <.h3("no side effects in our functional programs")
    ),

    slide(
      "Functional side effects",
      <.h3("instead: store intended side effect for execution \"some time later\"")
    )
  )

  val code = chapter(
    chapterSlide(
      <.h2("Side Effects in Code"),
      <.h3("Ok. One Last Question: What?")
    ),

    slide(
      "Side effects in code",
      <.h2("Introduce IO[A]"),
      <.h3("side effect(s) resulting in a value of type A")
    ),

    slide(
      "Side effects in code",
      scalaC("""
        println("Hello World"): IO[Unit]

        readLine(): IO[String]
      """)
    ),

    slide(
      "Consequences of IO[A]",
      <.h3("restoration of referential transparency"),
      scalaC("""
        f(println(), println()) == { val pl = println(); f(pl, pl) }
      """)
    ),

    slide(
      "Consequences of IO[A]",
      <.h3("forcing marked code/handling of effects"),
      <.h4("compare to null vs None")
    ),

    noHeaderSlide(
      <.h2("Example"),
      <.h3("Neat! Let's Use It.")
    ),

    slide(
      "Example",
      scalaC("""
        val name = readLine()

        println("Hello" + name)
      """)
    ),
    
    slide(
      "Example",
      scalaC("""
        val name = readLine() //: IO[String]

        println("Hello" + name) //: IO[Unit]
      """)
    ),

    slide(
      "Example",
      scalaC("""
        for {
          name <- readLine()
           
          _ <- println("Hello" + name)
        } yield () //: IO[Unit]
      """)
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
          introduction,
          pros,
          cons,
          functional,
          code
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
