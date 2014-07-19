import org.scalatra._
import scalikejdbc._, SQLInterpolation._
import com.github.nscala_time.time.Imports._


case class Smiles(
  id: Int,
  kind: Option[String],
  size: Option[String],
  createdAt: DateTime,
  updatedAt: DateTime
)

object Smiles extends SQLSyntaxSupport[Smiles] {
  implicit val session = AutoSession
  val s = Smiles.syntax("s")

  def getAll: List[Map[String, Any]] = withSQL {
    select.from(Smiles as s)
  }
  .map(
    rs => Map(
      "id"   -> rs.long(s.resultName.id),
      "kind" -> rs.string(s.resultName.kind),
      "size" -> rs.string(s.resultName.size)
    )
  )
  .list()
  .apply()

  def getOne(id: Any): Option[Map[String, Any]] = withSQL {
    select.from(Smiles as s)
      .where.eq(s.id, id)
  }
  .map(
    rs => Map(
      "id"   -> rs.long(s.resultName.id),
      "kind" -> rs.string(s.resultName.kind),
      "size" -> rs.string(s.resultName.size)
    )
  )
  .single()
  .apply()

  def create(kind: String, size: String) = withSQL {
    val c = Smiles.column
    insert
      .into(Smiles)
      .namedValues(
        c.kind      -> kind,
        c.size      -> size,
        c.createdAt -> DateTime.now,
        c.updatedAt -> DateTime.now
      )
  }
  .updateAndReturnGeneratedKey
  .apply()

  def update(params: Map[String, String]) = withSQL {
    val c = Smiles.column
    QueryDSL.update(Smiles).set(
      c.kind      -> params("kind"),
      c.size      -> params("size"),
      c.updatedAt -> DateTime.now
    ).where.eq(Smiles.column.id, params("id"))
  }
  .update()
  .apply()
}
