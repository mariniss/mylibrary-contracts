package com.fm.mylibrary.producer.db

import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._


trait Database extends MigrationConfig {

  val db: H2Profile.backend.Database = Database.forConfig("database")

  def closeDB(): Unit = db.close

}
