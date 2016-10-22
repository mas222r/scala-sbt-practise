lazy val hello = taskKey[Unit]("Prints 'Hello World'")
hello := println("hello world!")

lazy val gitHeadCommitSha = settingKey[Option[String]]("Determines the current git commit SHA")
gitHeadCommitSha := { 
try {
Some(Process("git rev-parse HEAD").lines.head)
} catch {
case _: Exception => None
}
}

lazy val commonSettings = Seq(
  organization := "Arshan IT pvt ltd",
  version := "1.0-",
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

mainClass := Some("some.com")
packageArchetype.java_server