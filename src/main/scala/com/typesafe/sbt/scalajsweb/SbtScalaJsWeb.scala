package com.typesafe.sbt.scalajsweb

import com.typesafe.sbt.web.SbtWeb
import sbt._
import sbt.Keys._

object Import {

  object Keys {
    val link = TaskKey[Seq[File]]("sjsw-link", "compile and link together all js")
  }

}

object SbtScalaJsWeb extends AutoPlugin {

  val autoImport = Import

  import autoImport._
  import Keys._

  import SbtWeb.autoImport._
  import WebKeys._

  override def requires = SbtWeb

  override def trigger = AllRequirements

  override def projectSettings: Seq[Setting[_]] =
    inConfig(Assets)(scopedSettings) ++ inConfig(TestAssets)(scopedSettings)

  private def scopedSettings: Seq[Setting[_]] = Seq(
    link := linkTask.value,
    sourceGenerators <+= link
  )

  private def linkTask = Def.task {
    val sourceDir = (sourceDirectory in Assets).value
    val targetDir = webTarget.value / "scalajs-web-plugin"
    val sources = sourceDir ** "*.scala"
    targetDir.mkdirs()
    Seq(targetDir)
  }

}
