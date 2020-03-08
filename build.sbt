scalaVersion := "2.13.1"

name := "distributed-unique-id"
organization := "com.github.carloscaldas"
version := "0.1"

crossScalaVersions := Seq("2.12.10")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

resolvers += Resolver.jcenterRepo

homepage := Some(url("http://github.com/carloscaldas/distributed-unique-id"))

scmInfo := Some(
  ScmInfo(
    browseUrl = url("https://github.com/carloscaldas/distributed-unique-id.git"),
    connection = "git@github.com:carloscaldas/distributed-unique-id.git"
  )
)

publishMavenStyle := true

bintrayRepository := "maven"
