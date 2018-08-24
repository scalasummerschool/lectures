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
      <.h1("Function Programming 101")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable(<.p("Immutability")),
        Item.fadeIn(<.p("Pure Functions")),
        Item.fadeIn(<.p("Recursion")),
        Item.fadeIn(<.p("Composability")),
        Item.fadeIn(<.p("Referential Transparency"))
      )
    ),

    noHeaderSlide(
      <.h2("You have a question?"),
      <.h3("Ask it right away!")
    )
  )

  val immutability = chapter(
    chapterSlide(
      <.h2("Immutability")
    ),

    slide(
      "Data never change",
      <.h4("We already know it!"),
      <.br,
      code("""
        // you cannot reassign to `a`
        val a = 5



        // you cannot mutate fields in a clase class instance
        case class User(name: String, age: Int)

        val user = User("Joe", 42)
      """)
    ),

    slide(
      "Data never change",
      <.h4("Once created your data stays the same until destruction.")
    ),

    slide(
      "What's the benefit?",
      Enumeration(
        Item.stable(<.p("state of your values known at all times")),
        Item.fadeIn(<.p("no \"bad effects\" in a concurrent scenario ")),
        Item.fadeIn(<.p("simplifies reasoning about values in your code"))
      )
    )
  )

  val pureFunctions = chapter(
    chapterSlide(
      <.h2("Pure Functions")
    ),

    slide(
      "Pure Functions",
      <.p(
        "A function which, for any given input, returns an output and returns the same output for the same input at any time. " +
        "Furthermore, it doesn't effect its context."
      )
    ),

    slide(
      "Pure Functions",
      code("""
        def plus(a: Int, b: Int): Int = a + b

        // works for every input and always returns the same output
        plus(1, 2) == 3
      """)
    ),

    slide(
      "Pure Functions: replace with value",
      code("""
        val plus = Map(
          ...,
          1 -> Map(
            ...
            2 -> 3
            ...
          )
          ...
        )

        plus(1)(2) == 3
      """)
    ),

    slide(
      "Impure Functions: exceptions",
      code("""
        def divide(a: Double, b: Double): Double = a / b

        // throws an Exceptions which bypasses your call stack
        divide(1.0, 0.0) == ???
      """)
    ),

    slide(
      "Impure Functions: partial functions",
      code("""
        // partial function
        def question(q: String): Int = q match {
          case "answer to everything" => 42
        }

        // throws an Exceptions which bypasses your call stack
        question("is the sun shining") == ???
      """)
    ),

    slide(
      "Impure Functions: changing results",
      code("""
        def question(q: String): Int = Random.nextInt()

        val a1 = question("what is the answer to everything")
        val a2 = question("what is the answer to everything")

        // probably
        a != a2
      """)
    ),

    slide(
      "Impure Functions: Effecting the context",
      code("""
        def log(msg: String): Unit = println(msg)

        // write to the console -> changes its context
        log("hello world")
      """)
    ),

    slide(
      "Impure Functions: Effecting the context",
      <.p("Application Input/Output (IO) is not allowed. How to write useful programs then?"),
      <.br,
      <.p("We will discuss that later!")
    ),

    exerciseSlide(
      "Is this function pure?",
      code("""
        def multiply(a: Int, b: Int): Int = a * b
      """),
      codeFragment("""
        // yes
      """),
      <.br,
      code("""
        def concat(a: String, b: String): String = {
          println(a)
          a + b
        }
      """),
      codeFragment("""
        // no -> we mutate the OS by writing to System Out
      """)
    ),

    exerciseSlide(
      "Is this function pure?",
      code("""
        def whatNumber(a: Int): String = a > 100 match {
          case true => "large one"
        }
      """),
      codeFragment("""
        // no -> partial function, only handles the `true` case
      """),
      <.br,
      code("""
        def increase(a: Int): Int = {
          // current time in milliseconds
          val current = System.currentTimeMillis
          
          a + current
        }
      """),
      codeFragment("""
        // no -> result depends on the time we call the function
      """)
    )
  )

  val recursion = chapter(
    chapterSlide(
      <.h2("Recursion")
    ),

    slide(
      "Recursion",
      <.p("Solving a problem where the solution depends on solutions to smaller instances of the same problem.")
    ),

    slide(
      "Recursion: data types",
      <.p("Definition of the data types depends on itself.")
    ),

    slide(
      "Recursion: data types",
      code("""
        // linked list of Ints
        sealed trait IntList

        // a single list element with its Int value and the remaining list
        case class Cons(head: Int, tail: IntList) extends IntList
        // end of the list
        case object Nil extends IntList
      """),
      codeFragment("""
        val list = Cons(0, Cons(1, Cons(2, Nil)))
      """)
    ),

    slide(
      "Recursion: data types",
      <.img(
        ^.alt := "Linked List",
        ^.src := "./img/list.svg"
      )
    ),

    noHeaderSlide(
      <.h3("We have to write list types for every data structure?"),
      <.br,
      <.h4(
        ^.cls := "fragment fade-in",
        "Fortunately no, we can use type parameters!"
      )
    ),

    slide(
      "Type Parameter in data structures",
      code("""
        sealed trait List[A]
        //                ^
        //                '
        //           type parameter
      """),
      codeFragment("""
        case class Cons[A](head: A, tail: List[A]) extends List[A]
        //              ^        ^             ^                ^
        //              '        '----         '----------------|
        //         type parameter    '                          |
        //                          fixes type of our value     |
        //                                                      '
        //                                    fixes type of remaing/whole list 

        // small trick relying on OOP and `Nothing` as the bottom type
        case object Nil extends List[Nothing]
      """)
    ),

    slide(
      "Type Parameter in data structures",
      code("""
        val intList  = Cons[Int](0, Cons(1, Cons(2, Nil)))
        val charList = Cons[Char]('a', Cons('b', Nil))
      """),
      codeFragment("""
        // Scala can infer `A` by looking at the values
        val intList  = Cons(0, Cons(1, Cons(2, Nil)))
        val charList = Cons('a', Cons('b', Nil))
      """)
    ),

    slide(
      "Type Parameter in data structures",
      Enumeration(
        Item.stable(<.p("also called generics")),
        Item.fadeIn(<.p("think of it like as missing (type) information")),
        Item.fadeIn(<.p("can be fixed by hand ", <.code("Cons[Int](0, Nil): List[Int]"))),
        Item.fadeIn(<.p("or inferred by Scala ", <.code("Cons(0, Nil): List[Int]")))
      )
    ),

    exerciseSlide(
      "Let's Code",
      bashCode("""
        sbt> project fp101-exercises
        sbt> test:testOnly RecursiveDataSpec
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
          immutability,
          pureFunctions,
          recursion
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
