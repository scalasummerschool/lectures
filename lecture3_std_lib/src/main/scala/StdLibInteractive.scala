
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

  val tup = (1, true, ("hello", 3.0))

  def tuples: Unit = {
    session = new Session(List(
      """Tuples are a basic collection type in Scala which can have two or more values of different types.
        |
        |  (1, "hello")
        |  (true, 1, (5.0, Some(4)))
        |
        |There is also a special operation to create two element tuples (pairs):
        |
        |  1 -> "hello" == (1, "hello")
        |
        |Your task is to:
        |  a) create a tuple from the numbers 1, 2, 3 with ->
      """.stripMargin -> Map(
        "a) 1 -> 2 -> 3" -> (1 -> 2 -> 3).toString
      ),

      """From now on we are using `val tup = (1, true, ("hello", 3.0))`. You can access elements by using index specific methods.
        |
        |  tup._1 == 1
        |  tup._2 == true
        |
        |If you work with pairs - a tuple with two elements - you can also swap their elements.
        |
        |  (1, true).swap == (true, 1)
        |
        |Your task is to:
        |  a) access "hello" in tup using the access methods
        |  b) swap the inner pair using swap and copy
      """.stripMargin -> Map(
        "a) tup._3._1" -> tup._3._1,
        "b) tup.copy(_3 = tup.swap)" -> (tup.copy(_3 = tup._3.swap)).toString
      ),

      """And of course you can pattern-match tuples.
        |
        |  tup match {
        |    case (a, b, (c, d)) => ???
        |  }
      """.stripMargin -> Map(),

      """The next session is 'sequence'.""" -> Map()
    ))

    session.next()
  }

  val seq = Seq(1, 2, 3, 4)

  def sequence: Unit = {
    session = new Session(List(
      """A sequence is a collection of values with an order and in Scala it is the most basic collection trait you can use.
        |When using it it defaults to the List implementation, but more to that in the next session.
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
      ),

      """Transform the whole sequence to something else. Make it an Int, tuple, or whatever you need.
        |
        |  seq.foldLeft(0)((agg, a) => agg + a)  == 0 + 1 + 2 + 3 + 4 == 10
        |  seq.foldRight(0)((a, agg) => agg + a) == 0 + 4 + 3 + 2 + 1 == 10
        |
        |What happens is, you create an aggregator (agg) of your result type with some initial state. Then
        |you go through the whole sequence and start to combine the aggregator with each element.
        |
        |Your task is to:
        |  a) create a tuple with all even numbers in _1 and all odd numbers in _2
        |  b) apply (agg + a) / 2.0 to foldLeft and foldRight and check if the results are the same
      """.stripMargin -> Map(
        """a) seq.foldLeft((Seq.empty[Int], Seq.empty[Int])) { case ((even, odd), a) => 
          |       if (a % 2 == 0) (even :+ a, odd) else (even, odd :+ a)
          |     }""".stripMargin -> (seq.foldLeft((Seq.empty[Int], Seq.empty[Int])) { case ((even, odd), a) => 
          if (a % 2 == 0) (even :+ a, odd) else (even, odd :+ a)
        }).toString,
        "b) seq.foldLeft(0.0)((agg, a) => (agg + a) / 2.0) == seq.foldRight(0.0)((agg, a) => (agg + a) / 2.0)" -> (
          seq.foldLeft(0.0)((agg, a) => (agg + a) / 2.0) == seq.foldRight(0.0)((agg, a) => (agg + a) / 2.0)
        ).toString
      ),

      """We talked about pattern-matching a while now and of course you can also use it with sequences.
        |
        |  seq match {
        |    case h +: t       => ???
        |    case Nil          => ???
        |    case Seq(a, b, c) => ???
        |  }
        |
      """.stripMargin -> Map(),

      """And we get an extension to the for-comprehension. With sequences we can also add a filter step.
        |
        |  val x = for {
        |    a <- seq
        |    
        |    if a > 1
        |  } yield a + 1
        |
        |  x == Seq(3, 4, 5)
      """.stripMargin -> Map(),

      """For more information and a complete set of methods take a look at:
        |
        |  https://www.scala-lang.org/api/2.12.6/scala/collection/Seq.html
        |
        |The next session is 'commonCollections'.
      """ -> Map()
    ))

    session.next()
  }

  val set = Set(1, 2)
  val map = Map(1 -> "hello", 2 -> "world")

  def commonCollections: Unit = {
    session = new Session(List(
      """As mentioned in the sequence-session Scala defaults to the List implementation of the Seq trait. But is there anything special
        |to it we have to know? It seems there is.
        |
        |  // create a List
        |  List(1, 2) == Seq(1, 2)
        |
        |  // adding elements
        |  0 :: list           == 0 +: seq
        |  List(1, 2) ::: list == Seq(1, 2) ++ seq
        | 
        |All Seq operations still apply to List.
      """.stripMargin -> Map(),

      """But then you also have more spcecialized implementations like Vector.
        |
        |  Vector(1, 2) == Seq(1, 2)
        |
        |This data structure gives you constant random access and update times. So if you need to access elements 
        |randomly - read not just the head - you probably want to use Vectors.
        |
        |  val vec = Vector(1, 2, 3)
        |
        |  vec(2) == 3
        |
        |Again all Seq methods apply. You just have to be aware that Nil is not part of Vectors type.
        |For more information take a look at:
        |
        |  https://www.scala-lang.org/api/2.12.6/scala/collection/immutable/Vector.html
      """.stripMargin -> Map(),

      """If you have to make sure each element only exists ones in your collection you should use Set.
        |
        |  Set(1, 2, 2) == Set(1, 2) != Seq(1, 2)
        |  Set(1, 2)                 == Seq(1, 2)
      """.stripMargin -> Map(),

      """From no on we are using `val set = Set(1, 2)`. But Set comes with some special methods.
        |
        |  // apply is not getting an element, but proofing its existence
        |  set(1) == set.contains(1)
        |
        |  // standard set operations
        |  set union Set(2, 3)     == Set(1, 2, 3)
        |  set diff Set(2, 3)      == Set(1)
        |  set intersect Set(2, 3) == Set(2)
        |
        |Your task is to:
        |  a) symetric difference (all elements which are only in A xor B) betwenn set and Set(2, 3)
      """.stripMargin -> Map(
        "a) set.diff(Set(2, 3)).union(Set(2, 3).diff(set))" -> (set.diff(Set(2, 3)).union(Set(2, 3).diff(set))).toString
      ),

      """But be aware that Set is not implementing Seq. Many of the methods still exists but some don't. For example append/prepend
        |methods boil down to the following:
        |
        |  set + 3       == Set(1, 2, 3)
        |  Set(0) ++ set == Set(0, 1, 2)
        |
        |You also lose any guarantees about order. A set does not have an order. But there is SortedSet if you need it.
      """.stripMargin -> Map(),

      """Again you can find more information when looking into:
        |
        |  https://www.scala-lang.org/api/2.12.6/scala/collection/immutable/Set.html
      """.stripMargin -> Map(),

      """And then, there is a construct called Map which is a collection of key-value pairs where the keys create a set.
        |
        |  Map(1 -> "hello", 2 -> "world")
        |
        |We continue to work with `val map = Map(1 -> "hello", 2 -> "world")`. You can access pairs by their key:
        |
        |  map(1)                   == "hello"
        |  map.get(1)               == Some("hello")
        |  map.getOrElse(1, "boom") == "hello"
        |
        |Your task is to:
        |  a) get the pair with key 3 using apply
        |  b) get the pair with key 3 or return "boom"
      """.stripMargin -> Map(
        "a) map(3)" -> "                   java.util.NoSuchElementException: key not found: 3",
        "b) map.getOrElse(2, \"boom\")" -> map.getOrElse(2, "boom")
      ),

      """You can also access the keys or values of a Map.
        |
        |  map.keys
        |  map.values
        |
        |What you get back is a mutable data structure which allows you to use the known transformation
        |operations. But be aware that it is mutable. Try to avoid to share the return objects.
        |
        |At least for keys you can restrict yourself to sets.
        |
        |  map.keySet == Set(1)
      """.stripMargin -> Map()
    ))

    session.next()
  }
}
