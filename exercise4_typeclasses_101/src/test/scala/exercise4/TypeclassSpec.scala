package exercise4

import org.scalacheck.Prop._
import org.scalacheck.{Gen, Properties}

object TypeclassSpec extends Properties("Map") {
  property("string reverse") = forAll { string: String =>
    TypeclassSolution.testReversableString(string) ?= Typeclasses.testReversableString(string)
  }

  property("smash int") = forAll { inp: (Int, Int) =>
    TypeclassSolution.testSmashInt(inp._1, inp._2) ?= Typeclasses.testSmashInt(inp._1, inp._2)
  }

  property("smash double") = forAll { inp: (Double, Double) =>
    TypeclassSolution.testSmashDouble(inp._1, inp._2) ?= Typeclasses.testSmashDouble(inp._1, inp._2)
  }

  property("smash string") = forAll { inp: (String, String) =>
    TypeclassSolution.testSmashString(inp._1, inp._2) ?= Typeclasses.testSmashString(inp._1, inp._2)
  }
}
