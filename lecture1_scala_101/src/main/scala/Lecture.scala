
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
        Item.fadeIn(<.p("Expressions and Declarations")),
        Item.fadeIn(<.p("Types")),
        Item.fadeIn(<.p("Functions")),
        Item.fadeIn(<.p("Case Classes and Traits")),
        Item.fadeIn(<.p("Pattern Matching"))
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
      "Why Scala: Java Ecosystem",
      <.img(^.alt := "Java Ecosystem", ^.src := "./img/java-logo.svg")
    ),

    slide(
      "Why Scala: Java Ecosystem",
      Enumeration(
        Item.stable(<.p("access to Java ecosystem")),
        Item.fadeIn(<.p("or any ecosystem compiling to ByteCode")),
        Item.fadeIn(<.p("JVM performance (JIT, GC)")),
        Item.fadeIn(<.p("... and tooling (metrics, debugging, etc.)")),
      )
    ),

    slide(
      "Why Scala: brings its own Ecosysten",
      <.img(^.alt := "Scala Ecosystem", ^.src := "./img/scala-ecosystem.svg")
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
      <.h1("Expressions & Declarations"),
      <.h3("Basic building blocks")
    ),

    slide(
      "Expressions",
      <.p("Scala programs are made up from expressions and declarations.")
    ),

    slide(
      "Expressions",
      <.h4("Expression can be values, combination of values or functions applied to values. And they get reduced to results.")
    ),

    slide(
      "Values as Expressions",
      code("""
        // already in reduced form
        1
        'a'
        "Hello"
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
        ==> "Hello world!"
      """)
    ),

    slide(
      "Assignment of results",
      code("""
        val firstName = "John"
        val lastName  = "Doe"

        val name = a + b
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
        > {
          val a = 3
      
          a * 5
        }
      """),
      codeFragment("""
        > {
          3 * 5
        }
      """),
      codeFragment("""
         ==> 15
      """)      
    ),

    slide(
      "Scoping",
      code("""
        val a = 1 + 2
        {
           val b = 2 + 3
           val c = {
             val a = 3 + 4
             a + b
           }
           a + c
        }
      """),
      codeFragment("""
        > val a = 3
        {
           val b = 5
           val c = {
             val a = 7
             7 + 5
           }
           3 + (7 + 5)
        }
      """),
      codeFragment("""
        ==> 15
      """)
    ),

    exerciseSlide(
      "Does this work?",
      code("""
        val a = 5
        {
          val b = 3
          a + b
        }
      """),
      codeFragment("""
        ==> 8
      """)
    ),

    exerciseSlide(
      "Does this work?",
      code("""
        val a = {
          val b = 6
          10
        }
        a + b
      """),
      codeFragment("""
        // no, `b` doesn't exist in the outer scope
      """)
    ),

    exerciseSlide(
      "Does this work?",
      code("""
        val a = 5
        {
          val a = {
            a * 10
          }
          a
        }
      """),
      codeFragment("""
        // no, you try to define `a` by using itself
      """)
    ),

    slide(
      "Precedence",
      code("""
        0 - (all letters)
        1 - |
        2 - ^
        3 - &
        4 - = !
        5 - < >
        6 - :
        7 - + -
        8 - * / %
        // (all other special characters)
      """)
    ),

    exerciseSlide(
      "What's the result?",
      code("""
        2 * 3 + 10
      """),
      codeFragment("""
        // prec('*') > prec('+')
        > (2 * 3) + 10
      """),
      codeFragment("""
        > 6 + 10
      """),
      codeFragment("""
        ==> 16
      """)
    ),

    exerciseSlide(
      "What's the result?",
      code("""
        2.0 * 3.0 / 3.0
      """),
      codeFragment("""
        // '*' left most operation
        > (2.0 * 3.0) / 3.0
      """),
      codeFragment("""
        > 6.0 / 3.0
      """),
      codeFragment("""
        ==> 2.0
      """)
    ),

    exerciseSlide(
      "What's the result?",
      code("""
        2 * 5 == 10 && 24 / 6 < 4 | ! false
      """),
      codeFragment("""
        > (((2 * 5) == 10) && ((24 / 6) < 4)) | (! false)
      """),
      codeFragment("""
        // use brackets
        ==> true
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
    ),

    slide(
      "Definition",
      <.h3("Types put constraints on term.")
    ),

    slide(
      "State of a Value",
      code("""
        a: Int = 0

        // `a` is of type `Int`
        // is an Integer number
        // -2147483648 <= a <= 2147483647

        b: String = a // forbidden 
      """)
    ),

    slide(
      "Primitive Types",
      code("""
        Boolean - true false
        Byte    - Integer from -128 to 127
        Short   - Integer from -32,768 to 32,767
        Int     - Integer from -2,147,483,648 to 2,147,483,647
        Long    - Integer from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
        Float   - +- 1.40129846432481707e-45 to 3.40282346638528860e+38
        Double  - +- 4.94065645841246544e-324 to 1.79769313486231570e+308
        Char    - signs encoded with Integers from 0 to 65,535
        String  - sequence of Chars
      """),
      <.br,
      codeFragment("""
        // And many more complex types. Some of them we will learn
        // in the course of this workshop.
      """)
    ),

    slide(
      "Operation restriction",
      code("""
          1 + 2
        """),
      codeFragment("""
        // numbers, Char and String a
        1 + 2.0       ==> 3.0: Double
        1 + 'a'       ==> 98: Int
        1 + " banana" ==> "1 banana": String
      """),
      codeFragment("""
        1 + true // forbidden
      """)
    ),

    exerciseSlide(
      "What is the Type?",
      code("""
        1 + 2
      """),
      codeFragment("""
        ==> 3: Int
      """),
      code("""
        1.0 + 2
      """),
      codeFragment("""
        ==> 3.0: Double
      """),
      code("""
        "Hello " + true
      """),
      codeFragment("""
        ==> "Hello true": String
      """)
    ),

    exerciseSlide(
      "What is the Type?",
      code("""
        1 / 2
      """),
      codeFragment("""
        ==> 0: Int
      """),
      code("""
        1 / 2.0
      """),
      codeFragment("""
        ==> 0.5: Double
      """),
      code("""
        1 / 0.0
      """),
      codeFragment("""
        // this throws an error (Exception)
      """)
    ),
    
    slide(
      "Types are important",
      Enumeration(
        Item.stable(<.p("give guarantees about state of values / application of operators")),
        Item.fadeIn(<.p("proofen during compile time")),
        Item.fadeIn(<.p("no extra tests necessary"))
      )
    ),

    noHeaderSlide(
      <.h4("We now know what Expressions are and how to use them."),
      <.br,
      <.h5("But how do we make them reusable?")
    )
  )

  val functions = chapter(
    chapterSlide(
      <.h1("Functions")
    ),

    slide(
      "Functions",
      <.h4("Special type of expressions which has a name, takes input and returns, as always, a result.")
    ),

    slide(
      "Functions",
      <.h4("They are expression with a declaration.")
    ),

    slide(
      "Functions",
      code("""
        val plus: Int => Int => Int = a => b => a + b
      """),
      codeFragment("""
        // declaration
        val plus: Int => Int => Int
        //   ^     ^      ^      ^
        //   |     '------|      |
        // identifier   input  result 
      """),
      codeFragment("""
        // body expression
           a => b => a + b
        // ^    ^
        // '----'
        //   |
        // input values
      """)
    ),

    slide(
      "Functions: def syntax",
      code("""
        // or a more prominent syntax
        // is mainly used when declaring or defining functions

        // this becomes
        val plus: Int => Int => Int = a => b => a + b

        // that; a bit more concise
        def plus(a: Int)(b: Int): Int = a + b
      """)
    ),

    slide(
      "Functions",
      Enumeration(
        Item.stable(<.p("have a unique identifier (name)")),
        Item.fadeIn(<.p("declare a number of parameters as input (arity)")),
        Item.fadeIn(<.p("and a single result type")),
        Item.fadeIn(<.p("body expression fulfills the declaration"))
      )
    ),

    slide(
      "Function Application",
      code("""
        val plus1: Int => Int = plus(1)
      """),
      codeFragment("""
        // 1 assigne to `a` and every appearance of `a` was replaced with 1
        > val plus1: Int => Int = b => 1 + b
      """),
      codeFragment("""
        val result = plus1(2)

        > val result: Int = 1 + 2
      """),
      codeFragment("""
        // in one step
        val result = plus(1)(2)
      """)
    ),

    slide(
      "Function Application",
      Enumeration(
        Item.stable(<.p("this technique is called Currying")),
        Item.fadeIn(<.p("apply a single parameter at a time (left to right)")),
        Item.fadeIn(<.p("every application returns a new function of arity $n - 1$"))
      )
    ),

    slide(
      "Uncurried Function",
      code("""
        // you can also write
        def plusUn: (a: Int, b: Int): Int = a + b

        // or when using `val` syntax 
        val plusUn: (Int, Int) => Int = (a, b) => a + b
 
        // more concise - underscore syntax
        // first `_` first paremeter, ...
        val plusUn: (Int, Int) => Int = _ + _
      """)
    ),

    slide(
      "Mix Curried and Uncurried",
      code("""
        def plusPair(a: Int, b: Int)(c: Int, d: Int): Int = {
          plus(a, c) + plus(b, d)
        }
      """)
    ),

    slide(
      "Higher-Order Functions",
      code("""
        // functions are first-class citizens > can be used like values
        def plus(a: Int, b: Int): Int = ???

        // therefore, we can treat it pass as parameter
        def resultMsg(f: (Int, Int) => Int)(a: Int, b: Int): String = {
          "The result for " + a + " and " + b + " is " op(a, b)
        }

        resultMsg(plus)(1, 2) // The result for 1 and 2 is 3
      """),
      codeFragment("""
        def multiply(a: Int, b: Int): Int = ???

        resultMsg(multiply)(1, 2) // The result for 1 and 2 is 2
      """)
    ),

    exerciseSlide(
      "Let's Code",
      bashCode("""
        # start sbt (keep it running)
        $> sbt
        # switch into exercise project
        sbt> project scala101-exercises
        # to compile your code
        sbt> compile
        # or let SBT compile on every file change
        sbt> ~compile
        # to test your implementation
        sbt> test:testOnly exercise1.AreaSpec
      """)
    ),

    exerciseSlide(
      "Let's Code",
      code("""
        // package/path definition
        // <package>.<object-name> must be unique
        package exercise1

        // object containing functions, values, other objects and more
        // like a namespace
        object Area {
          // our functions live here
          
          ...
        }
      """)
    ),

    exerciseSlide(
      "Let's Code",
      bashCode("""
        sbt> project scala101-exercises
        sbt> test:testOnly exercise1.HigherOrderSpec
      """)
    ),

    noHeaderSlide(
      <.h3("Enough about functions for now"),
      <.br,
      <.h4("Let's do something new. Let's create some Data Types")
    )
  )

  val traitsAndCaseClasses = chapter(
    chapterSlide(
      <.h1("Traits and Case Classes")
    ),

    slide(
      "Case Classes",
      <.h3("Scala's data types bring us just so far."),
      <.h3("We want to be able to create our own.")
    ),

    slide(
      "Case Classes",
      code("""
        case class User(name: String, age: Int)
        //          ^    ^             ^
        //          |    '-------------'
        //       identifier     |
        //                      |
        //                 public fields

        val user = User("John", 42)

        // read only, no mutations
        user.name
        user.age
      """)
    ),

    slide(
      "Case Classes: How to mutate them?",
      code("""
        // comes with a copy method
        val user = User("John", 42)

        // creates copy of `user`
        val newUser = user.copy("Jim", 27)
      """)
    ),

    slide(
      "Case Classes: How to mutate them?",
      code("""
        val user = User("John", 42)

        // change just a supset of fields
        val newUser = user.copy(name = "Jim")
      """)
    ),

    slide(
      "Functions: default parameter",
      code("""
        def newUser(name: String = "Joe", age: Int = 42): User = User(name, age)

        newUser()          // User(Joe, 42)
        newUser("Jim", 27) // User(Jim, 27)
        newUser("Jim")     // User(Jim, 42)
        newUser(age = 27)  // User(Joe, 27)
      """)
    ),

    slide(
      "Methods: functions for classes",
      code("""
        case class User(name: String, age: Int) {
          
          def birthYear(year: Int): Int = year - age
      """),
      codeFragment("""
          // which is equal to
          def birthYear(year: Int): Int = year - this.age
        }
      """),
      codeFragment("""
        // as function
        def birthYear(user: User)(year: Int): Int = year - user.age
      """)
    ),

    slide(
      "Methods: functions for classes",
      code("""
        // methods give us infix notation like `+`
        case class Euro(value: Int) {

          def +(other: Euro): Euro = Euro(value + other.value)
        }

        Euro(10).+(Euro(5)) // Euro(15)
      """),
      codeFragment("""
        // or without dots and brackets
        Euro(10) + Euro(5)
      """)
    ),

    slide(
      "Static Case Classes",
      <.h4("What if you want to represent colors?"),
      <.br,
      code("""
        // this is static; will not change
        // why create a new instance every time?
        case class Green()
      """),
      codeFragment("""
        // use case object s instead
        case object Green
      """)
    ),

    slide(
      "Traits",
      <.h4("But what if we have multiple data types which share a relation?")
    ),

    slide(
      "Traits",
      code("""
        // all persons but different types
        case class Man(firstName: String, lastName: String)
        case class Woman(firstName: String, lastName: String)
        case class Child(firstName: String, lastName: String)
      """)
    ),

    slide(
      "Traits",
      code("""
          sealed trait Person
        //  ^            ^
        //  '            '-------------------
        // only allow sub classes           '
        // in this file (optional)       identifier

        case class Man(firstName: String, lastName: String)   extends Person
        case class Woman(firstName: String, lastName: String) extends Person
        case class Child(firstName: String, lastName: String) extends Person
      """)
    ),

    slide(
      "Traits: common properties and behaviour",
      code("""
        // all subclasses share this properties
        sealed trait Person {

          val firstName: String
          val lastName: String

          // default implementation
          def fullName: String = firstName + " " + lastName
        }
      """),
      codeFragment("""
        case class Man(firstName: String, lastName: String) extends Person

        val man = Man("John", "Doe")
        man.fullName
      """)
    ),

    slide(
      "Traits: OOP in FP",
      <.h4("Looks like OOP inheritence to you?"),
      <.br,
      <.h4(
        ^.cls := "fragment fade-in",
        "That's because it is. Again Scala is the fusion of OOP and FP."
      )
    ),

    exerciseSlide(
      "Let's Code",
      bashCode("""
        sbt> project scala-101-exercises
        sbt> test:testOnly exercise1.PersonSpec
      """)
    ),

    noHeaderSlide(
      <.h3("So far, We learned stuff about ..."),
      <.br,
      Enumeration(
        Item.fadeIn(<.p("epxressions and declarations")),
        Item.fadeIn(<.p("functions and types")),
        Item.fadeIn(<.p("case class and trait")),
      ),
      <.br,
      <.h4(
        ^.cls := "fragment fade-in",
        "We are ready for the FP part, right?"
      )
    )
  )

  val patternMatching = chapter(
    chapterSlide(
      <.h1("Pattern Matching")
    ),

    slide(
      "Pattern Matching",
      Enumeration(
        Item.stable(<.p("match values against pattern")),
        Item.fadeIn(<.p("extract information")),
        Item.fadeIn(<.p("decide program control flow"))
      )
    ),

    slide(
      "Pattern Matching",
      code("""
        val person: Person = ???

        person match {
          case Woman(first, last) => first
          case Man(first, last)   => first
          case Child(first, last) => first
        }
      """)
    ),

    slide(
      "Ommit values you don't need",
      code("""
        val person: Person = ???

        person match {
          case Woman(first, _) => first
          ...
        }
      """)
    ),

    slide(
      "Conditional cases",
      code("""
        val person: Person = ???

        person match {
          // you can also use `&` and `|`
          case Woman(first, last) if last == "Smith" => first
          ...
        }
      """)
    ),

    slide(
      "Get whole value",
      code("""
        val person: Person = ???

        person match {
          // you can also use `&` and `|`
          case w@Woman(_, last) if last == "Smith" => w
          ...
        }
      """)
    ),

    slide(
      "Pattern Matching",
      Enumeration(
        Item.stable(<.p("Make it exhaustive. Don't miss a case.")),
        Item.fadeIn(<.p("Only declare fields you use.")),
        Item.fadeIn(<.p("Works also with primitives."))
      )
    ),

    exerciseSlide(
      "Let's Code",
      bashCode("""
        sbt> project scala101-exercises
        sbt> test:testOnly exercise1.PatternMatchingSpec
      """)
    )
  )

  val summary = chapter(
    chapterSlide(
      <.h1("Summary")
    ),

    slide(
      "Expressions",
      code("""
        {
          val a = 1 + 2
      
          a * 5
        }

        ==> 15
      """)
    ),

    slide(
      "Types",
      code("""
        val a: String = "Hello"

        val b: Int = a // no no
      """)
    ),

    slide(
      "Functions",
      code("""
        def plus(a: Int, b: Int): Int = a + b

        plus(1, 2)
      """)
    ),

    slide(
      "Case Classes and Traits",
      code("""
        sealed trait Currency

        case class Euro(value: Int) extends Currency
        case class Dollar(value: Int) extends Currency
      """)
    ),

    slide(
      "Pattern Matching",
      code("""
        val curr: Currency = ???

        curr match {
          case Euro(value)   => value
          case Dollar(value) => value
        }
      """)
    ),

    noHeaderSlide(
      <.h2("Next Topic"),
      <.br,
      <.h3("Next Topic: Functional Programming 101")
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
          types,
          functions,
          traitsAndCaseClasses,
          patternMatching,
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
