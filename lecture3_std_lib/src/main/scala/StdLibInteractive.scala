
final class Session(paragraphs: List[StdLibInteractive.ParagraphT]) {

  private var head = Option.empty[StdLibInteractive.ParagraphT]
  private var tail = paragraphs

  def next(): Unit = {
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

    next()
  }
}

object StdLibInteractive {

  type ParagraphT = (String, Map[String, String])

  implicit var session = new Session(List(
    "You have to start a session first." -> Map.empty
  ))

  def next(implicit sess: Session): Unit = sess.next()
  def expected(implicit sess: Session): Unit = sess.expected
  def restart(implicit sess: Session): Unit = sess.restart()
  def restart(n: Int = 1)(implicit sess: Session): Unit = sess.restart(n)

  val seq = Seq(1, 2, 3, 4)

  def sequence: Unit = {
    session = new Session(List(
      """A sequence is a collection of values with an order:
        |  
        |  // empty sequence
        |  Seq[Char]()
        |  Seq.empty[Char]
        |  Nil: Seq[Char]
        |
        |  // sequence of n elements
        |  Seq(1, 2, 3)
        |
        |Create sequences with:
        |  a) 1 as the single element 
        |  b) no elements and the type Boolean
      """.stripMargin -> Map(
        "a) Seq(1)" -> "            Seq(1)",
        "b) Seq.empty[Boolean]" -> "Seq()"
      ),

      """From now on all exercises should be done using `val seq = Seq(1, 2, 3, 4)` which is
        |defined in this session. 
        |
        |Let's start with accessing elements by index:
        |  
        |  seq(1) == 2
        |
        |That is calling `seq.apply(1)` which is returning the second element (we start at zero). Now try to access:
        |  a) the first
        |  b) the second
        |  c) the 100th element
      """.stripMargin -> Map(
        "a) seq(0)" -> ("  " + seq(0)),
        "b) seq(1)" -> ("  " + seq(1)),
        "c) seq(100)" -> "java.lang.IndexOutOfBoundsException: 100"
      ),

      """You can also access the head or tail by using the following methods:
        |  
        |  seq.head       == 1
        |  seq.tail       == Seq(2, 3, 4)
        |  seq.headOption == Some(1)
        |  seq.last       == 4
        |
        |Access:
        |  a) headOption
        |  b) call tail consecutively five times on seq
        |  c) get the head from Seq()
        |  d) last from Seq()
      """.stripMargin -> Map(
        "a) seq.headOption" -> ("              " + seq.headOption.toString),
        "b) seq.tail.tail.tail.tail.tail" -> "java.lang.UnsupportedOperationException: tail of empty list",
        "c) Seq().head" -> "                  java.util.NoSuchElementException: head of empty list",
        "d) Seq().last" -> "                  java.util.NoSuchElementException"
      ),

      """You can also filter elements out of a sequence.
        |
        |  seq.filter(_ > 1)     == Seq(2, 3, 4)
        |  seq.filterNot(_ <= 1) == Seq(1)
        |
        |Filter all elements:
        |  a) less than 5
        |  b) greater than 2
      """.stripMargin -> Map(
        "a) seq.filterNot(_ <= 5)" -> seq.filterNot(_ <= 5).toString,
        "b) seq.filter(_ > 2)" -> ("    " + seq.filter(_ > 2).toString)
      ),

      """Find elements which satisfy a condition. Or check conditions on whole sequences.
        |
        |  seq.exists(_ == 2) == true
        |  seq.forall(_ == 2) == false
        |  seq.find(_ == 2)   == Some(2)
        |  seq.containts(2)   == true
        |
        |Proof the following conditions:
        |  a) is every number even in seq
        |  b) find first 3
      """.stripMargin -> Map(
        "a) seq.forall(_ % 2 == 0)" -> seq.forall(_ % 2 == 0).toString,
        "b) seq.find(3)" -> ("           " + seq.find(_ == 3))
      ),

      """Apply a new order.
        |
        |  seq.sortWith(_ > _) == Seq(4, 3, 2, 1)
      """.stripMargin -> Map(),

      """Of course, you can also add elements as needed.
        |
        |  0 +: seq         == Seq(0, 1, 2, 3, 4)
        |  seq :+ 5         == Seq(1, 2, 3, 4, 5)
        |  seq ++ Seq(5, 6) == Seq(1, 2, 3, 4, 5, 6)
        |
        |Add the following elements to seq:
        |  a) -1 at the head and 42 at the end
        |  b) append a sequence with the number 3.0 and 4.0
      """.stripMargin -> Map(
        "a) -1 +: seq :+ 42" -> ("     " + (-1 +: seq :+ 42)),
        "b) seq ++ Seq(3.0, 4.0)" -> (seq ++ Seq(3.0, 4.0)).toString
      ),

      """You can create sub-sequences.
        |
        |  seq.take(2) == Seq(1, 2)
        |  seq.drop(2) == Seq(3, 4)
        |  seq.splitAt(2) == (Seq(1, 2), Seq(3, 4))
        |
        |Create the following sub-sequences:
        |  a) take the five elements
        |  b) drop the first five elements
        |  c) split seq at position five
      """.stripMargin -> Map(
        "a) seq.take(5)" -> ("   " + seq.take(5)),
        "b) seq.drop(5)" -> ("   " + seq.drop(5)),
        "c) seq.splitAt(5)" -> seq.splitAt(5).toString
      ),

      """Tranform your data.
        |
        |  seq.map(_ + 1)              == Seq(2, 3, 4, 5)
        |  seq.flatMap(a => Seq(a, a)) == Seq(1, 1, 2, 2, 3, 3, 4, 4)
        |
        |Your task is to:
        |  a) transform every element to the result of the is-even test
        |  b) create from every a sequence of 3 elements (use map and flatten,
        |     and flatMap)
      """.stripMargin -> Map(
        "a) seq.map(_ % 2 == 0)" -> ("               " + seq.map(_ % 2 == 0)),
        "b) seq.map(a => Seq(a, a, a)).flatten" -> seq.map(a => Seq(a, a, a)).flatten.toString,
        "b) seq.flatMap(a => Seq(a, a, a))" -> ("    " + seq.flatMap(a => Seq(a, a, a)))
      )
    ))

    session.next()
  }
}
