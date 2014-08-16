package environment

import com.typesafe.config._

object Env {
  val Config = ConfigFactory.load()

  val env = Config.getString("$name;format="norm"$.env")

  object Db {
    val adapter  = Config.getString("$name;format="norm"$.database.adapter")
    val host     = Config.getString("$name;format="norm"$.database.host")
    val port     = Config.getString("$name;format="norm"$.database.port")
    val name     = Config.getString("$name;format="norm"$.database.name")
    val user     = Config.getString("$name;format="norm"$.database.user")
    val password = Config.getString("$name;format="norm"$.database.password")

    val url = s"jdbc:\${adapter}://\${host}:\${port}/\${name}_\${env}"
  }
}

