package com.fm.mylibrary.producer.db

import com.fm.mylibrary.producer.Config
import org.flywaydb.core.Flyway


trait MigrationConfig extends Config {

  private val flyway = new Flyway()
  flyway.setDataSource(databaseUrl, databaseUser, databasePassword)

  def migrate(): Unit = {
    flyway.migrate()
  }

  def reloadSchema(): Unit = {
    flyway.clean()
    flyway.migrate()
  }
}