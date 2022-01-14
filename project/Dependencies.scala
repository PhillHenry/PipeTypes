import sbt._

object Dependencies {
	lazy val cats  = "org.typelevel" %% "cats-core" % "2.6.1"
	lazy val refined = "eu.timepit" %% "refined" % "0.9.28"
	lazy val munit = "org.scalameta" %% "munit" % "0.7.26" % Test
	lazy val iron = "io.github.iltotore" %% "iron" % "1.2.0"
	lazy val iron_numeric = "io.github.iltotore" %% "iron-numeric" % "1.2-1.0.0"

	lazy val compileDeps = Seq(cats, refined, iron, iron_numeric)
	lazy val testDeps    = Seq(munit)
}
