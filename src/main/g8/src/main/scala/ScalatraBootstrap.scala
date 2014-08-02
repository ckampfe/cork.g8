import _root_.akka.actor.{Props, ActorSystem}
import $package$._
import org.scalatra._
import javax.servlet.ServletContext
import com.$name;format="snake"$.config.DatabaseConnector

class ScalatraBootstrap extends LifeCycle with DatabaseConnector {

  val actorSystem = ActorSystem()
  // val myActor = actorSystem.actorOf(Props[MyActor])

  override def init(context: ServletContext) {
    // EXAMPLE ROUTE:
    context.mount(new SmilesController(actorSystem), "/smiles/*")
  }

  override def destroy(context: ServletContext) {
    actorSystem.shutdown()
  }
}
