import _root_.akka.actor.{Props, ActorSystem}
import $package$._
import org.scalatra._
import javax.servlet.ServletContext
import environment._
import databaseConnector._
import org.flywaydb.core.Flyway

class ScalatraBootstrap extends LifeCycle with DatabaseConnector {
  /* run db migrations on application start */
  val flyway = new Flyway
  flyway.setDataSource(Env.Db.url, Env.Db.user, Env.Db.password)
  flyway.migrate

  /* initialize akka actors */
  val actorSystem = ActorSystem()
  // val myActor = actorSystem.actorOf(Props[MyActor])

  /* mount routes */
  override def init(context: ServletContext) {
    // EXAMPLE ROUTE:
    context.mount(new SmilesController(actorSystem), "/smiles/*")
  }

  /* destroy akka actors on shutdown */
  override def destroy(context: ServletContext) {
    actorSystem.shutdown()
  }
}
