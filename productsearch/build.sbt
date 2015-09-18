name := "productsearch"

organization := "com.example"

version := "0.0.1"
scalaVersion := "2.11.5"
json4sVersion := "3.2.10"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-native" % json4sVersion,
  "org.json4s" %% "json4s-ext" % json4sVersion,
  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test"
)

initialCommands := "import com.example.productsearch._"

