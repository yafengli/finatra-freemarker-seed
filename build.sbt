import Build._

lazy val root = (project in file(".")).enablePlugins(SbtDistApp).
  settings(
    name := "hello",
    organization := "com.example",
    scalaVersion := "2.12.1",
    mainClass := Some("com.example.HelloServerMain"),
    libraryDependencies ++= Seq(
      "com.twitter" %% "finatra-freemarker" % $("finatra"),
      "org.freemarker" % "freemarker" % $("freemarker"),      
      "com.twitter" %% "finatra-http" % $("finatra") % "test" classifier "tests",
      "com.twitter" %% "inject-server" % $("finatra") % "test" classifier "tests",
      "com.twitter" %% "inject-app" % $("finatra") % "test" classifier "tests",
      "com.twitter" %% "inject-core" % $("finatra") % "test" classifier "tests",
      "com.twitter" %% "inject-modules" % $("finatra") % "test" classifier "tests",
      "org.mockito" % "mockito-core" % $("mockito") % "test",
      "org.scalatest" %% "scalatest" % $("scalatest") % "test")
  )
