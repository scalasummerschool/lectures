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
      <.h1("Scala Standard Library")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable(<.p("Primitives")),
        Item.fadeIn(<.p("Collections")),
        Item.fadeIn(<.p("Option")),
        Item.fadeIn(<.p("Either"))
      )
    ),

    noHeaderSlide(
      <.h2("You have a question?"),
      <.h3("Ask it right away!")
    )
  )

  val primitives = chapter(
    chapterSlide(
      <.h2("Primitives")
    ),

    slide(
      "Primitives",
      <.p("We already saw some primitives over the course of this workshop. But let's take a closer look what Scala actually provides.")
    ),

    noHeaderSlide(
      <.h3("Boolean")
    ),

    slide(
      "Primitives: Boolean",
      code("""
        Boolean - true false
      """)
    ),

    slide(
      "Primitives: Boolean operators",
      code("""
        !a     // negation

        a & b  // AND, always evaluates `b`
        a && b // AND, evaluates `b` only if `a` is true
        
        a | b  // OR, always evaluates b
        a || b // OR, evaluates `b` only if `a` is true
        
        a > b  // greater than
        a < b  // smaller than
        a == b // equal
        a != b // unequal
      """)
    ),

    noHeaderSlide(
      <.h3("Numbers")
    ),

    slide(
      "Primitives: numbers",
      code("""
        Byte    - Integer from -128 to 127
        Short   - Integer from -32,768 to 32,767
        Int     - Integer from -2,147,483,648 to 2,147,483,647
        Long    - Integer from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
        Float   - +- 1.40129846432481707e-45 to 3.40282346638528860e+38
        Double  - +- 4.94065645841246544e-324 to 1.79769313486231570e+308
      """)
    ),

    slide(
      "Primitives: number operators",
      code("""
        1 + 2 == 3    // addition
        1 - 2 == -1   // substraction
        2 % 2 == 0    // modulo
        2 * 2 == 4    // multiplication
      """),
      codeFragment("""
        -1.abs == 1   // absolute value
        2.max(3) == 3 // find the maximum value
        2.min(3) == 2 // find the minimum value
        3.signum == 1 // singum
      """)
    ),

    slide(
      "Primitives: division",
      code("""
        // when done with Integers
        1 / 2 == (1: Int) / (2: Int)
              == (1 / 2): Int
              == 0
      """),
      codeFragment("""
        // at least one element is a Double
        1 / 2.0 == (1: Int) / (2.0: Double)
                == (1.0 / 2.0): Double
                == 0.5
      """)
    ),

    slide(
      "Primitives: number typing",
      <.p("Scala selects the number type with the highest precision.")
    ),

    slide(
      "Primitives: division",
      code("""
        // we already know that this will fail
        1 / 0 == ArithmeticException
      """),
      codeFragment("""
        // but what happens here?
        1 / 0.0
      """),
      codeFragment("""
        1 / 0.0 = Infinity
      """)
    ),

    slide(
      "Primitives: infinity",
      <.p("You cannot access an Infinity object, but you can check if a value is infinite.")
    ),

    slide(
      "Primitives: infinity",
      code("""
        val infinity = 1 / 0.0

        infinity.isInfinity    == true
        infinity.isPosInfinity == true
        infinity.isNegInfinity == false
      """)
    ),

    slide(
      "Primitives: comparison operators",
      <.p("All Boolean comparison opeartors are available for numbers.")
    ),

    slide(
      "Primitives: floating point comparison",
      code("""
        val a: Double = ???
        val b: Double = ???

        // is dangerous - at which precision do you define them as equal?
        a == b
      """)
    ),

    slide(
      "Primitives: floating point comparison",
      code("""
        def eq(a: Double: b: Double, epsilon: Double): Boolean =
          (a - b).abs < epsilon

        eq(a, b, 0.00000001)
      """)
    ),

    slide(
      "Primitives: Integer operators",
      code("""
        // some Integer specific operators
        2 << 1 == 4 // bit-shift to the left
        2 >> 1 == 1 // bit-shift to the right
        2 ^ 3  == 1 // bit-wise XOR
        2 & 3  == 2 // bit-wise AND
        2 | 3  == 3 // bit-wise OR
      """)
    ),

    slide(
      "Primitives: more information",
      <.h4("Use the search bar to find your type"),
      <.a(
        ^.href := "https://www.scala-lang.org/api/2.12.0/",
        "https://www.scala-lang.org/api/2.12.0/"
      )
    ),

    noHeaderSlide(
      <.h3("Char")
    ),

    slide(
      "Primitives: Char",
      code("""
        // all Integer operators apply
        Char - signs encoded with Integers from 0 to 65,535
      """)
    )
  )

  val collections = chapter(
    chapterSlide(
      <.h2("Collections")
    ),

    slide(
      "Collections",
      <.p("Let's start with the most used collection trait.")
    ),

    noHeaderSlide(
      <.h3("Sequence")
    ),

    slide(
      "Sequence: definition",
      <.p("A sequence is a collection of values with an order."),
      <.br,
      code("""
        Seq[Char]()     // empty
        Seq.empty[Char] // empty
        Seq(1, 2, 3)    // sequence of numbers 1, 2 and 3
      """)
    ),

    slide(
      "Sequence: length",
      code("""
        Seq[Char]().length  == 0
        Seq(1, 2, 3).length == 3
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
          primitives,
          collections
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
