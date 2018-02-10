package com.fm.mylibrary.producer

import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global


trait Database extends MigrationConfig {

  val db: H2Profile.backend.Database = Database.forConfig("database")

  def closeDB(): Unit = db.close

}
