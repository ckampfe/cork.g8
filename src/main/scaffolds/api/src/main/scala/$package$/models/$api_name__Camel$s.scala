import org.scalatra._
import scalikejdbc._, SQLInterpolation._
import com.github.nscala_time.time.Imports._


case class $api_name;format="Camel"$s(
  id: Int,
  kind: String,
  size: String,
  createdAt: DateTime,
  updatedAt: DateTime
)

object $api_name;format="Camel"$s extends SQLSyntaxSupport[$api_name;format="Camel"$s] {
  implicit val session = AutoSession
  val s = $api_name;format="Camel"$s.syntax("s")

  def getAll: List[Map[String, Any]] = withSQL {
    select.from($api_name;format="Camel"$s as s)
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
    select.from($api_name;format="Camel"$s as s)
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
    val c = $api_name;format="Camel"$s.column
    insert
      .into($api_name;format="Camel"$s)
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
    val c = $api_name;format="Camel"$s.column
    QueryDSL.update($api_name;format="Camel"$s).set(
      c.kind      -> params("kind"),
      c.size      -> params("size"),
      c.updatedAt -> DateTime.now
    ).where.eq($api_name;format="Camel"$s.column.id, params("id"))
  }
  .update()
  .apply()

  def destroy(id: Any): Unit = withSQL {
    delete.from($api_name;format="Camel"$s).where.eq($api_name;format="Camel"$s.column.id, id)
  }
  .update
  .apply()
}
