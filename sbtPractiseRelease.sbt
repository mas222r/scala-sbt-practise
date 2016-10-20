import complete._
import DefaultParsers._

def releaseParser(state:State):Parser[String] = ID

def releaseAction(state:State,version:String):State = {
"checkNoLocalChanges" ::
("all test integrationTests" ::
s"git tag ${version}" ::
"reload" ::
"publish" ::
state)
}
val ReleaseCommand = Command.apply("sbtPractiseRelease")(releaseParser)(releaseAction)
commands +=ReleaseCommand

lazy val checkNoLocalChanges = taskKey[Unit]("checks to see if we have local git changes.Fails if we do.")
checkNoLocalChanges := {
val dir = baseDirectory.value
val changes = Process("git diff-index --name-only HEAD --",dir) !! streams.value.log
if(!changes.isEmpty){
val changeMsg = changes.split("[\r\n]+").mkString(" - ","\n - ","\n")
sys.error("Git changes were found: \n" + changeMsg)
}
}