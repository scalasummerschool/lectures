
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

lazy val root = project
  .in(file("."))
  .aggregate(
    `lectures-shared`, `exercises-shared`, 
    `scala101-lecture`, `scala101-exercises`,
    `fp101-lecture`, `fp101-exercises`,
    `std-lib-lecture`
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
    lectureCommon
  )
  .settings(
    name := "scala101-lecture"
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
    lectureCommon
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
    lectureCommon
  )
  .settings(
    name := "std-lib-lecture"
  )
  .dependsOn(`lectures-shared`)
