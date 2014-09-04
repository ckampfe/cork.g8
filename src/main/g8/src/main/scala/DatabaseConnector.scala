package environment

import scalikejdbc._
import javax.sql.DataSource
import com.zaxxer.hikari._

trait DatabaseConnector {
  case class InvalidDatabaseAdapterException(message: String) extends Exception(message)

  def init(env: Env) {
    val dataSource: DataSource = {
      val ds = new HikariDataSource()

      // https://github.com/brettwooldridge/HikariCP/wiki/About-Pool-Sizing
      ds.setMaximumPoolSize(12)

      env.Db.adapter match {
        case "mysql" => {
          ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource")
          ds.addDataSourceProperty("serverName",   env.Db.host)
          ds.addDataSourceProperty("databaseName", env.Db.name)
          ds.addDataSourceProperty("port",         env.Db.port)
          ds.addDataSourceProperty("user",         env.Db.user)
          ds.addDataSourceProperty("password",     env.Db.password)
        }
        case "h2" => {
          ds.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource")
          ds.addDataSourceProperty("URL",          s"jdbc:h2:mem:${env.Db.name}")
          ds.addDataSourceProperty("user",         env.Db.user)
          ds.addDataSourceProperty("password",     env.Db.password)
        }
        case "postgresql" => "org.postgresql.ds.PGSimpleDataSource"
        case _            => throw new InvalidDatabaseAdapterException("you must supply a valid database adapter")
      }

      ds
    }

    ConnectionPool.singleton(new DataSourceConnectionPool(dataSource))

    // ad-hoc session provider on the REPL
    implicit val session = AutoSession

    sql"""
    create table if not exists bodes (
      id SERIAL NOT NULL PRIMARY KEY,
      kind varchar(255),
      size varchar(255),
      created_at timestamp NOT NULL,
      updated_at timestamp NOT NULL,
    )
    """.execute.apply()
  }
}
