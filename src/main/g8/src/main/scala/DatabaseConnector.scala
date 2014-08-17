package environment

import scalikejdbc._
import javax.sql.DataSource
import com.zaxxer.hikari._

trait DatabaseConnector {
  case class InvalidDatabaseAdapterException(message: String) extends Exception(message)
  // set up HikariCP connection pool
  val dataSource: DataSource = {
    val ds = new HikariDataSource()
    val dataSourceClassName = Env.Db.adapter match {
      case "mysql"      => "com.mysql.jdbc.jdbc2.optional.MysqlDataSource"
      case "h2"         => "org.h2.jdbcx.JdbcDataSource"
      case "postgresql" => "org.postgresql.ds.PGSimpleDataSource"
      case _            => throw new InvalidDatabaseAdapterException("you must supply a valid database adapter")
    }

    // https://github.com/brettwooldridge/HikariCP/wiki/About-Pool-Sizing
    ds.setMaximumPoolSize(12)
    ds.setDataSourceClassName(dataSourceClassName)
    ds.addDataSourceProperty("serverName",   Env.Db.host)
    ds.addDataSourceProperty("databaseName", Env.Db.name)
    ds.addDataSourceProperty("port",         Env.Db.port)
    ds.addDataSourceProperty("user",         Env.Db.user)
    ds.addDataSourceProperty("password",     Env.Db.password)
    ds
  }

  // and initialize it
  ConnectionPool.singleton(new DataSourceConnectionPool(dataSource))

  /* H2 in-memory datastore, great for testing */
  // Class.forName("org.h2.Driver")
  // ConnectionPool.singleton("jdbc:h2:mem:hello", "user", "pass")

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession

  /* EXAMPLE DATABASE CREATION */
  // sql"""
  // create table smiles (
  //   id SERIAL NOT NULL PRIMARY KEY,
  //   kind varchar(255),
  //   size varchar(255),
  //   created_at timestamp NOT NULL,
  //   updated_at timestamp NOT NULL
  // )
  // """.execute.apply()
}
