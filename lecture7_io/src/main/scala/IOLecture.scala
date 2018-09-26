
import PresentationUtil._
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object IOLecture extends JSApp {

  import Enumeration._

  val overview = chapter(
    chapterSlide(
      <.h1("IO")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable("Cats Effect IO"),
        Item.fadeIn("Shared State")
      )
    )
  )

  val io = chapter(
    chapterSlide(
      <.h2("Cats Effect IO")
    ),

    noHeaderSlide(
      <.a(
        ^.href := "https://github.com/typelevel/cats-effect",
        "https://github.com/typelevel/cats-effect"
      )
    ),

    slide(
      "Cats Effect IO",
      <.p("Provides an implementation of the described IO concept to handle side effects (and more).")
    ),

    slide(
      "Side Effect Example",
      scalaC("""
        // idea
        for {
          name <- readLine()
           
          _    <- println("Hello" + name)
        } yield () //: IO[Unit]
      """)
    ),

    slide(
      "Side Effect Example",
      scalaC("""
        // code
        import cats.effects.IO

        val appIO = for {
          name <- IO(readLine())
           
          _    <- IO(println("Hello" + name))
        } yield ()
      """)
    ),

    slide(
      "Cats Effect IO",
      <.p("IO is a Monad and comes with all its properties."),
      <.br,
      scalaC("""
        IO.pure(0) // never put side-effects here

        IO(readLine()).flatMap(name => println("Hello " + name)
      """)
    ),
   
    slide(
      "Cats Effect IO",
      <.p("We got an ", <.strong("appIO: IO[Unit]"), ". Nothing happend. What want action.")
    ),

    slide(
      "Cats Effect IO: run",
      scalaC("""
        // danger-zone - blocks, throws Exceptions ... impure 
        appIO.unsafeRunSync()
        // $ Gandalf
        // $ Hello Gandalf
      """),
      scalaCFragment("""
        appIO.unsafeRunSync()
        // $ Frodo
        // $ Hello Frodo
      """)
    ),

    slide(
      "Cats Effect IO: run", 
      scalaC("""
        appIO.unsafeRunTimed(FiniteDuration(5, SECONDS)
      """)
    ),

    slide(
      "Cats Effect IO: run", 
      scalaC("""
        appIO.unsafeRunAsync {
          case Right(a)    => ???
          case Left(error) => ???
        }
      """)
    ),

    slide(
      "Cats Effect IO: run",
      <.p("There are more ways to run an IO. Take a look into the documentation.")
    ),

    slide(
      "Cats Effect IO: run",
      <.p("But, you can also move the IO execution part out of your program into ", <.strong("IOApp"), ".")
    ),

    slide(
      "Cats Effect IO: run",
      scalaC("""
        import cats.effect.{IOApp, ExitCode}

        object App extends IOApp {

          def run(args: List[String]): IO[ExitCode] = 
            appIO.map(_ => ExitCode.Success)
        }
      """)
    ),
  
    noHeaderSlide(
      <.h3("But wait there is more")
    ),

    slide(
      "Cats Effect IO: stack-safety",
      <.h4("Stack-Safety"),
      <.br,
      scalaC("""
        def replicate[A](n: Int, a: A): List[A] = 
          if (n > 0) a :: replicate(n - 1, a)
          else       Nil

        
        // blows up our program
        replicate(100000, 0)
      """)
    ),

    slide(
      "Cats Effect IO: stack-safety",
      <.h4("Stack-Safety"),
      <.br,
      scalaC("""
        def replicate[A](n: Int, a: A): IO[List[A]] = 
          if (n > 0) replicate(n - 1, a).map(tail => a :: tail)
          else       IO.pure(Nil)

      """),
      scalaCFragment("""
        // still blows up
        replicate(100000, 0)
      """)
    ),

    slide(
      "Cats Effect IO: stack-safety",
      scalaC("""
        // postpones IO creation
        IO.suspend[A](thunk: => IO[A]): IO[A]
      """)
    ),

    slide(
      "Cats Effect IO: stack-safety",
      scalaC("""
        def replicate[A](n: Int, a: A): IO[List[A]] = 
          if (n > 0) IO.suspend(replicate(n - 1, a).map(tail => a :: tail))
          else       IO.pure(Nil)
      """),
      scalaCFragment("""
        // fine
        replicate(100000, 0)
      """)
    ),

    noHeaderSlide(
      <.h3("Resource Management")
    ),

    slide(
      "Cats Effect IO: brackets",
      <.p("Often you acquire some resource (web socket, db connection, etc.) to do IO. But how to guarantee a proper handling?")
    ),

    slide(
      "Cats Effect IO: brackets",
      scalaC("""
        val resource = IO(new BufferedReader(...))

        resource.bracket(
          // use resource
          buf => {
          val line = buf.readLine()
          ...
          },
          // release it
          buf => {
            IO(buf.close())
          }
        )
      """)
    ),

    slide(
      "Cats Effect IO: brackets",
      <.p("Brackets ensure that your resources are released even when an error occurs.")
    ),

    noHeaderSlide(
      <.h3("Error Handling")
    ),

    slide(
      "Cats Effect IO: error handling",
      scalaC("""
        // can fail if the given resource (e.g. file) doesn't exist
        val acquire = IO(new BufferedInputStream(...))
      """)
    ),

    slide(
      "Cats Effect IO: error handling",
      <.p("In case of an error IO will fail all computations until the it is recovered or the execution point is reached.")
    ),

    slide(
      "Cats Effect IO: error handling",
      scalaC("""
        acquire.handleErrorWith(
          case cause: FileNotFoundException => IO(???)           
        )
      """)
    ),

    slide(
      "Cats Effect IO: error handling",
      scalaC("""
        acquire.attempt.map(
          case Right(a)    => ???
          case Left(cause) => ???
        )
      """)
    ),

    slide(
      "Cats Effect IO",
      <.p("There is way more to IO ..."),
      Enumeration(
        Item.stable("concurrency"),
        Item.fadeIn("asynchronous computations"),
        Item.fadeIn("computational resource control"),
        Item.fadeIn("and more")
      )
    ),

    exerciseSlide(
      "Let's Code",
      <.p("I created a dirty, impure app. Make it nice and shiny (read functional) using IO.")
    )
  )

  val sharedState = chapter(
    chapterSlide(
      <.h2("Shared State")
    ),

    slide(
      "Shared State",
      <.p("Sometimes it is not feasable to have immutable or local state.")
    ),

    noHeaderSlide(
      <.h3("MVar & Ref")
    ),

    slide(
      "Shared State",
      scalaC("""
        // block until channel is empty
        def produce(ch: Channel, users: List[User]): IO[Unit] = users match {
          case user :: tail => 
            ch.put(Some(user)).flatMap(_ => produce(ch, tail))

          case Nil => ch.put(None)
        }
      """),
      scalaCFragment("""
        // block until channel is full
        def consume(ch: Channel): IO[List[User]] = 
          ch.take.flatMap {
            case Some(user) => consumer(ch).map(tail => user :: tail))
            case None       => IO.pure(Nil)
          }
      """)
    ),

    slide(
      "Shared State",
      scalaC("""
        val ch = ???

        // thread A
        produce(ch, List(User("Gandalf", 2019), ...))

        ...

        // thread B
        consume(ch)
      """)
    ),

    noHeaderSlide(
      <.h3("Make this work with MVar")
    ),

    slide(
      "Shared State: MVar",
      scalaC("""
        for {
          ch <- MVar[IO].empty[Option[User]]

          // run concurrently
          prod <- produce(ch, List(User("Gandalf", 2019), ...)).start
          cons <- consumer(ch).start

          _     <- prod.join
          users <- cons.join
        } yield users
      """)
    ),

    slide(
      "Shared State: MVar",
      Enumeration(
        Item.stable("is a shared piece of memory"),
        Item.fadeIn("synchronized, thread-safe, mutable"),
        Item.fadeIn("you can also just `read`")
      )
    ),

    noHeaderSlide(
      <.h3("I need no synchronization"),
      <.br,
      <.h4("Ref")
    ),

    slide(
      "Shared State: Ref",
      scalaC("""
        for {
          counter <- Ref.of[IO, Int](0)

          // run concurrently
          a <- IO(counter.update(_ + 1)).start
          b <- IO(counter.update(_ + 1)).start

          a.join
          b.join

          c <- counter.get()
        } yield c
      """)
    ),

    slide(
      "Shared State",
      <.p("Again, there is way more stuff in the library. Just take a look.")
    )
  )

  val summary = chapter(
    chapterSlide(
      <.h2("Summary")
    ),

    slide(
      "Cats Effect IO",
      <.p("Use IO to make side effects referential transparent."),
      <.br,
      scalaC("""
        def hello  = IO(println("hello, world!"))

        val helloV = hello

        {hello; hello} == {helloV; helloV}
      """)
    ),

    slide(
      "cats Effect IO",
      <.p("Use it for resource management, error handling and much more.")
    ),

    slide(
      "Shared State",
      <.p("User MVar and Ref to represent shared state.")
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
          io,
          sharedState,
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
