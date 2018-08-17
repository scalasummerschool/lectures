
val reactV = "16.2.0"

lazy val common = Seq(
  version      := "DEVELOP",
  scalaVersion := "2.12.6",

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
  .aggregate(shared, `scala-101`)

lazy val shared = project
  .in(file("shared"))
  .enablePlugins(ScalaJSPlugin)
  .settings(common)

lazy val `scala-101` = project
  .in(file("lecture1_scala_101"))
  .enablePlugins(ScalaJSPlugin)
  .settings(common)
  .settings(
    name := "scala101"
  )
  .dependsOn(shared)
