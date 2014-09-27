import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.typesafe.sbt.SbtNativePackager

object $name;format="Camel"$Build extends Build {
  val Organization    = "$organization$"
  val Name            = "$name$"
  val Version         = "$version$"
  val ScalaVersion    = "$scala_version$"
  val ScalatraVersion = "$scalatra_version$"

  lazy val project = Project (
    "$name;format="snake"$",
    file("."),
    settings = Defaults.defaultSettings ++
    ScalatraPlugin.scalatraWithJRebel ++
    Seq(giter8.ScaffoldPlugin.scaffoldSettings: _*) ++
    Seq(SbtNativePackager.packageArchetype.java_application: _*) ++
    Seq(
      organization     := Organization,
      name             := Name,
      version          := Version,
      scalaVersion     := ScalaVersion,
      resolvers        += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra"            %% "scalatra"             % ScalatraVersion,
        "org.scalatra"            %% "scalatra-scalatest"   % "2.3.+" % "test",
        "org.scalatra"            %% "scalatra-json"        % "2.3.0",
        "ch.qos.logback"           % "logback-classic"      % "1.0.6" % "runtime",
        "org.json4s"              %% "json4s-jackson"       % "3.2.10",
        "com.github.nscala-time"  %% "nscala-time"          % "1.2.0",
        "org.flywaydb"             % "flyway-core"          % "3.0",
        "org.scalikejdbc"         %% "scalikejdbc"          % "2.0.+",
        "mysql"                    % "mysql-connector-java" % "5.1.31",
        "org.postgresql"           % "postgresql"           % "9.3-1102-jdbc41",
        "com.h2database"           % "h2"                   % "1.4.+",
        "com.zaxxer"               % "HikariCP"             % "2.0.1",
        "ch.qos.logback"           % "logback-classic"      % "1.1.+",
        "com.typesafe.akka"       %% "akka-actor"           % "2.3.4",
        "com.typesafe"             % "config"               % "1.2.+",
        "net.databinder.dispatch" %% "dispatch-core"        % "0.11.1",
        "org.eclipse.jetty"        % "jetty-webapp"         % "9.2.3.v20140905" % "compile;container",
        "org.eclipse.jetty.orbit"  % "javax.servlet"        % "3.0.0.v201112011016" % "compile;container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      )
    )
  )
}
