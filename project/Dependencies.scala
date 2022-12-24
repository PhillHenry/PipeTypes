import sbt._

object Dependencies {
	lazy val cats  = "org.typelevel" %% "cats-core" % "2.6.1"
	lazy val refined = "eu.timepit" %% "refined" % "0.9.28"
	lazy val munit = "org.scalameta" %% "munit" % "0.7.26" % Test
	lazy val iron = "io.github.iltotore" %% "iron" % "2.0.0-RC1-21-38acc8-DIRTY12775d8a-SNAPSHOT"
//	lazy val iron_numeric = "io.github.iltotore" %% "iron-numeric" % "1.2-1.0.0"
	lazy val scalacheck = "org.scalacheck" %% "scalacheck" % "1.15.4"


	lazy val compileDeps = Seq(cats, refined, iron) //, iron_numeric)
	lazy val testDeps    = Seq(munit, scalacheck)
}
