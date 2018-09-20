
val common = Seq(
  version      := "DEVELOP",
  scalaVersion := "2.12.6"
)

val reactV = "16.2.0"

lazy val lectureCommon = Seq(
  libraryDependencies ++= Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % "1.2.3",
    "org.scala-js" %%% "scalajs-dom" % "0.9.2"
  ),
  jsDependencies ++= Seq(
    "org.webjars.bower" % "react" % "15.2.1" / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
    "org.webjars.bower" % "react" % "15.2.1" / "react-dom.js"         minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM"
  ),

  scalaJSUseMainModuleInitializer := true
)

val copyFast = taskKey[Unit]("Copy fast optimized JS presentation.")

def copyFastImpl(project: String) = Seq(
  copyFast := {
    IO.copyFile(
      target.value / "scala-2.12" / s"$project-fastopt.js",
      baseDirectory.value / "presentation.js"
    )
    IO.copyFile(
      target.value / "scala-2.12" / s"$project-jsdeps.js",
      baseDirectory.value / "jsdeps.js"
    )
  }
)

val copyFull = taskKey[Unit]("Copy fully optimized JS presentation.")

def copyFullImpl(project: String) = Seq(
  copyFull := {
    IO.copyFile(
      target.value / "scala-2.12" / s"$project-opt.js",
      baseDirectory.value / "presentation.js"
    )
    IO.copyFile(
      target.value / "scala-2.12" / s"$project-jsdeps.min.js",
      baseDirectory.value / "jsdeps.js"
    )
  }
)

lazy val root = project
  .in(file("."))
  .aggregate(
    `lectures-shared`, `exercises-shared`,
    `scala101-lecture`, `scala101-exercises`,
    `fp101-lecture`, `fp101-exercises`,
    `std-lib-lecture`, `std-lib-exercises`,
    `typeclasses-101-lecture`, `typeclasses-101-exercises`
  )
  .settings(
    sourceDirectories in Compile := Nil,
    sourceDirectories in Test := Nil
  )

lazy val `lectures-shared` = project
  .in(file("lectures-shared"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    common,
    lectureCommon
  )

lazy val `exercises-shared` = project
  .in(file("exercises-shared"))
  .settings(common)
  .settings(
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"
  )

lazy val `scala101-lecture` = project
  .in(file("lecture1_scala_101"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    common,
    lectureCommon,
    copyFastImpl("scala101-lecture"),
    copyFullImpl("scala101-lecture"),
    addCommandAlias("fastCompile", "; fastOptJS; copyFast"),
    addCommandAlias("fullCompile", "; fullOptJS; copyFull")
  )
  .settings(
    name := "scala101-lecture",
  )
  .dependsOn(`lectures-shared`)

lazy val `scala101-exercises` = project
  .in(file("exercise1_scala_101"))
  .settings(common)
  .settings(
    name := "scala101-exercises"
  )
  .dependsOn(`exercises-shared` % "compile->compile;test->test")

lazy val `fp101-lecture` = project
  .in(file("lecture2_fp_101"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    common,
    lectureCommon,
    copyFastImpl("fp101-lecture"),
    copyFullImpl("fp101-lecture"),
    addCommandAlias("fastCompile", "; fastOptJS; copyFast"),
    addCommandAlias("fullCompile", "; fullOptJS; copyFull")
  )
  .settings(
    name := "fp101-lecture"
  )
  .dependsOn(`lectures-shared`)

lazy val `fp101-exercises` = project
  .in(file("exercise2_fp_101"))
  .settings(common)
  .settings(
    name := "fp101-exercises"
  )
  .dependsOn(`exercises-shared` % "compile->compile;test->test")

lazy val `std-lib-lecture` = project
  .in(file("lecture3_std_lib"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    common,
    lectureCommon,
    copyFastImpl("std-lib-lecture"),
    copyFullImpl("std-lib-lecture"),
    addCommandAlias("fastCompile", "; fastOptJS; copyFast"),
    addCommandAlias("fullCompile", "; fullOptJS; copyFull")
  )
  .settings(
    name := "std-lib-lecture"
  )
  .dependsOn(`lectures-shared`)

lazy val `std-lib-exercises` = project
  .in(file("exercise3_std_lib"))
  .settings(common)
  .settings(
    name := "std-lib-exercises"
  )
  .dependsOn(`exercises-shared` % "compile->compile;test->test")

lazy val `typeclasses-101-lecture` = project
  .in(file("lecture4_typeclasses_101"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    common,
    lectureCommon,
    copyFastImpl("typeclasses-101-lecture"),
    copyFullImpl("typeclasses-101-lecture"),
    addCommandAlias("fastCompile", "; fastOptJS; copyFast"),
    addCommandAlias("fullCompile", "; fullOptJS; copyFull")
  )
  .settings(
    name := "typeclasses-101-lecture"
  )
  .dependsOn(`lectures-shared`)

lazy val `typeclasses-101-exercises` = project
  .in(file("exercise4_typeclasses_101"))
  .settings(common)
  .settings(
    name := "typeclasses-101-exercises"
  )
  .dependsOn(`exercises-shared` % "compile->compile;test->test")

lazy val `typeclasses-incarnations-lecture` = project
  .in(file("lecture5_typeclasses_incarnations"))
  .settings(common)
  .settings(
    name := "typeclasses-incarnations-lecture"
  )
  .dependsOn(`exercises-shared` % "compile->compile;test->test")

lazy val `typeclasses-incarnations-exercises` = project
  .in(file("exercise5_typeclasses_incarnations"))
  .settings(common)
  .settings(
    name := "typeclasses-incarnations-exercises"
  )
  .dependsOn(`exercises-shared` % "compile->compile;test->test")
