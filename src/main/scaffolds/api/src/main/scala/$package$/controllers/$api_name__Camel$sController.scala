import org.scalatra._
// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}
// JSON handling support from Scalatra
import org.scalatra.json._
import scala.reflect.runtime.universe._
import com.github.nscala_time.time.Imports._


class $api_name;format="Camel"$sController extends ScalatraServlet with JacksonJsonSupport {
  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  /** ROUTES && ACTIONS */

  get("/?") { // show all $api_name;format="Camel"$s
    val $api_name;format="camel"$sCollection = $api_name$s.getAll
    formatResponse($api_name;format="camel"$sCollection)
  }

  get("/:id") { // get a $api_name;format="lower"$
    val $api_name;format="camel"$ = $api_name$s.getOne(params("id"))
    formatResponse($api_name;format="camel"$)
  }

  post("/?") { // create a $api_name;format="lower"$
    $api_name$s.create(params("kind"), params("size"))
    Created()
  }

  put("/:id") { // update a $api_name;format="lower"$
    $api_name$s.update(params)
    Ok()
  }

  delete("/:id") { // destroy a $api_name;format="lower"$
    $api_name$s.destroy(params("id"))
    NoContent()
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


