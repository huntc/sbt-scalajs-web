sbtPlugin := true

organization := "com.typesafe.sbt"
name := "sbt-scalajs-web"

scalaVersion := "2.10.4"

resolvers += Classpaths.sbtPluginSnapshots

addSbtPlugin("org.scala-lang.modules.scalajs" % "scalajs-sbt-plugin" % "0.5.5")
addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.1.1")

publishMavenStyle := false
publishTo := {
  if (isSnapshot.value) Some(Classpaths.sbtPluginSnapshots)
  else Some(Classpaths.sbtPluginReleases)
}

scriptedSettings
scriptedLaunchOpts += ("-Dproject.version=" + version.value)
