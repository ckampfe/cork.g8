import org.scalatra._
// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}
// JSON handling support from Scalatra
import org.scalatra.json._
import com.github.nscala_time.time.Imports._
import scala.reflect.runtime.universe._
import scalikejdbc._, SQLInterpolation._


class SmilesController extends ScalatraServlet with JacksonJsonSupport {
  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  implicit val session = AutoSession

  before() {
    contentType = formats("json")
  }

  /** ROUTES && ACTIONS */

  get("/?") {
    // show all smiles
    val smiles: List[Map[String, Any]] = DB readOnly { implicit session =>
      sql"SELECT * FROM smiles"
        .map(
          rs => Map(
            "id"   -> rs.long("id"),
            "kind" -> rs.string("kind"),
            "size" -> rs.string("size")
          )
        )
        .list()
        .apply()
    }

    ResponseFormatter(smiles)
  }

  get("/:id") {
    // get a smile

    val smile: Option[Map[String, Any]] = DB readOnly { implicit session =>
      sql"SELECT * FROM smiles WHERE id = ${params("id")}"
        .map(
          rs => Map(
            "id"   -> rs.long("id"),
            "kind" -> rs.string("kind"),
            "size" -> rs.string("size")
          )
        )
        .single()
        .apply()
    }

    ResponseFormatter(smile)
  }

  post("/?") {
    // create a smile

    val dt = new DateTime
    val smile = Smile(params("kind"), params("size"))

    val objId = sql"""
      INSERT INTO smiles (kind, size, created_at, updated_at)
      VALUES (${smile.kind}, ${smile.size}, ${dt.toString()}, ${dt.toString()});
    """.updateAndReturnGeneratedKey.apply()

    Created()
  }

  put("/:id") {
    // update a smile
  }

  delete(":id") {
    // delete a smile
  }
}


// format responses into object-type, timestamp, and data
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
