package com.cork.config

import scalikejdbc._

trait DatabaseConnector {
  // initialize JDBC driver & connection pool
  Class.forName("com.mysql.jdbc.Driver")
  ConnectionPool.singleton("jdbc:mysql://localhost:3306/scalatra_test", "root", "")

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
