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

  val Show = ScalaComponent
    .builder[Unit]("Slideshow")
    .renderStatic(
      <.div(
        ^.cls := "reveal",
        <.div(
          ^.cls := "slides",
          overview
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
