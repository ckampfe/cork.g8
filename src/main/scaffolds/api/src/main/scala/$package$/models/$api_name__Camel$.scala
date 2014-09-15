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

  def all(): List[$api_name;format="Camel"$Representer] = withSQL {
    select.from($api_name;format="Camel"$ as s)
  }
  .map(
    rs => $api_name;format="Camel"$Representer(
      id   = rs.long(s.resultName.id),
      kind = Option(rs.string(s.resultName.kind)),
      size = Option(rs.string(s.resultName.size))
    )
  ).list().apply()

  def find(id: Int): Option[$api_name;format="Camel"$Representer] = withSQL {
    select.from($api_name;format="Camel"$ as s)
      .where.eq(s.id, id)
  }
  .map(
    rs => $api_name;format="Camel"$Representer(
      id   = rs.long(s.resultName.id),
      kind = Option(rs.string(s.resultName.kind)),
      size = Option(rs.string(s.resultName.size))
    )
  ).single().apply()

  def create(params: Map[String, String]): $api_name;format="Camel"$Representer = {
    val c = $api_name;format="Camel"$.column
    val generatedKey = withSQL {
      insert.into($api_name;format="Camel"$)
        .namedValues(
          c.kind      -> params.get("kind"),
          c.size      -> params.get("size"),
          c.createdAt -> DateTime.now,
          c.updatedAt -> DateTime.now
        )
    }.updateAndReturnGeneratedKey.apply()

    $api_name;format="Camel"$Representer(
      id   = generatedKey,
      kind = params.get("kind"),
      size = params.get("size")
    )
  }

  def update(params: Map[String, String]): $api_name;format="Camel"$Representer = {
    val c = $api_name;format="Camel"$.column

    withSQL {
      QueryDSL.update($api_name;format="Camel"$).set(
        c.kind      -> params.getOrElse("kind", c.kind),
        c.size      -> params.getOrElse("size", c.size),
        c.updatedAt -> DateTime.now
        ).where.eq(
          $api_name;format="Camel"$.column.id, params("id").toInt
        )
    }.update.apply()

    $api_name;format="Camel"$.find(params("id").toInt).get
  }

  def destroy(id: Int): Unit = withSQL {
    delete.from($api_name;format="Camel"$).where.eq($api_name;format="Camel"$.column.id, id)
  }.update.apply()
}
