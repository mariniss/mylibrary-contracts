package com.fm.mylibrary.producer

import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import com.fm.mylibrary.entity.CategoryEntity._
import slick.jdbc.H2Profile

import scala.concurrent.Await
import scala.concurrent.duration.Duration

trait MockData {

  implicit val db: H2Profile.backend.Database


  def populateDB(): Unit = {

    val setup = DBIO.seq(
      // Create the tables, including primary and foreign keys
      categories.schema.create,

      // Insert some suppliers
      categories += "Java",
      categories += "DevOps"
    )


    val setupFuture = db.run(setup)
    Await.result(setupFuture, Duration.Inf)
  }

}
