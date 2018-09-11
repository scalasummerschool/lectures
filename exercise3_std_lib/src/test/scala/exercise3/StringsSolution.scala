package exercise3

object StringsSolution {

  def uppercase(str: String): String = str.map(_.toUpper)

  def interpolations(name: String, age: Int) = s"Hi my name is $name and I am $age years old."

  def computation(a: Int, b: Int): String =
    """
      |Hi,
      |now follows a quite hard calculation. We try to add:
      |  a := $a
      |  b := $b
      |
      |  result is ${a + b}
    """.stripMargin

  def takeTwo(str: String): String = 
    if (str.length >= 2) str.take(2)
    else                 str
}
