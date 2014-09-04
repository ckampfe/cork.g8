import _root_.akka.actor.{Props, ActorSystem}
import $package$._
import org.scalatra._
import javax.servlet.ServletContext
import environment.Env
import environment.DatabaseConnector
import org.flywaydb.core.Flyway

class ScalatraBootstrap extends LifeCycle with DatabaseConnector {
  /* initial database connection */
  val env = new Env("application")
  init(env)

  /* run db migrations on application start */
  val flyway = new Flyway
  flyway.setDataSource(env.Db.url, env.Db.user, env.Db.password)
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
