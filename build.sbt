import Build._

lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging).
  settings(
    name := "finatra-freemarker-seed",
    organization := "com.example",
    scalaVersion := "2.12.2",
    mainClass := Some("com.example.HelloServerMain"),
    resolvers += "my_repo" at "https://dl.bintray.com/yafengli/maven/",
    libraryDependencies ++= Seq(
      "greatbit" %% "finatra-freemarker" % $("finatra"),
      "org.freemarker" % "freemarker" % $("freemarker"),      
      "com.twitter" %% "finatra-http" % $("finatra") % "test" classifier "tests",
      "org.scalatest" %% "scalatest" % $("scalatest") % "test")
  )
