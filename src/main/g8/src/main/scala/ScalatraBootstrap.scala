package $package$

import _root_.akka.actor.{Props, ActorSystem}
import org.scalatra._
import javax.servlet.ServletContext
import environment.Env
import environment.DatabaseConnector
import org.flywaydb.core.Flyway
import java.util.Properties

class ScalatraBootstrap extends LifeCycle with DatabaseConnector {
  // initial database connection
  val env = new Env("application")
  init(env)

  val flyway = new Flyway
  val properties = new Properties

  properties.setProperty("flyway.url",      env.Db.url)
  properties.setProperty("flyway.user",     env.Db.user)
  properties.setProperty("flyway.password", env.Db.password)

  // Flyway cannot infer the driver
  // for "postgres" (only "postgresql"), so handle drivers manually
  env.Db.adapter match {
    case "mysql" =>
      properties.setProperty("flyway.driver", "com.mysql.jdbc.Driver")
    case "postgresql" | "postgres" =>
      properties.setProperty("flyway.driver", "org.postgresql.Driver")
    case "h2" =>
      properties.setProperty("flyway.driver", "org.h2.Driver")
  }

  flyway.configure(properties)
  flyway.migrate

  /* initialize akka actors */
  val actorSystem = ActorSystem()
  // val myActor = actorSystem.actorOf(Props[MyActor])

  /* mount routes */
  override def init(context: ServletContext) {
    // EXAMPLE ROUTE:
    context.mount(new YoApi(actorSystem), "/yos/*")
  }

  /* destroy akka actors on shutdown */
  override def destroy(context: ServletContext) {
    actorSystem.shutdown()
  }
}
