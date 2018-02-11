package com.fm.mylibrary.producer.db

import com.fm.mylibrary.producer.entity.CategoryEntity._
import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

trait MockData extends DatabaseSupport {


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
