

lazy val commonSettings = Seq(
  organization := "Arshan it ltd",
  version := "1.0",
  scalaVersion := "2.11.8"
)

lazy val commonexec = (
Project("commonexecute", file("commonexecute")).settings(commonSettings:_*)
)

val scalazVersion = "7.1.0"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

lazy val root = (project in file(".")).settings(commonSettings:_*).settings(name :="scala-sbt-practice-project1",libraryDependencies ++= Seq("junit" % "junit" % "4.11" % "test","org.specs2" %% "specs2-core" % "3.0" % "test", "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test")).dependsOn(commonexec).aggregate(commonexec)

