import sbt._
import sbt.Keys._
import scalafix.sbt.ScalafixPlugin.autoImport._

object Settings {

  private def crossPlugin(x: sbt.librarymanagement.ModuleID) = compilerPlugin(x.cross(CrossVersion.full))

  val compilerPlugins: Seq[sbt.librarymanagement.ModuleID] = Seq(
    crossPlugin("org.typelevel"    %% "kind-projector"     % "0.11.0"),
    crossPlugin("com.github.cb372" %% "scala-typed-holes"  % "0.1.5"),
    compilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1")
  )

  val scalaFixSettings: Seq[Def.Setting[_]] = Seq(
    scalafixScalaBinaryVersion := "2.13",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )

  val commonSettings: Seq[Def.Setting[_]] = Seq[Def.Setting[_]](
    scalacOptions -= "-Xfatal-warnings",
    Test / parallelExecution := false,
    testFrameworks += new TestFramework("munit.Framework")
  )

}