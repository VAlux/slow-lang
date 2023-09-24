val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
<<<<<<< HEAD
    name := "sox",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
=======
    name := "slox",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

>>>>>>> 9221089 (slox lexer POC)
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
