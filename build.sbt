import Common._

ThisBuild / resolvers := Resolver.sonatypeOssRepos("snapshots")

val scala3Version = "3.2.1"
lazy val root = (project in file("."))
  .commonSettings("pipetypes", "0.1.0")
  .settings(
    libraryDependencies ++= Dependencies.compileDeps ++ Dependencies.testDeps
  )


lazy val docs = project       // new documentation project
  .in(file("dox"))       // important: it must not be docs/
  .dependsOn(root)
  .enablePlugins(MdocPlugin)
  .settings(
    mdocIn := file("mdocs"),
    scalaVersion := scala3Version,
  )
