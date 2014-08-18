package environment

import com.typesafe.config._

object Env {
  var Config = ConfigFactory.load()

  lazy val env = Config.getString("$name;format="norm"$.env")

  object Db {
    lazy val adapter  = Config.getString("$name;format="norm"$.database.adapter")
    lazy val host     = Config.getString("$name;format="norm"$.database.host")
    lazy val port     = Config.getString("$name;format="norm"$.database.port")
    lazy val name     = Config.getString("$name;format="norm"$.database.name")
    lazy val user     = Config.getString("$name;format="norm"$.database.user")
    lazy val password = Config.getString("$name;format="norm"$.database.password")

    lazy val url = s"jdbc:\${adapter}://\${host}:\${port}/\${name}"
  }
}
