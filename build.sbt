name := "testtask"

version := "1.0"

scalaVersion := "2.11.11"

import scala.language.postfixOps


libraryDependencies ++= {
  val specsVersion = "3.8.9"

  val testing = Seq(
    "org.specs2"                  %% "specs2-core"                % specsVersion,
    "org.specs2"                  %% "specs2-mock"                % specsVersion
  ) map {_ % "test"}

  val parser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"
  testing ++ Seq(parser)

}


        