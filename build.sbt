ThisBuild / version := "1.0"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "linux-kernel-coding-style"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test"