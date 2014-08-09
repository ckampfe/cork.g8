package com.$name;format="snake"$.config

import scalikejdbc._
import javax.sql.DataSource
import com.zaxxer.hikari._

trait DatabaseConnector {
  // set up HikariCP connection pool
  val dataSource: DataSource = {
    val ds = new HikariDataSource()
    val dataSourceClassName = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource"

    // https://github.com/brettwooldridge/HikariCP/wiki/About-Pool-Sizing
    ds.setMaximumPoolSize(12)
    ds.setDataSourceClassName(dataSourceClassName)
    ds.addDataSourceProperty("serverName", "localhost")
    ds.addDataSourceProperty("databaseName", "scalatra_test")
    ds.addDataSourceProperty("port", "3306")
    ds.addDataSourceProperty("user", "root")
    ds.addDataSourceProperty("password", "")
    ds
  }

  // and initialize it
  ConnectionPool.singleton(new DataSourceConnectionPool(dataSource))
  // ConnectionPool.singleton("jdbc:mysql://localhost:3306/scalatra_test", "root", "")

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
