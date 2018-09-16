package exercise3

/** Write your solutions in the test functions.
  * 
  * https://www.scala-lang.org/api/2.12.3/scala/collection/immutable/StringOps.html
  */
object Strings {

  // a) Map all Chars into upper case (don't use capitalize).
  def testUppercase(str: String): String = ""

  /* b) Interpolate the following values in the String:
   *       Hi my name is <name> and I am <age> years old.
   */
  def testInterpolations(name: String, age: Int): String = ""

  /* c) Add two numbers as interpolation in the following multiline String:
   *       Hi,
   *       now follows a quite hard calculation. We try to add:
   *         a := <value of a>
   *         b := <value of b>
   * 
   *         result is <a + b>
   */
  def testComputation(a: Int, b: Int): String = ""

  // d) Has the String length two? If so return the first two Chars as String otherwise the whole String.
  def testTakeTwo(str: String): String = ""
}
