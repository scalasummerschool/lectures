
final class Session(paragraphs: List[StdLibInteractive.ParagraphT]) {

  private var head = Option.empty[StdLibInteractive.ParagraphT]
  private var tail = paragraphs

  def apply(): Unit = {
    head = tail.headOption
    tail = if (tail.isEmpty) Nil else tail.tail

    head match {
      case None         => println("You finished this session.")
      case Some((p, _)) => println(p)
    }
  }

  def expected(): Unit = {
    head match {
      case None                => 
        println("You aren't in an active session right now. Try `restart`.")

      case Some((_, expected)) => 
        val expStr  = expected.map { case (op, exp) => s"$op: $exp" }.mkString("  ", "\n  ", "")
        val message = "Did you get the following results?\n" + expStr

        println(message)
    }
  }

  def restart(n: Int = 1): Unit = {
    if (n > paragraphs.length || n <= 1) {
      head = Option.empty[StdLibInteractive.ParagraphT]
      tail = paragraphs
    }
    else {
      head = Some(paragraphs(n - 1))
      tail = paragraphs.drop(n)
    }
  }
}

object StdLibInteractive {

  type ParagraphT = (String, Map[String, String])

  val seq = Seq(1, 2, 3, 4)

  def sequence(): Session =
    new Session(List(
      """In this session we are looking into `Seq`. All exercises should be done using `val seq = Seq(1, 2, 3, 4)`
        |defined in this session. 
        |
        |Let's start with accessing elements by index:
        |  
        |  seq(1) == 2
        |
        |That is calling `seq.apply(1)` which is returning the second element (we start at zero). Now try to access
        |the first, second and 100th element.
      """.stripMargin -> Map(
        "seq(0)" -> s"  ${seq(0).toString}",
        "seq(1)" -> s"  ${seq(1).toString}",
        "seq(100)" -> "java.lang.IndexOutOfBoundsException: 100"
      ),

      """You can also access the head or tail by using the following methods:
        |  
        |  seq.head
        |  seq.tail
        |  seq.headOption
        |
        |Now try to access the `headOption`, call `tail` consecutively five times on seq and get the `head`
        |from `Nil`.
      """.stripMargin -> Map(
        "seq.headOption" -> ("              " + seq.headOption.toString),
        "seq.tail.tail.tail.tail.tail" -> "java.lang.UnsupportedOperationException: tail of empty list",
        "Nil.head" -> "                    java.util.NoSuchElementException: head of empty list"
      )
    ))
}
