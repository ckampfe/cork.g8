package environment

import java.net.URI
import com.typesafe.config._

class Env(env: String) {
  var Config = ConfigFactory.load(env)

  object Db {
    def dbURI = new URI(sys.env.getOrElse("DATABASE_URL", ""))

    // if there is a DATABASE_URL (like from Heroku),
    // use it, otherwise draw from application config files
    val (adapter, host, port, name, user, password) = dbURI.toString.isEmpty match {
      case true =>
        (Config.getString("$name;format="norm"$.database.adapter"),
         Config.getString("$name;format="norm"$.database.host"),
         Config.getString("$name;format="norm"$.database.port"),
         Config.getString("$name;format="norm"$.database.name"),
         Config.getString("$name;format="norm"$.database.user"),
         Config.getString("$name;format="norm"$.database.password"))

      case false =>
        (dbURI.getScheme,
         dbURI.getHost,
         dbURI.getPort,
         dbURI.getPath.tail,
         dbURI.getUserInfo.split(":")(0),
         dbURI.getUserInfo.split(":")(1))
    }

    val url = adapter match {
      case "h2" =>
        s"jdbc:h2:mem:\${name}"

      case "postgresql" | "postgres" =>
        s"jdbc:postgresql://\${host}:\${port}/\${name}"

      case _    =>
        s"jdbc:\${adapter}://\${host}:\${port}/\${name}"
    }
  }
}
