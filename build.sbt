import play.Project._

name := "testapp"

version := "1.0"

playScalaSettings

ivyConfigurations += config("native").hide

// use a dummy local file for testing
libraryDependencies += "org.somewhere" % "libs" % "1.0" % "native" from ("file://" + sys.props("user.dir") + "/libs.tgz")

mappings in Universal ++= update.value.select(configurationFilter("native")) pair flatRebase("nativeLibs")

NativePackagerKeys.bashScriptExtraDefines += """declare -r native_lib_dir="$(realpath "${app_home}/../nativeLibs")" """

NativePackagerKeys.bashScriptExtraDefines += """addJava "-Djava.library.path=${native_lib_dir}" """
