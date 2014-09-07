package $package$

import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import org.flywaydb.core.Flyway
import com.typesafe.config._
import environment._

abstract class SpecBase extends ScalatraSuite
  with FunSuiteLike
  with DatabaseConnector
{
  val env = new Env("application-test")

  SpecBase.migrated match {
    case false =>
      SpecBase.migrated = true
      SpecBase.migrate(env)
    case _ =>
  }

  init(env)
}

object SpecBase {
  var migrated = false
  lazy val flyway = new Flyway

  def migrate(env: Env) {
    flyway.setDataSource(env.Db.url, env.Db.user, env.Db.password)
    flyway.migrate
  }
}
