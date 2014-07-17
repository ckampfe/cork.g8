import org.scalatra._
// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}
// JSON handling support from Scalatra
import org.scalatra.json._
import com.github.nscala_time.time.Imports._
import scala.reflect.runtime.universe._

class SmilesController extends ScalatraServlet with JacksonJsonSupport {
  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  /** ROUTES && ACTIONS */

  get("/?") {
    ResponseFormatter(Smiles.all)
  }

  get("/:id") {
    val id = params("id")
    id.toInt match {
      case id if id >= 5 => Ok("Great! " + id + " is greater than 5")
      case id if id < 5  => BadRequest("Sorry, " + id + " is less than 5...")
      case _ => BadRequest("Sorry, I didn't quite get that. Try a number.")
    }
  }

  post("/?") {
    // create a smile
  }

  put("/:id") {
    // update a smile
  }

  delete(":id") {
    // delete a smile
  }
}



object ResponseFormatter {
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

object Smiles {
  val all = List(
      Smile("grin", "shit-eating"),
      Smile("smirk", "sly"),
      Smile("school picture", "huge"))
}
