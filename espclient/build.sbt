name := "espclient"

organization := "com.example"

version := "0.0.1"

scalaVersion := "2.11.5"

val json4sVersion = "3.2.10"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-native" % json4sVersion,
  "org.json4s" %% "json4s-ext" % json4sVersion,
  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "org.apache.httpcomponents" % "httpclient" % "4.5",
  "org.apache.httpcomponents" % "fluent-hc" % "4.5",
  "org.apache.commons" % "commons-lang3" % "3.4",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test"
)

initialCommands := "import com.example.espclient._"

