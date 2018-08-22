
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.vdom.TagOf
import org.scalajs.dom
import dom.raw.HTMLElement

object SlideshowUtil {  

  val dataBackground      = VdomAttr("data-background")
  val dataBackgroundColor = VdomAttr("data-background-color")
  val dataBackgroundSize  = VdomAttr("data-background-size")
  val dataTrim            = VdomAttr("data-trim") := ""

  def chapter(slides: TagOf[HTMLElement]*): TagOf[HTMLElement] = <.section(slides: _*)

  def header(text: String, cls: String): TagOf[HTMLElement] = 
    <.div(
      ^.cls := cls,
      <.img(
        ^.src  := "./../reveal/img/logo.svg",
        ^.alt  := "Scala Summer School logo"
      ),
      <.p(text)
    )

  // 100% side-effect full
  private def removeHeader(): Unit = {
    val headerElements = dom.document.getElementsByClassName("slide-header")

    (0 until headerElements.length).foreach { id =>
      val element = headerElements(id)

      element.parentNode.removeChild(element)
    }
  }

  private def cleanSlide(content: TagOf[HTMLElement]): TagOf[HTMLElement] = {
    removeHeader()
    
    content
  }

  private val ChapterSlideProps = Seq(
    (dataBackground      := "./../reveal/img/dark-logo.svg"),
    (dataBackgroundColor := "#363633"),
    (dataBackgroundSize  := "30%")
  )

  def chapterSlide(content: TagOf[HTMLElement]*): TagOf[HTMLElement] = cleanSlide(
    <.section(
      (ChapterSlideProps ++: content): _*
    )
  )

  def noHeaderSlide(content: TagOf[HTMLElement]*): TagOf[HTMLElement] = cleanSlide(
    <.section(
      content: _*
    )
  )
  
  def exerciseSlide(headerStr: String, content: TagOf[HTMLElement]*): TagOf[HTMLElement] = cleanSlide(
    <.section(
      (header(headerStr, "exercise-header") +: content): _*
    )
  )

  def slide(headerStr: String, content: TagOf[HTMLElement]*): TagOf[HTMLElement] = cleanSlide(
    <.section(
      (header(headerStr, "slide-header") +: content): _*
    )
  )

  private def rawCode(language: String, codeStr: String): TagOf[HTMLElement] =
    <.code(
      ^.cls := language,
      dataTrim,
      codeStr
    )

  def code(codeStr: String): TagOf[HTMLElement] = <.pre(rawCode("Scala", codeStr))

  def codeFragment(codeStr: String): TagOf[HTMLElement] =
    <.pre(
      ^.cls := "fragment fade-in",
      rawCode("Scala", codeStr)
    )

  def bashCode(codeStr: String): TagOf[HTMLElement] = <.pre(rawCode("Bash", codeStr))

  object Enumeration {
   
    object Item {

      def stable(content: TagOf[HTMLElement]): TagOf[HTMLElement] = <.li(content)
      def fadeIn(content: TagOf[HTMLElement]): TagOf[HTMLElement] = <.li(^.cls := "fragment fade-in", content)
    }

    def apply(head: TagOf[HTMLElement], tail: TagOf[HTMLElement]*): TagOf[HTMLElement] = {
      <.ul(
        (head +: tail): _*
      )
    }
  }
}
