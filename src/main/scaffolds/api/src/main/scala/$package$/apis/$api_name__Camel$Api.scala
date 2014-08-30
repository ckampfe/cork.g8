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


class $api_name;format="Camel"$Api(system: ActorSystem)
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

  get("/?") { // show all $api_name;format="Camel"$s
    new AsyncResult { val is =
      Future {
        val $api_name;format="camel"$sCollection = $api_name$.getAll
        formatResponse($api_name;format="camel"$sCollection)
      }
    }
  }

  get("/:id") { // get a $api_name;format="lower"$
    new AsyncResult { val is =
      Future {
        val $api_name;format="camel"$ = $api_name$.getOne(params("id"))
        formatResponse($api_name;format="camel"$)
      }
    }
  }

  post("/?") { // create a $api_name;format="lower"$
    new AsyncResult { val is =
      Future {
        $api_name$.create(params("kind"), params("size"))
        Created()
      }
    }
  }

  put("/:id") { // update a $api_name;format="lower"$
    new AsyncResult { val is =
      Future {
        $api_name$.update(params)
        Ok()
      }
    }
  }

  delete("/:id") { // destroy a $api_name;format="lower"$
    new AsyncResult { val is =
      Future {
        $api_name$.destroy(params("id"))
        NoContent()
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
