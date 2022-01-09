import Common._

lazy val root = (project in file("."))
  .commonSettings("pipetypes", "0.1.0")
  .settings(
    libraryDependencies ++= Dependencies.compileDeps ++ Dependencies.testDeps
  )

lazy val docs = project
  .in(file("mdocs"))
  .settings(
    mdocIn := file("modules/docs"),
    mdocOut := file("docs"),
    mdocVariables := Map("VERSION" -> version.value),
  )//.dependsOn(root)
   .enablePlugins(MdocPlugin)

