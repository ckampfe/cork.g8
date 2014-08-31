package $package$

import org.scalatra._
import scalikejdbc._, SQLInterpolation._
import com.github.nscala_time.time.Imports._


case class $api_name;format="Camel"$(
  id: Int,
  kind: String,
  size: String,
  createdAt: DateTime,
  updatedAt: DateTime
)

object $api_name;format="Camel"$ extends SQLSyntaxSupport[$api_name;format="Camel"$] {
  implicit val session = AutoSession

  override val tableName = "$api_name;format="lower,snake"$s"

  val s = $api_name;format="Camel"$.syntax("s")

  def getAll: List[Map[String, Any]] = withSQL {
    select.from($api_name;format="Camel"$ as s)
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
    select.from($api_name;format="Camel"$ as s)
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
    val c = $api_name;format="Camel"$.column
    insert
      .into($api_name;format="Camel"$)
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
    val c = $api_name;format="Camel"$.column
    QueryDSL.update($api_name;format="Camel"$).set(
      c.kind      -> params("kind"),
      c.size      -> params("size"),
      c.updatedAt -> DateTime.now
    ).where.eq($api_name;format="Camel"$.column.id, params("id"))
  }
  .update()
  .apply()

  def destroy(id: Any): Unit = withSQL {
    delete.from($api_name;format="Camel"$).where.eq($api_name;format="Camel"$.column.id, id)
  }
  .update
  .apply()
}
