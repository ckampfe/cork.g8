import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object CorkBuild extends Build {
  val Organization = "$organization$"
  val Name = "$name$"
  val Version = "$version$"
  val ScalaVersion = "$scala_version$"
  val ScalatraVersion = "$scalatra_version$"

  lazy val project = Project (
    "cork",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++
    scalateSettings ++ Seq(giter8.ScaffoldPlugin.scaffoldSettings: _*) ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra"            %% "scalatra"             % ScalatraVersion,
        "org.scalatra"            %% "scalatra-scalate"     % ScalatraVersion,
        "org.scalatra"            %% "scalatra-specs2"      % ScalatraVersion % "test",
        "ch.qos.logback"           % "logback-classic"      % "1.0.6" % "runtime",
        "org.scalatra"            %% "scalatra-json"        % "2.3.0",
        "org.json4s"              %% "json4s-jackson"       % "3.2.10",
        "com.github.nscala-time"  %% "nscala-time"          % "1.2.0",
        "org.scalikejdbc"         %% "scalikejdbc"          % "2.0.+",
        "mysql"                    % "mysql-connector-java" % "5.1.31",
        "com.h2database"           % "h2"                   % "1.4.+",
        "com.zaxxer"               % "HikariCP"             % "2.0.1",
        "ch.qos.logback"           % "logback-classic"      % "1.1.+",
        "com.typesafe.akka"       %% "akka-actor"           % "2.3.4",
        "net.databinder.dispatch" %% "dispatch-core"        % "0.11.1",
        "org.eclipse.jetty"        % "jetty-webapp"         % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit"  % "javax.servlet"        % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      )
    )
  )
}
