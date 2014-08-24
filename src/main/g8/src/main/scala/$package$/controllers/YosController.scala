import org.scalatra._
// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}
// JSON handling support from Scalatra
import org.scalatra.json._
import scala.reflect.runtime.universe._
import com.github.nscala_time.time.Imports._

// Futures with Akka
import _root_.akka.dispatch._
import _root_.akka.actor.ActorSystem
import org.scalatra.FutureSupport
import scala.concurrent.{ExecutionContext, Future, Promise}


class YosController(system: ActorSystem)
  extends ScalatraServlet
  with MethodOverride
  with JacksonJsonSupport
  with FutureSupport {
  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  // set the execution context for actors
  protected implicit def executor: ExecutionContext = system.dispatcher

  before() {
    contentType = formats("json")
  }

  /** ROUTES && ACTIONS */

  get("/?") {
    new AsyncResult { val is =
      Future {
        formatResponse(new Yo(id = 1998, greeting = "Yo! Welcome to Cork!"))
      }
    }
  }

  // format responses into object-type, timestamp, and data
  object formatResponse {
    def apply[T: TypeTag](content: T) = {
      val dt = new DateTime

      Map(
        "object-type" -> objectType(content),
        "timestamp"   -> dt.toString(),
        "data"        -> content
      )
    }

    def objectType[T: TypeTag](obj: T): String = {
      typeOf[T].toString
    }
  }
}
