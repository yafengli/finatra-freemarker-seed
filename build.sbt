import Build._

lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging).
  settings(
    name := "finatra-freemarker-seed",
    organization := "com.example",
    scalaVersion := $("scala2"),
    mainClass := Some("com.example.HelloServerMain"),
    libraryDependencies ++= Seq(
      //"greatbit" %% "finatra-freemarker" % $("finatra"),
      "org.freemarker" % "freemarker" % $("freemarker"),
      "com.twitter" %% "finatra-http-server" % $("finatra"),
      "org.scalatest" %% "scalatest" % $("scalatest") % "test")
  )
