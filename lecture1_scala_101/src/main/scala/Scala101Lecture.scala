
import PresentationUtil._
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Scala101Lecture extends JSApp {

  import Enumeration._

  val overview = chapter(
    chapterSlide(
      <.h1("Scala 101")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable("Scala background"),
        Item.fadeIn("Expressions & Declarations & Objects"),
        Item.fadeIn("Types"),
        Item.fadeIn("Functions"),
        Item.fadeIn("Classes"),
        Item.fadeIn("Pattern Matching")
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

    noHeaderSlide(
      <.h3("Why Scala?")
    ),

    slide(
      "Why Scala: Java ecosystem",
      <.img(^.alt := "Java Ecosystem", ^.src := "./img/java-logo.svg")
    ),

    slide(
      "Why Scala: Java ecosystem",
      Enumeration(
        Item.stable("access to Java ecosystem"),
        Item.fadeIn("or any ecosystem compiling to ByteCode"),
        Item.fadeIn("JVM performance (JIT, GC)"),
        Item.fadeIn("... and tooling (metrics, debugging, etc.)"),
      )
    ),

    slide(
      "JVM in a nutshell",
      <.img(
        ^.alt   := "JVM in a nutshell",
        ^.width := "50%",
        ^.src   := "./img/jvm.svg"
      )
    ),

    slide(
      "Why Scala: brings its own ecosysten",
      <.img(^.alt := "Scala Ecosystem", ^.src := "./img/scala-ecosystem.svg")
    ),

    slide(
      "Why Scala: fusion of FP and OOP",
      Enumeration(
        Item.stable("fusion of FP and OOP"),
        Item.fadeIn("with additional Type-Level programming on top"),
        Item.fadeIn("choose paradigm which solve your problem best ..."),
        Item.fadeIn("... stay in the same language")
      )
    ),

    slide(
      "Why Scala: industry & academia combined",
      Enumeration(
        Item.stable("big players use it: Twitter, Stripe to name a few"),
        Item.fadeIn("backed by Lightbend Inc."),
        Item.fadeIn("backed by EPFL"),
        Item.fadeIn("... and of course by an Open Source community")
      )
    ),

    noHeaderSlide(
      <.h3("Why not to use Scala?")
    ),

    slide(
      "Why not to use Scala: tooling",
      <.h3("Tooling")
    ),

    slide(
      "Why not to user Scala: learning curve",
      <.img(
        ^.alt := "Learning Curve",
        ^.src := "./img/learning_curve.jpg"
      )
    ),

    noHeaderSlide(
      <.h3("Are you still convinced?"),
      <.br,
      <.h4(
        ^.cls := "fragment fade-in",
        "No is not an answer! So, let's move on."
      )
    )
  )

  val expressions = chapter(
    chapterSlide(
      <.h2("Expressions & Declarations & Objects"),
      <.h3("Scalas' basic building blocks")
    ),

    noHeaderSlide(
      <.h3("Objects")
    ),

    slide(
      "Objects",
      <.p("An object is a collection of values and a set of operations on that values."),
      <.br,
      scalaC("""
        // example: literal object
        1
        // value: integer 1
        // operations: +, -, *, /, etc.
      """),
      scalaCFragment("""
        // example: literal object
        "Hello"
        // value: string Hello
        // operations: +, split, take, etc.
      """),
      scalaCFragment("""
        // example: class object (tuple)
        (1, "Hello")
        // values: integer 1 and string Hello
        // operations: _1, _2, swap, etc.
      """)
    ),

    noHeaderSlide(
      <.h3("Expressions")
    ),

    slide(
      "Expressions",
      <.p("Expression can be objects, combination of objects or functions applied to objects. And they get evaluated/reduced to results.")
    ),

    slide(
      "Objects as expressions",
      <.p("Objects"),
      <.br,
      scalaC("""
        // already in reduced form
        1

        'a'

        "Hello"
      """)
    ),

    slide(
      "Operations as expressions",
      <.p("Function/Operation application"),
      <.br,
      scalaC("""
        1 + 2
      """),
      scalaCFragment("""
        == 3
      """),
      <.br,
      <.p(
        ^.cls := "fragment fade-in",
        "But how do we use the result?"
      )
    ),

    noHeaderSlide(
      <.h3("Assignment")
    ),

    slide(
      "Assignment of Results",
      scalaC("""
          val result = 1 + 2
        // ^    ^    ^
        // |    |    '
        // |    |  assigns result to identifier
        // |    '
        // |   identifier
        // '
        // starts assignment

        result == 3
      """)
    ),

    slide(
      "Assignment of Results",
      scalaC("""
        val result = 1 + 2

        // forbidden - `result` never changes
        result = 2 + 3
      """)
    ),

    slide(
      "Expressions",
      <.p("Okay, we can create single line expressions or assignments. But this is barely useful.")
    ),

    noHeaderSlide(
      <.h3("Block Expressions")
    ),

    slide(
      "Block Expressions",
      <.p("When you want to combine multiple expressions you put them in blocks."),
      <.br,
      scalaC("""
        // start with '{' and end with '}'
        {
          val a = 1 + 2
      
          3 * a
        }
      """)
    ),

    slide(
      "Block expressions",
      scalaC("""
        {
          val a = 1 + 2
      
          3 * a
        }
      """),
      scalaCFragment("""
        {
           val a = 3      <==
      
           3 * a
        }
      """),
      scalaCFragment("""
        {
           val a = 3
      
           3 * 3          <==
        }
      """),
      scalaCFragment("""
         == 9
      """)      
    ),

    slide(
      "Scoping: values in blocks",
      <.p("Access objects in the same or an outer block."),
      <.br,
      scalaC("""
        {
          val a = 1 + 2
 
          // `a` in scope
          {
             val b = 3 + 4
 
             a + b
          }
        }
      """)
    ),

    slide(
      "Scoping: values in blocks",
      scalaC("""
        {
          val a = 1 + 2
 
          {
             val b = 3 + 4

             a + b
          }
        }
      """),
      scalaCFragment("""
        {
          val a = 3       <==
 
          {
             val b = 7    <==
  
             a + b
          }
        }
      """)
    ),

    slide(
      "Scoping: values in blocks",
      scalaC("""
        {
          val a = 3
 
          {
             val b = 7

             3 + 7        <==
          }
        }
      """),
      scalaCFragment("""
        == 10
      """)
    ),

    slide(
      "Block Expressions",
      <.p("From now on we ignore the outer block brackets in the slides.")
    ),

    exerciseSlide(
      "Does it work?",
      scalaC("""
        val a = 5
        {
          val b = 3

          a + b
        }
      """),
      scalaCFragment("""
        == 8
      """)
    ),

    exerciseSlide(
      "Does it work?",
      scalaC("""
        val a = {
          val b = 6

          10
        }
        a + b
      """),
      scalaCFragment("""
        // no, `b` doesn't exist in the outer scope
      """)
    ),

    exerciseSlide(
      "Does it work?",
      scalaC("""
        val a = 5
        {
          val a = {
            a * 10
          }

          a
        }
      """),
      scalaCFragment("""
        // no, you try to define `a` by using itself
      """)
    ),

    exerciseSlide(
      "Does it work?",
      scalaC("""
        val a = 5
        {
          val a = 10

          a
        }
      """),
      scalaCFragment("""
        == 10
      """)
    ),

    noHeaderSlide(
      <.h3("Conditional Branching")
    ),

    slide(
      "Conditional expression",
      scalaC("""
        val a = 5

        if (a > 0) {
          "positive number"
        }
      """),
      scalaCFragment("""
        else if (a == 0) {
          "is zero"
        }
      """),
      scalaCFragment("""
        else {
          "negative number"
        }
      """)
    ),

    slide(
      "Comparison",
      scalaC("""
        a >  b  // greater than
        a <  b  // smaller than
        a >= b  // greater or equal
        a <= b  // smaller or equal
        a == b // equal
        a != b // equal

        // more later on ...
      """)
    ),

    slide(
      "Short Summary",
      <.p("So far we have ..."),
      <.br,
      scalaC("""
        // object expressions
        val PI     = 3.14
        val radius = 2.0
      """),
      scalaCFragment("""
        // function applications
        val area = radius * radius * PI
      """),
      scalaCFragment("""
        // conditional expressions
        if (area > 4.0) {
          "big circle"
        }
        else {
          "small circle"
        }
      """)
    ),

    slide(
      "Expression Evaluation",
      <.p("But in which order are these expressions evaluated exactly?")
    ),

    slide(
      "Expression Evaluation",
      Enumeration(
        Item.stable("from top to bottom"),
        Item.fadeIn("from left to right"),
        Item.fadeIn("before application"),
        Item.fadeIn("following precedence rules")
      )
    ),

    slide(
      "Precedence",
      scalaC("""
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
      scalaC("""
        2 * 3 + 10
      """),
      scalaCFragment("""
        // prec('*') > prec('+')
        == (2 * 3) + 10
      """),
      scalaCFragment("""
        == 6 + 10
      """),
      scalaCFragment("""
        == 16
      """)
    ),

    exerciseSlide(
      "What's the result?",
      scalaC("""
        2.0 * 3.0 / 3.0
      """),
      scalaCFragment("""
        // '*' left most operation
        == (2.0 * 3.0) / 3.0
      """),
      scalaCFragment("""
        == 6.0 / 3.0
      """),
      scalaCFragment("""
        == 2.0
      """)
    ),

    exerciseSlide(
      "What's the result?",
      scalaC("""
        val a = 1 + 2

        if (a > 1 + 3) {
          "a = " + a
        }
        else {
          "too small"
        }
      """),
      scalaCFragment("""
        == {
          if (3 > 4) {
            "a = " + 3
          }
          else {
            "too small"
          }
        }
      """),
      scalaCFragment("""
        == "too small"
      """)
    ),

    noHeaderSlide(
      <.h3("Expressions return typed results"),
      <.br,
      scalaCFragment("""
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
      <.h3("Types put constraints on term")
    ),

    slide(
      "State of a value",
      <.p("Restriction on objects."),
      <.br,
      scalaC("""
        val a: Int = 0

        // `a` is of type `Int`
        // is an Integer number
        // -2147483648 <= a <= 2147483647

        val b: String = a // forbidden
      """)
    ),

    slide(
      "Operation restriction",
      <.p("Restriction on operations"),
      <.br,
      scalaC("""
        1 + 2    == 3: Int
        1 + 2.0  == 3.0: Double
      """),
      scalaCFragment("""
        1 + true // forbidden
      """)
    ),

    slide(
      "Types",
      <.p("Types restrict the number of valid programs. Basically, it is harder for you to write invalid programs.")
    ),

    exerciseSlide(
      "What is the type?",
      scalaC("""
        1 + 2
      """),
      scalaCFragment("""
        == 3: Int
      """),
      <.br,
      scalaC("""
        1.0 + 2
      """),
      scalaCFragment("""
        == 3.0: Double
      """),
      <.br,
      scalaC("""
        "Hello " + true
      """),
      scalaCFragment("""
        == "Hello true": String
      """)
    ),

    exerciseSlide(
      "What is the type?",
      scalaC("""
        1 / 2
      """),
      scalaCFragment("""
        == 0: Int
      """),
      <.br,
      scalaC("""
        1 / 2.0
      """),
      scalaCFragment("""
        == 0.5: Double
      """)
    ),

    slide(
      "Type: number types",
      <.p("Scala picks the more precise number type during operator application.")
    ),
    
    slide(
      "Types are important",
      Enumeration(
        Item.stable("guarantees about state of values / application of operators"),
        Item.fadeIn("proven during compile time"),
        Item.fadeIn("no extra tests necessary")
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
      <.p("Special type of expression which has a name, takes input and returns, as always, a result.")
    ),

    slide(
      "Functions",
      <.h4("They are expressions with a declaration")
    ),

    slide(
      "Functions",
      scalaC("""
        def plus(a: Int, b: Int): Int = a + b
      """),
      scalaCFragment("""
        // declaration
        def plus(a: Int, b: Int): Int
        //   ^     ^       ^       ^
        //   |     '-------|       |
        // identifier    input   result 
      """),
      scalaCFragment("""
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
      scalaC("""
        // functions are objects

        // this becomes
        def plus(a: Int, b: Int): Int = a + b

        // that
        val plus: (Int, Int) => Int = (a, b) => a + b
      """)
    ),

    slide(
      "Functions",
      Enumeration(
        Item.stable("have a unique identifier (name)"),
        Item.fadeIn("declare a number of parameters as input (arity)"),
        Item.fadeIn("and a single result type"),
        Item.fadeIn("body expression fulfills the declaration")
      )
    ),

    slide(
      "Function application",
      scalaC("""
        plus(1, 2) == 3
      """),
      scalaCFragment("""
        // `1` assigned to `a` and `2` to `b`
        // every appearance of `a` and `b` is replaced by the objects
        1 + 2      == 3
      """)
    ),

    noHeaderSlide(
      <.h3("Functions return objects. Functions are objects.")
    ),

    slide(
      "Curried Function",
      scalaC("""
        def plusCurried(a: Int): Int => Int = b => a + b
        
        // equal to
     
        def plusCurried(a: Int)(b: Int): Int = a + b
      """),
      scalaCFragment("""
        val plus1: Int => Int = plusCurried(1)
      """),
      scalaCFragment("""
        plus1(2) == plus(1, 2)
      """)
    ),

    slide(
      "Curried Functions",
      Enumeration(
        Item.stable("this technique is called currying"),
        Item.fadeIn("apply a single parameter at a time (left to right) - it's called partial application"),
        Item.fadeIn("every application returns a new function of arity $n - 1$")
      )
    ),

    slide(
      "Mix curried and uncurried",
      scalaC("""
        def plusPair(a: Int, b: Int)(c: Int, d: Int): Int = {
          plus(a, c) + plus(b, d)
        }
      """)
    ),

    slide(
      "Higher-Order functions",
      scalaC("""
        def plus(a: Int, b: Int): Int = ???

        // we can return function, we can pass functions - all just objects
        def resultMsg(f: (Int, Int) => Int)(a: Int, b: Int): String = {
          "The result for " + a + " and " + b + " is " + f(a, b)
        }

        val plusMsg = resultMsg(plus)

        plusMsg(1, 2) == "The result for 1 and 2 is 3"
      """),
      <.br,
      scalaCFragment("""
        def multiply(a: Int, b: Int): Int = ???

        val multMsg = resultMsg(multiply)

        multMsg(1, 2) == "The result for 1 and 2 is 2"
      """)
    ),

    slide(
      "Anonymous Functions",
      <.p("Sometimes a function parameter in a higher-order function is only used ones. So why providing a whole declaration?")
    ),

    noHeaderSlide(
      <.h3("Anonymous Functions")
    ),

    slide(
      "Anonymous Functions",
      scalaC("""
        // just provide the expression body - no declaration
        val subMsg = resultMsg((a, b) => a - b)

        subMsg(1 , 2) == "The result of 1 and 2 is -1"
      """)
    ),

    exerciseSlide(
      "Let's code: Functions",
      bash("""
        # start sbt (keep it running)
        $> sbt

        # switch into exercise project
        sbt> project scala101-exercises

        # to compile your code
        sbt> compile

        # to test your implementation
        sbt> test:testOnly exercise1.FunctionsSpec
      """)
    ),

    exerciseSlide(
      "Let's code",
      scalaC("""
        // package/path definition: <package>.<object-name> must be unique
        package exercise1

        object Functions {
          // your code goes here
          
          ...
        }
      """)
    ),

    exerciseSlide(
      "Let's code: HigherOrder",
      bash("""
        sbt> project scala101-exercises
        sbt> test:testOnly exercise1.HigherOrderSpec
      """)
    ),

    noHeaderSlide(
      <.h3("Enough about functions for now"),
      <.br,
      <.h4("Let's do something new. Let's create some data types!")
    )
  )

  val classes = chapter(
    chapterSlide(
      <.h2("Classes")
    ),

    slide(
      "Create a new type",
      <.h3("Scala's objects only get us so far."),
      <.br,
      <.h4("We want to be able to create our own.")
    ),

    slide(
      "Create a new type",
      <.p("You create new types in Scala by defining Classes. A class is a way to define a set of objects which have same fields and functions."),
      <.br,
      Enumeration(
        Item.fadeIn("class"),
        Item.fadeIn("case class"),
        Item.fadeIn("object"),
        Item.fadeIn("trait"),
        Item.fadeIn("abstract Class")
      )
    ),

    noHeaderSlide(
      <.h3("Classes")
    ),

    slide(
      "Classes",
      scalaC("""
        class Person(val name: String, val age: Int)
        //     ^        ^                 ^
        //     '        '-----------------'
        // identifier            '
        //              constructor with public fields
      """),
      scalaCFragment("""
        val user = new Person("Gandalf", 2019)
        //          ^
        //          '
        //   call constructor / create new object

        user.name == "Gandalf"
        user.age  == 2019
      """)
    ),

    slide(
      "Classes: private fields",
      scalaC("""
        class Person(val name: String, age: Int)
        //                            ^
        //                            '
        //                       private field
      """),
      scalaCFragment("""
        val user = new Person("Gandalf", 2019)

        user.name == "Gandalf"
        user.age // not allowed
      """)
    ),

    slide(
      "Classes: inner fields",
      scalaC("""
        class Person(val name: String, val age: Int) {
          private val ageString = age.toString

          val show = "person: " + name + ", " + ageString
        }

        new Person("Gandalf", 2019).show == "person: Gandalf, 2019"
      """),
      scalaCFragment("""
        val user = new Person("Gandalf", 2019)

        user.show == "person: Gandalf, 2019"
        user.ageString // not allowed
      """)
    ),

    slide(
      "Classes: methods",
      <.p("Methods aka context sensitive functions."),
      <.br,
      scalaC("""
        class Person(val name: String, val age: Int) {

        // functions refering to their object are called methods
          def isOlder(other: Person): Boolean = age > other.age
      """),
      scalaCFragment("""
        // equal to
          def isOlder(other: Person): Boolean = this.age > other.age
        }
      """)
    ),

    slide(
      "Classes: methods",
      <.p("Methods aka context sensitive functions."),
      <.br,
      scalaC("""
        // or
        def isOlder(user: Person)(other: Person): Boolean = 
          user.age > other.age
      """),
      scalaCFragment("""
        val gandalf = new Person("Gandalf", 2019)

        gandalf.isOlder(new Person("Frodo", 33)) == {
          isOlder(gandalf)(new Person("Frodo", 33))
        }
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

    noHeaderSlide(
      <.h3("Case Classes")
    ),

    slide(
      "Case Classes",
      scalaC("""
        case class Person(name: String, age: Int)
        //          ^    ^             ^
        //          |    '-------------'
        //       identifier     |
        //                      |
        //                 public fields

        // no `new` keyword any longer
        val gandalf = Person("Gandalf", 2019)
      """)
    ),

    slide(
      "Case Classes: How to mutate them?",
      scalaC("""
        // comes with a copy method
        val gandalf = Person("Gandalf", 2019)

        // creates copy of `gandalf`
        gandalf.copy("Gandolf", 2000) == Person("Gandolf", 2000)
      """)
    ),

    slide(
      "Case Classes: How to mutate them?",
      scalaC("""
        val gandalf = Person("Gandalf", 2019)

        // change just a supset of fields
        gandalf.copy(name = "Gandolf") == Person("Gandolf", 2019)
      """)
    ),

    slide(
      "Functions: default parameter",
      <.p("Default parameters and by-name application"),
      <.br,
      scalaC("""
        def newPerson(name: String = "Gandalf", age: Int = 2019): Person =
          Person(name, age)

        newPerson()                == Person("Gandalf", 2019)
        newPerson("Gandolf", 2000) == Person("Gandolf", 2000)
        newPerson("Gandolf")       == Person("Gandolf", 2019)
        newPerson(age = 2000)      == Person("Gandalf", 2000)
      """)
    ),

    slide(
      "Case Objects",
      <.p("What if you want to represent some case which is static?"),
      <.br,
      scalaC("""
        // this is static; will not change
        // why create a new instance every time?
        case class Green()
        case class Red()
      """),
      scalaCFragment("""
        // use `case objects` instead
        case object Green
        case object Red
      """)
    ),

    noHeaderSlide(
      <.h3("Objects")
    ),

    slide(
      "Objects",
      <.p("Objects are classes with a single instance. You use them to provide static fields and functions.")
    ),

    slide(
      "Objects",
      <.p("From now on we refer to object classes as ", <.strong("Objects"), " and object terms as ", <.strong("Values"), ".")
    ),

    slide(
      "Objects",
      scalaC("""
        object Predef {
        
          // constant fields start with a uppercase letter
          val Gandalf = Person("Gandalf", 2019)

          def older(a: Person, b: Person): Person = 
            if (a.age > b.age) a else b
        }
      """),
      scalaCFragment("""
        Predef.older(Person("Frodo", 33), Predef.Gandalf) == Predef.Gandalf
      """)
    ),

    slide(
      "Companion Objects: a class's companion",
      <.p("Companion to a class."),
      <.br,
      scalaC("""
        case class Person(name: String, age: Int)
      """),
      scalaCFragment("""
        // same name as the class, case class, trait, abstract class
        object Person {

          val Gandalf = Person("Gandalf", 2019)

          def isGandalf(person: Person): Boolean = 
            person.name == "Gandalf"
        }
      """)
    ),

    slide(
      "Traits",
      <.p("But what if we have multiple data types which share a relation?")
    ),

    noHeaderSlide(
      <.h3("Traits")
    ),

    slide(
      "Traits",
      scalaC("""
        // all people (of some magic realm) but of different type
        case class Wizard(name: String, power: String)
        case class Elf(name: String, age: Int)        
        case class Dwarf(name: String, height: Int)   
      """)
    ),

    slide(
      "Traits",
      scalaC("""
          sealed trait Person
        //  ^            ^
        //  '            '-------------------
        // only allow sub classes           '
        // in this file (optional)       identifier

        case class Wizard(name: String, power: String) extends Person
        case class Elf(name: String, age: Int)         extends Person
        case class Dwarf(name: String, height: Int)    extends Person
      """)
    ),

    slide(
      "Traits: common properties and behaviour",
      scalaC("""
        // all subclasses share this properties
        sealed trait Person {

          // expected field
          val name: String

          // expected function
          def likesPlace(place: String): Boolean

          // default implementation
          def sameName(other: Person): Boolean = name == other.name
        }
      """)
    ),

    slide(
      "Traits: common properties and behaviour",
      scalaC("""
        case class Wizard(name: String, power: String) extends Person {
          def likesPlace(place: String): Boolean = true
        }

        case class Elf(name: String, age: Int) extends Person {
          def likesPlace(place: String): Boolean = place == "woods"
        }

        case class Darf(name: String, height: Int) extends Person {
          def likesPlace(place: String): Boolean = place == "mountain"
        }

        val gandalf = Wizard("Gandalf", "magic")

        gandalf.likesPlace("The Shire") == true
      """)
    ),

    noHeaderSlide(
      <.h3("Abstract Classes")
    ),

    slide(
      "Abstract Classes",
      scalaC("""
        // non complete (abstract methods) class definition
        abstract class Person(val name: String) {

          def isOlder(person: Person): Boolean
        }
      """)
    ),

    noHeaderSlide(
      <.h3("Functions and Classes")
    ),

    slide(
      "Functions and Classes",
      <.p("Function values are instances of Function-Classes in Scala. A Function-Class is a class which provides an ", <.strong("apply"), " method.")
    ),

    slide(
      "Functions and Classes",
      scalaC("""
        class Plus() {

          def apply(a: Int, b: Int): Int = a + b
        }
      """),
      scalaCFragment("""
        val plus = new Plus()

        plus.apply(1, 2) == plus(1, 2)
                         == 3
      """)
    ),

    slide(
      "Functions and Classes",
      scalaC("""
        object Plus {

          def apply(a: Int, b: Int): Int = a + b
        }
      """),
      scalaCFragment("""
        Plus.apply(1, 2) == Plus(1, 2)
                         == 3
      """)
    ),

    slide(
      "Functions and Classes: inheritence",
      scalaC("""
        object Plus extends ((Int, Int) => Int) {

          def apply(a: Int, b: Int): Int = a + b
        }
      """),
      scalaCFragment("""
        Plus.apply(1, 2) == Plus(1, 2)
                         == 3
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
      "Let's code: Classes",
      bash("""
        sbt> project scala101-exercises
        sbt> test:testOnly exercises1.ClassesSpec
      """)
    ),

    noHeaderSlide(
      <.h3("So far, We learned stuff about ..."),
      <.br,
      Enumeration(
        Item.fadeIn("expressions and declarations"),
        Item.fadeIn("functions and types"),
        Item.fadeIn("classes"),
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
        Item.stable("match values against pattern"),
        Item.fadeIn("extract information"),
        Item.fadeIn("decide program control flow")
      )
    ),

    slide(
      "Pattern Matching",
      scalaC("""
        val person: Person = ???

        person match {
          case Wizard(name, power) => s"this Wizards uses $power"
          case Elf(name, age)      => s"this Elf is $age years old"
          case Dwarf(name, height) => s"this dwarf is $height cm tall"
        }
      """)
    ),

    slide(
      "Pattern Matching",
      scalaC("""
        val person = Wizard("Gandalf", "magic")
      """),
      scalaCFragment("""
        person match {
          case Wizard(name, power) => s"this Wizards uses $power"
          case Elf(name, age)      => s"this Elf is $age years old"
          case Dwarf(name, height) => s"this dwarf is $height cm tall"
        }
      """),
      scalaCFragment("""
        person match {
          case Wizard(name, power = "magic") => s"this Wizards uses $power"
          // case Elf(name, age)             => ...
          //case Dwarf(name, height)         => ...
        }
      """)
    ),

    slide(
      "Pattern Matching",
      scalaC("""
        val person = Dwarf("Gimli", 107 /*cm*/)
      """),
      scalaCFragment("""
        person match {
          case Wizard(name, power) => s"this Wizards uses $power"
          case Elf(name, age)      => s"this Elf is $age years old"
          case Dwarf(name, height) => s"this dwarf is $height cm tall"
        }
      """),
      scalaCFragment("""
        person match {
          // case Wizard(name, power) => ...
          case Elf(name, age)         => s"this Elf is $age years old"
          case Dwarf(name, height)    => s"this dwarf is $height cm tall"
        }
      """),
      scalaCFragment("""
        person match {
          // case Wizard(name, power)    => ...
          // case Elf(name, age)         => ...
          case Dwarf(name, height = 107) => s"this dwarf is $height cm tall"
        }
      """)
    ),

    slide(
      "Ommit values you don't need",
      scalaC("""
        val person: Person = ???

        person match {
          case Wizard(name, _) => name
          ...
        }
      """)
    ),

    slide(
      "Conditional cases",
      scalaC("""
        val person: Person = ???

        person match {
          // you can also use `&` and `|`
          case Wizard(name, _) if name == "Gandalf" => "a wizard is never late"
          ...
        }
      """)
    ),

    slide(
      "Get the whole value",
      scalaC("""
        val person: Person = ???

        person match {
          // you can also use `&` and `|`
          case w@Wizard(_, power) if power == "magic" => w
          ...
        }
      """)
    ),

    slide(
      "Match on everything",
      scalaC("""
        val person: Person = ???

        person match {
          case person: Person => person.name
        }
      """)
    ),

    slide(
      "Match on everything",
      scalaC("""
        val person: Person = ???

        person match {
          case Wizard(name, _) => "The mighty " + name
          case person: Person  => person.name

          // can't reach this -> previous case captures everything
          case Dwarf(_, _) => "you will never now"
        }
      """)
    ),

    slide(
      "Ignore match",
      scalaC("""
        val person: Person = ???

        person match {
          case Wizard(name, _) => "The mighty " + name
          case _               => "I don't care"
        }
      """)
    ),

    slide(
      "Pattern Matching",
      Enumeration(
        Item.stable("Make it exhaustive. Don't miss a case."),
        Item.fadeIn("Only declare fields you use."),
        Item.fadeIn("Works also with primitives.")
      )
    ),

    slide(
      "Match primitives",
      scalaC("""
        0 match {
          case 5 => "yeah five"
          case 0 => "nay zero"
          case _ => "don't care"
        }
      """)
    ),

    exerciseSlide(
      "Let's code: Pattern Matching",
      bash("""
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
      scalaC("""
        {
          val a = 1 + 2
      
          3 * a
        }

        == 15
      """)
    ),

    slide(
      "Types",
      scalaC("""
        val a: String = "Hello"

        val b: Int = a // no no
      """)
    ),

    slide(
      "Functions",
      scalaC("""
        def plus(a: Int, b: Int): Int = a + b

        plus(1, 2) == 3
      """)
    ),

    slide(
      "Classes",
      scalaC("""
        sealed trait Persons {
          val name: String
        }

        case class Wizard(name: String, power: String) extends Person
        case class Elf(name: String, age: Int)         extends Person
      """)
    ),

    slide(
      "Pattern Matching",
      scalaC("""
        val person: Person = ???

        person match {
          case Wizard(name, _) => name
          case Elf(name, _)    => name
        }
      """)
    ),

    noHeaderSlide(
      <.h2("Next Topic"),
      <.br,
      <.h3("Functional Programming 101")
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
