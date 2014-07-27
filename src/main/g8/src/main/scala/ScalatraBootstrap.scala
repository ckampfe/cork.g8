import _root_.akka.actor.{Props, ActorSystem}
// import com.$name;format="lower"$.app._
import $package$._
import org.scalatra._
import javax.servlet.ServletContext
import com.$name;format="snake"$.config.DatabaseConnector

class ScalatraBootstrap extends LifeCycle with DatabaseConnector {

  val system = ActorSystem(actorSystemName)
  // val myActor = system.actorOf(Props[MyActor])

  override def init(context: ServletContext) {
    context.mount(new $servlet_name$, "/*")
    context.mount(new SmilesController, "/smiles/*")
  }

  override def destroy(context: ServletContext) {
    system.shutdown()
  }
}
