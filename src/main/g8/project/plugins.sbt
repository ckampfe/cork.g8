addSbtPlugin("com.mojolly.scalate" % "xsbt-scalate-generator" % "0.5.0")

addSbtPlugin("org.scalatra.sbt" % "scalatra-sbt" % "0.3.5")

addSbtPlugin("net.databinder.giter8" %% "giter8-scaffold" % "0.6.4")

addSbtPlugin("org.flywaydb" % "flyway-sbt" % "3.0")

resolvers += "Flyway" at "http://flywaydb.org/repo"
