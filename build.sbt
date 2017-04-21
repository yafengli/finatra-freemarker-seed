import Build._

assemblyMergeStrategy in assembly := {
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case PathList("javax", "servlet", _@_*) => MergeStrategy.last
  case PathList(ps@_*) if ps.last endsWith ".class" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.first
  case "application.conf" => MergeStrategy.first
  case "unwanted.txt" | "changelog.txt" | "BUILD" => MergeStrategy.discard
  case x if x.endsWith("spring.factories") => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

test in assembly := {}

lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.8"
    )),
    name := "hello",
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
