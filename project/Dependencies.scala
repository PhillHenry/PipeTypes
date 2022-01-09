import sbt._

object Dependencies {
	lazy val cats  = "org.typelevel" %% "cats-core" % "2.6.1"
	lazy val refined = "eu.timepit" %% "refined" % "0.9.28"
	lazy val munit = "org.scalameta" %% "munit" % "0.7.26" % Test

	lazy val compileDeps = Seq(cats, refined)
	lazy val testDeps    = Seq(munit)
}
