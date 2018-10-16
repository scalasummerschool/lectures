package exercise3

/** Write your solutions in the test functions.
  * 
  * https://www.scala-lang.org/api/2.12.3/scala/collection/immutable/StringOps.html
  */
object Strings {

  /* a) Map all Chars into upper case (don't use capitalize).
   *    You can implement your solution directly in the test-function. DO NOT change the signature.
   */
  def testUppercase(str: String): String = ""

  /* b) Interpolate the following values in the String:
   *       Hi my name is <name> and I am <age> years old.
   *    You can implement your solution directly in the test-function. DO NOT change the signature.
   */
  def testInterpolations(name: String, age: Int): String = ""

  /* c) Add two numbers as interpolation in the following multiline String:
   *       Hi,
   *       now follows a quite hard calculation. We try to add:
   *         a := <value of a>
   *         b := <value of b>
   * 
   *         result is <a + b>
   * 
   *   You can implement your solution directly in the test-function. DO NOT change the signature.
   */
  def testComputation(a: Int, b: Int): String = ""

  /* d) Has the String length two? If so return the first two Chars as String otherwise the whole String.
   *    You can implement your solution directly in the test-function. DO NOT change the signature.
   */
  def testTakeTwo(str: String): String = ""
}
