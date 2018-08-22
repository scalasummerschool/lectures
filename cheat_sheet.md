## Scala Cheat Sheet
This document gives you an overview of the basic building blocks of Scala and how to use them.

### Functions
#### Definition Style
Curried:

```Scala
def plus(a: Int)(b: Int): Int = a + b

// apply first parameter returns a function which expects the second parameter
val plus1: Int => Int = plus(1)

plus1(2) == 3
```

Uncurried:

```Scala
def plus(a: Int, b: Int): Int = a + b

// apply all parameters at once
plus(1, 2) == 3
```

#### Value Style
Curried:

```Scala
val plus: Int => Int => Int = a => b => a + b

// apply first parameter returns a function which expects the second parameter
val plus1: Int => Int = plus(1)

plus1(2) == 3
```

Uncurried:

```Scala
val plus: (Int, Int) => Int = (a, b) => a + b

// apply all parameters at once
plus(1, 2) == 3
```

Underscore:

```Scala
val plus: (Int, Int) => Int = _ + _

// apply all parameters at once
plus(1, 2) == 3
```

### Case Classes
#### Plain Data Type
```Scala
case class Person(firstName: String, lastName: String, birthyear: Int)
```

#### With Methods
```Scala
case class Person(firstName: String, lastName: String, birthyear: Int) {

  def age(year: Int): Int = year - birthyear
}
```

### Traits
#### Single Type for many Data Types
```Scala
sealed trait Person

case class Teacher(name: String) extends Person
case class Student(name: String, degree: Int) extends Person
```

#### With common properties and methods
```Scala
sealed trait Person {

  val name: String
}

case class Teacher(name: String) extends Person
case class Student(name: String, degree: Int) extends Person
``` 
