
import SlideshowUtil._
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Lecture extends JSApp {

  import Enumeration._

  val overview = chapter(
    chapterSlide(
      <.h1("Scala 101")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable(<.p("Scala background")),
        Item.fadeIn(<.p("SBT: manage your projects")),
        Item.fadeIn(<.p("introduction into Scala (our first code yeah)"))
      )
    ),

    noHeaderSlide(
      <.h2("You have a question?"),
      <.h3("Ask it right away!")
    )
  )

  val introduction = chapter(
    chapterSlide(
      <.h1("What is Scala? And why?")
    ),

    slide(
      "In a nutshell",
      Enumeration(
        Item.stable(<.p("strict and statically typed programming language")),
        Item.fadeIn(<.p(<.b("Sca"), "lable ", <.b("La"), "nguage")),
        Item.fadeIn(<.p("support for JVM, native and JavaScript")),
        Item.fadeIn(<.p("Martin Orderky et all in 2005"))
      )
    ),

    slide(
      "Why Scala: JVM",
      Enumeration(
        Item.stable(<.p("access to Java ecosystem")),
        Item.fadeIn(<.p("or any ecosystem compiling to ByteCode)")),
        Item.fadeIn(<.p("JVM performance (JIT, GC)")),
        Item.fadeIn(<.p("... and tooling (metrics, debugging, etc.)")),
      )
    ),

    slide(
      "Why Scala: brings its own Ecosysten",
      Enumeration(
        Item.stable(<.p("large projects like Spark or Akka")),
        Item.fadeIn(<.p("strong library support for HTTP, FP, OOP, JDBC, ..."))
      )
    ),

    slide(
      "Why Scala: Fusion of FP and OOP",
      Enumeration(
        Item.stable(<.p("with additional Type-Level programming on top")),
        Item.fadeIn(<.p("choose paradigm which solve your problem best ...")),
        Item.fadeIn(<.p("... stay in the same language"))
      )
    ),

    slide(
      "Why Scala: Industry & Academia combined",
      Enumeration(
        Item.stable(<.p("big players use it: Twitter, Stripe to name a few")),
        Item.fadeIn(<.p("backed by Lightbend")),
        Item.fadeIn(<.p("backed by EPFL")),
        Item.fadeIn(<.p("... and of course by an Open Source community"))
      )
    ),

    noHeaderSlide(
      <.h2("Are you convinced?"),
      <.h4(
        ^.cls := "fragment fade-in",
        "Great, then let's move on and learn how to use it."
      )
    )
  )

  val expressions = chapter(
    chapterSlide(
      <.h1("Expressions"),
      <.h3("Basic building blocks")
    ),

    slide(
      "Expressions",
      <.p("Scala programs are made up from expressions and declarations.")
    ),

    slide(
      "Expressions",
      <.p("Expression can be values, combination of values or functions applied to values. And they get reduced to results.")
    ),

    slide(
      "Values as Expressions",
      code("""
        // already in reduced form
        // literals
        1
        'a'
        "Hello"

        // values
        val a = "world"
        a
      """)
    ),

    slide(
      "Operations as Expressions",
      code("""
        1 + 2
      """),
      codeFragment("""
        ==> 3
      """),
      codeFragment("""
        val w = "world"

        "Hello " + a + "!"
      """),
      codeFragment("""
        ==> "Hello !"
      """)
    ),

    slide(
      "Block Expressions",
      code("""
        {
          val a = 1 + 2
      
          a * 5
        }
      """),
      codeFragment("""
        {
          val a = 3
      
          a * 5
        }
      """),
      codeFragment("""
        {
          3 * 5
        }
      """),
      codeFragment("""
         ==> 15
      """)      
    ),

    noHeaderSlide(
      <.h2("Expressions return typed results"),
      <.br,
      codeFragment("""
        1       // Int
        "hello" // String

        1.0 + 2.0 // reduces to a Double
      """)
    ),

    noHeaderSlide(
      <.h2("But what are types?")
    )
  )

  val types = chapter(
    chapterSlide(
      <.h1("Types"),
      <.h3("Make your code more strict")
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
          expressions,
          types
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
