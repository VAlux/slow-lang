val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "sox",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    scalafmtOnCompile := true,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
