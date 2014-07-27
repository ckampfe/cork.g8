import org.scalatra._
// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}
// JSON handling support from Scalatra
import org.scalatra.json._
import scala.reflect.runtime.universe._
import com.github.nscala_time.time.Imports._

// Futures with Akka
import _root_.akka.dispatch._
import org.scalatra.FutureSupport


class SmilesController extends ScalatraServlet with JacksonJsonSupport {
  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  /** ROUTES && ACTIONS */

  get("/?") { // show all smiles
    val smilesCollection = Smiles.getAll
    formatResponse(smilesCollection)
  }

  get("/:id") { // get a smile
    val smile = Smiles.getOne(params("id"))
    formatResponse(smile)
  }

  post("/?") { // create a smile
    Smiles.create(params("kind"), params("size"))
    Created()
  }

  put("/:id") { // update a smile
    Smiles.update(params)
    Ok()
  }

  delete("/:id") { // destroy a smile
    Smiles.destroy(params("id"))
    NoContent()
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
