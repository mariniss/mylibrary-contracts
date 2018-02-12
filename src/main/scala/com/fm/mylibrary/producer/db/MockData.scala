package com.fm.mylibrary.producer.db

import com.fm.mylibrary.model.Category
import com.fm.mylibrary.producer.entity.CategoryDAO

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.Duration

trait MockData {

  implicit val executionContext: ExecutionContext


  def addMockCategories(): Unit = {
    val categoryEntityDAO = new CategoryDAO()

    val setupFuture = for {
      c1 <- categoryEntityDAO.insertOrUpdate(Category("Java"))
      c2 <- categoryEntityDAO.insertOrUpdate(Category("DevOps"))
    } yield c1 + c2

    Await.result(setupFuture, Duration.Inf)
  }

}
