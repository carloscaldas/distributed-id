scalaVersion := "2.12.10"

name := "distributed-unique-id"
organization := "com.carloscaldas"
version := "0.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"

homepage := Some(url("https://github.com/carloscaldas/distributed-id"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/carloscaldas/distributed-id"),
    "git@github.com:carloscaldas/distributed-id.git"
  )
)
developers := List(Developer("carloscaldas",
  "Carlos Caldas",
  "ccaldas@gmail.com",
  url("https://github.com/carloscaldas")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
publishMavenStyle := true

// Add sonatype repository settings
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)
