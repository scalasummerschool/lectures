
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
        Item.fadeIn(<.p("Expressions & Declarations & Objects")),
        Item.fadeIn(<.p("Types")),
        Item.fadeIn(<.p("Functions")),
        Item.fadeIn(<.p("Classes")),
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
      <.h2("What is Scala? And why?")
    ),

    slide(
      "The Name",
      <.h3(
        font(^.color := "#ce1d25", "Sca"), "lable ", 
        font(^.color := "#ce1d25", "La"), "nguage"
      ),
      <.br,
      <.h4(
        ^.cls := "fragment fade-in",
        font(^.color := "#ce1d25", "S"), "low ", 
        font(^.color := "#ce1d25", "C"), "ompiling ", 
        font(^.color := "#ce1d25", "A"), "cademic ", 
        font(^.color := "#ce1d25", "La"), "nguage"
      )
    ),

    slide(
      "Creation: Martin Odersky et al around 2004",
      <.img(
        ^.alt := "Scala Paper",
        ^.src := "./img/scala-paper.png"
      )
    ),

    slide(
      "Scala's Principles",
      <.img(
        ^.alt   := "Scala's Prinicples",
        ^.width := "50%",
        ^.src   := "./img/principles.svg"
      )
    ),

    slide(
      "Target-Systems",
      <.img(
        ^.alt   := "Scala's Targets",
        ^.width := "50%",
        ^.src   := "./img/targets.svg"
      )
    ),

    slide(
      "Why Scala: Java ecosystem",
      <.img(^.alt := "Java Ecosystem", ^.src := "./img/java-logo.svg")
    ),

    slide(
      "Why Scala: Java ecosystem",
      Enumeration(
        Item.stable(<.p("access to Java ecosystem")),
        Item.fadeIn(<.p("or any ecosystem compiling to ByteCode")),
        Item.fadeIn(<.p("JVM performance (JIT, GC)")),
        Item.fadeIn(<.p("... and tooling (metrics, debugging, etc.)")),
      )
    ),

    slide(
      "Why Scala: brings its own ecosysten",
      <.img(^.alt := "Scala Ecosystem", ^.src := "./img/scala-ecosystem.svg")
    ),

    slide(
      "Why Scala: fusion of FP and OOP",
      Enumeration(
        Item.stable(<.p("with additional Type-Level programming on top")),
        Item.fadeIn(<.p("choose paradigm which solve your problem best ...")),
        Item.fadeIn(<.p("... stay in the same language"))
      )
    ),

    slide(
      "Why Scala: industry & academia combined",
      Enumeration(
        Item.stable(<.p("big players use it: Twitter, Stripe to name a few")),
        Item.fadeIn(<.p("backed by Lightbend Inc.")),
        Item.fadeIn(<.p("backed by EPFL")),
        Item.fadeIn(<.p("... and of course by an Open Source community"))
      )
    ),

    noHeaderSlide(
      <.h3("Are you convinced?"),
      <.br,
      <.h4(
        ^.cls := "fragment fade-in",
        "Great, then let's move on and learn how to use it."
      )
    )
  )

  val expressions = chapter(
    chapterSlide(
      <.h2("Expressions & Declarations & Objects"),
      <.h3("Scalas' basic building blocks")
    ),

    slide(
      "Objects",
      <.p("An object is a collection of values and a set of operations on that data."),
      code("""
        // example: literal object
        1
        // value: integer 1
        // operations: +, -, *, /, etc.
      """),
      codeFragment("""
        // example: literal object
        "Hello"
        // value: string Hello
        // operations: +, split, take, etc.
      """),
      codeFragment("""
        // example: tuple object
        (1, "Hello")
        // values: integer 1 and string Hello
        // operations: _1, _2, swap, etc.
      """)
    ),

    slide(
      "Expressions",
      <.p("Expression can be objects, combination of objects or functions applied to objects. And they get evaluated/reduced to results.")
    ),

    slide(
      "Objects as expressions",
      code("""
        // already in reduced form
        1

        'a'

        "Hello"
      """)
    ),

    slide(
      "Operations as expressions",
      code("""
        1 + 2
      """),
      codeFragment("""
        == 3
      """),
      <.br,
      code("""
        val w = "world"

        "Hello " + a + "!"
      """),
      codeFragment("""
        == "Hello world!"
      """)
    ),

    slide(
      "Assignment of results",
      <.p("Often you use the result of an expression several times. But you don't want to compute over and over again."),
      <.p("Therefore, you assign them a name."),
      <.br,
      code("""
        // we have to repeat (1 + 2) everytime
        3 * (1 + 2)

        5 - (1 + 2)
      """)
    ),

    slide(
      "Assignment of results",
      code("""
          val result = 1 + 2
        // ^    ^    ^
        // |    |    '
        // |    |  assigns result to identifier
        // |    '
        // |   identifier
        // '
        // starts assignment

        3 * result

        5 - result
      """)
    ),

    slide(
      "Assignment of results",
      <.p("You cannot reassign a new object to a value. Once set, it keeps its reference forever.")
    ),

    slide(
      "Block expressions",
      <.p("When you want to combine multiple expressions you put them in blocks."),
      <.br,
      code("""
        // start with '{' and end with '}'
        {
          val a = 1 + 2
      
          a * 5
        }
      """)
    ),

    slide(
      "Block expressions",
      code("""
        {
          val a = 1 + 2
      
          a * 5
        }
      """),
      codeFragment("""
        {
           val a = 3      <==
      
           a * 5
        }
      """),
      codeFragment("""
        {
           val a = 3
      
           3 * 5          <==
        }
      """),
      codeFragment("""
         == 8
      """)      
    ),

    slide(
      "Scoping: values in blocks",
      <.p("Access objects in the same or an outer block."),
      <.br,
      code("""
        val a = 1 + 2
 
        // `a` in scope
        {
           val b = 3 + 4

           a + b
        }
      """)
    ),

    slide(
      "Scoping: values in blocks",
      code("""
        val a = 1 + 2
 
        {
           val b = 3 + 4

           a + b
        }
      """),
      codeFragment("""
        val a = 3       <==
 
        {
           val b = 7    <==

           a + b
        }
      """),
      codeFragment("""
        val a = 3
 
        {
           val b = 7

           3 + 7        <==
        }
      """),
      codeFragment("""
        == 10
      """)
    ),

    exerciseSlide(
      "Does it work?",
      code("""
        val a = 5
        {
          val b = 3
          a + b
        }
      """),
      codeFragment("""
        == 8
      """)
    ),

    exerciseSlide(
      "Does it work?",
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
      "Does it work?",
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

    exerciseSlide(
      "Does it work?",
      code("""
        val a = 5
        {
          val a = 10
          a
        }
      """),
      codeFragment("""
        == 10
      """)
    ),

    slide(
      "Conditional expression",
      code("""
        val a = 5

        if (a > 0) {
          "positive number"
        }
      """),
      codeFragment("""
        else if (a == 0) {
          "is zero"
        }
      """),
      codeFragment("""
        else {
          "negative number"
        }
      """)
    ),

    slide(
      "Boolean operators",
      code("""
        !a     // negation

        a & b  // AND, always evaluates `b`
        a && b // AND, evaluates `b` only if `a` is true
        
        a | b  // OR, always evaluates b
        a || b // OR, evaluates `b` only if `a` is true
        
        a > b  // greater than
        a < b  // smaller than
        a == b // equal
        a != b // equal

        // some more, but not relevant yet
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
        == (2 * 3) + 10
      """),
      codeFragment("""
        == 6 + 10
      """),
      codeFragment("""
        == 16
      """)
    ),

    exerciseSlide(
      "What's the result?",
      code("""
        2.0 * 3.0 / 3.0
      """),
      codeFragment("""
        // '*' left most operation
        == (2.0 * 3.0) / 3.0
      """),
      codeFragment("""
        == 6.0 / 3.0
      """),
      codeFragment("""
        == 2.0
      """)
    ),

    exerciseSlide(
      "What's the result?",
      code("""
        2 * 5 == 10 && 24 / 6 < 4 | ! false
      """),
      codeFragment("""
        == (((2 * 5) == 10) && ((24 / 6) < 4)) | (! false)
      """),
      codeFragment("""
        // use brackets
        == true
      """)
    ),

    noHeaderSlide(
      <.h3("Expressions return typed results"),
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
      <.h2("Types"),
      <.h3("Make your code more strict")
    ),

    slide(
      "Definition",
      <.h3("Types put constraints on term.")
    ),

    slide(
      "State of a value",
      code("""
        a: Int = 0

        // `a` is of type `Int`
        // is an Integer number
        // -2147483648 <= a <= 2147483647

        b: String = a // forbidden 
      """)
    ),

    slide(
      "Primitive Data Types",
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
        // numbers, Chars and Strings
        1 + 2.0       == 3.0: Double
        1 + 'a'       == 98: Int
        1 + " banana" == "1 banana": String
      """),
      codeFragment("""
        1 + true // forbidden
      """)
    ),

    exerciseSlide(
      "What is the type?",
      code("""
        1 + 2
      """),
      codeFragment("""
        == 3: Int
      """),
      <.br,
      code("""
        1.0 + 2
      """),
      codeFragment("""
        == 3.0: Double
      """),
      <.br,
      code("""
        "Hello " + true
      """),
      codeFragment("""
        == "Hello true": String
      """)
    ),

    exerciseSlide(
      "What is the type?",
      code("""
        1 / 2
      """),
      codeFragment("""
        == 0: Int
      """),
      <.br,
      code("""
        1 / 2.0
      """),
      codeFragment("""
        == 0.5: Double
      """),
      <.br,
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
      <.h2("Functions")
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
        def plus(a: Int)(b: Int): Int = a + b
      """),
      codeFragment("""
        // declaration
        def plus(a: Int)(b: Int): Int
        //   ^     ^       ^       ^
        //   |     '-------|       |
        // identifier    input   result 
      """),
      codeFragment("""
        // body expression
           a + b
        // ^   ^
        // '---'
        //   |
        // input values
      """)
    ),

    slide(
      "Functions: val syntax",
      code("""
        // less used syntax

        // this becomes
        def plus(a: Int)(b: Int): Int = a + b

        // that
        val plus: Int => Int => Int = a => b => a + b
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
      "Function application",
      code("""
        val plus1: Int => Int = plus(1)
      """),
      codeFragment("""
        // `1` assigned to `a` and every appearance of `a` was replaced with `1`
        == val plus1: Int => Int = b => 1 + b
      """),
      codeFragment("""
        val result = plus1(2)

        == val result: Int = 1 + 2
      """),
      codeFragment("""
        // in one step
        plus(1)(2) == 3
      """)
    ),

    slide(
      "Function application",
      Enumeration(
        Item.stable(<.p("this technique is called Currying")),
        Item.fadeIn(<.p("apply a single parameter at a time (left to right)")),
        Item.fadeIn(<.p("every application returns a new function of arity $n - 1$"))
      )
    ),

    slide(
      "Uncurried function",
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
      "Mix curried and uncurried",
      code("""
        def plusPair(a: Int, b: Int)(c: Int, d: Int): Int = {
          plus(a, c) + plus(b, d)
        }
      """)
    ),

    slide(
      "Higher-Order functions",
      code("""
        // functions are first-class citizens > can be used like values
        def plus(a: Int, b: Int): Int = ???

        // therefore, we can pass them as parameter
        def resultMsg(f: (Int, Int) => Int)(a: Int, b: Int): String = {
          "The result for " + a + " and " + b + " is " op(a, b)
        }

        resultMsg(plus)(1, 2) == "The result for 1 and 2 is 3"
      """),
      <.br,
      codeFragment("""
        def multiply(a: Int, b: Int): Int = ???

        resultMsg(multiply)(1, 2) == "The result for 1 and 2 is 2"
      """)
    ),

    exerciseSlide(
      "Let's code",
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
        sbt> test:testOnly exercise1.FunctionsSpec
      """)
    ),

    exerciseSlide(
      "Let's code",
      code("""
        // package/path definition: <package>.<object-name> must be unique
        package exercise1

        object Functions {
          // your functions go here
          
          ...
        }
      """)
    ),

    exerciseSlide(
      "Let's code",
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

  val classes = chapter(
    chapterSlide(
      <.h2("Classes")
    ),

    slide(
      "Create a new type",
      <.h3("Scala's objects bring us just so far."),
      <.h4("We want to be able to create our own.")
    ),

    slide(
      "Create a new type",
      <.p("You create new types in Scala by defining Classes. Here, a class is a way to define a set of objects which have same fields and functions."),
      <.br,
      Enumeration(
        Item.fadeIn(<.p("class")),
        Item.fadeIn(<.p("case class")),
        Item.fadeIn(<.p("object")),
        Item.fadeIn(<.p("abstract Class")),
        Item.fadeIn(<.p("trait"))
      )
    ),

    slide(
      "Classes",
      code("""
        class User(val name: String, val age: Int)
        //     ^        ^                 ^
        //     '        '-----------------'
        // identifier            '
        //              constructor with public fields
      """),
      codeFragment("""
        val user = new User("Joe", 27)
        //          ^
        //          '
        //   call constructor / create new object

        user.name == "Joe"
        user.age == 27
      """)
    ),

    slide(
      "Classes: private fields",
      code("""
        class User(val name: String, age: Int)
        //                            ^
        //                            '
        //                       private field
      """),
      codeFragment("""
        val user = new User("Joe", 27)

        user.name == "Joe"
        user.age // not allowed
      """)
    ),

    slide(
      "Classes: inner fields",
      code("""
        class User(val name: String, val age: Int) {
          private val ageString = age.toString

          val show = "user: " + name + ", " + ageStr
        }

        new User("Joe", 27).show == "user: Joe, 27"
      """)
    ),

    slide(
      "Classes: methods",
      code("""
        class User(val name: String, val age: Int) {

          // functions refering to there object are called methods          
          def isOlder(other: User): Boolean = age > other.age
      """),
      codeFragment("""
        // equal to
          def isOlder(other: User): Boolean = this.age > other.age
        }
      """),
      codeFragment("""
        // or
        def isOlder(user: User)(other: User): Boolean = user.age > other.age
      """),
      codeFragment("""
        val user = new User("Joe", 27)

        user.isOlder(new User("Jim", 21)) == isOlder(user)(new User("Jim", 21))
      """)
    ),

    slide(
      "Classes: methods",
      <.p("Methods are functions which have their surrounding object in scope and belong to the class. They also provide a means to get infix notation.")
    ),

    slide(
      "Classes: immutable fields",
      <.p("All fields are `val`. Therefore, they cannot change after assignment. To change them you have to create a new object.")
    ),

    exerciseSlide(
      "Let's code",
      bashCode("""
        sbt> project scala-101-exercises
        sbt> test:testOnly exercise1.ClassesSpec
      """)
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

        // no `new` keyword any longer
        val user = User("John", 42)
      """)
    ),

    slide(
      "Case Classes: How to mutate them?",
      code("""
        // comes with a copy method
        val user = User("John", 42)

        // creates copy of `user`
        user.copy("Jim", 27) == User("Jim", 27)
      """)
    ),

    slide(
      "Case Classes: How to mutate them?",
      code("""
        val user = User("John", 42)

        // change just a supset of fields
        user.copy(name = "Jim") == User("Jim", 42)
      """)
    ),

    slide(
      "Functions: default parameter",
      code("""
        def newUser(name: String = "Joe", age: Int = 42): User = User(name, age)

        newUser()          == User(Joe, 42)
        newUser("Jim", 27) == User(Jim, 27)
        newUser("Jim")     == User(Jim, 42)
        newUser(age = 27)  == User(Joe, 27)
      """)
    ),

    slide(
      "Case Objects",
      <.p("What if you want to represent colors?"),
      <.br,
      code("""
        // this is static; will not change
        // why create a new instance every time?
        case class Green()
      """),
      codeFragment("""
        // use case objects instead
        case object Green
      """)
    ),

    slide(
      "Objects",
      <.p("Objects are classes with a single instance. You use them to provide static fields and functions.")
    ),

    slide(
      "Objects",
      code("""
        object Predef {
        
          // constant fields start with a uppercase letter
          val Joe = User("joe", 27)

          def older(a: User, b: User): User = if (a.age > b.age) a else b
        }
      """),
      codeFragment("""
        Predef.older(User("Jim", 21), Predef.Joe) == Predef.Joe
      """)
    ),

    exerciseSlide(
      "Let's code",
      bashCode("""
        sbt> project scala-101-exercises
        sbt> test:testOnly exercise1.CaseClassesSpec
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
        case class Man(name: String, age: Int)
        case class Woman(name: String, age: Int)
        case class Child(name: String, age: Int)
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

        case class Man(name: String, age: Int)   extends Person
        case class Woman(name: String, age: Int) extends Person
        case class Child(name: String, age: Int) extends Person
      """)
    ),

    slide(
      "Traits: common properties and behaviour",
      code("""
        // all subclasses share this properties
        sealed trait Person {

          val name: String
          val age: Int

          // default implementation
          def isOlder(other: Person): Boolen = age > other.age
        }
      """),
      codeFragment("""
        case class Man(name: String, age: Int) extends Person

        val man = Man("John", 27)

        man.isOlder(User("Jim", 21) == true
      """)
    ),

    slide(
      "OOP",
      <.h4("This all looks like OOP to you?"),
      <.br,
      <.p(
        ^.cls := "fragment fade-in",
        "That's because it is. Again Scala is the fusion of OOP and FP."
      )
    ),

    exerciseSlide(
      "Let's code",
      bashCode("""
        sbt> project scala-101-exercises
        sbt> test:testOnly exercise1.TraitsSpec
      """)
    ),

    noHeaderSlide(
      <.h3("So far, We learned stuff about ..."),
      <.br,
      Enumeration(
        Item.fadeIn(<.p("expressions and declarations")),
        Item.fadeIn(<.p("functions and types")),
        Item.fadeIn(<.p("classes")),
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
      <.h2("Pattern Matching")
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
      "Get the whole value",
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
      "Let's code",
      bashCode("""
        sbt> project scala101-exercises
        sbt> test:testOnly exercise1.PatternMatchingSpec
      """)
    )
  )

  val summary = chapter(
    chapterSlide(
      <.h2("Summary")
    ),

    slide(
      "Expressions",
      code("""
        {
          val a = 1 + 2
      
          a * 5
        }

        == 15
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

        plus(1, 2) == 3
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
          classes,
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
