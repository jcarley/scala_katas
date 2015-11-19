name := "countfiles"

organization := "com.example"

version := "0.0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.0"
)

initialCommands := "import com.example.countfiles._"

