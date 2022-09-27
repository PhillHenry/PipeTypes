import Common._

lazy val root = (project in file("."))
  .commonSettings("pipetypes", "0.1.0")
  .settings(
    libraryDependencies ++= Dependencies.compileDeps ++ Dependencies.testDeps
  )

val scala3Version = "3.2.0"
lazy val docs = project       // new documentation project
  .in(file("dox"))       // important: it must not be docs/
  .dependsOn(root)
  .enablePlugins(MdocPlugin)
  .settings(
    mdocIn := file("mdocs"),
    scalaVersion := scala3Version,
  )

