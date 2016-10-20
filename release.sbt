val ReleaseCommand = Command.command("release"){
state => 
"all clean compile" :: "test" :: "package" :: state


}
commands +=ReleaseCommand