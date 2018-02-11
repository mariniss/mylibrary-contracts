package com.fm.mylibrary.producer

import akka.stream.Materializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext
import com.fm.mylibrary.producer.entity.CategoryEntityDAO

import io.circe.generic.auto._
import io.circe.syntax._


trait Routes {

  implicit val materializer: Materializer
  implicit val executionContext: ExecutionContext

  private val categoryEntityDAO = new CategoryEntityDAO()


  val searchRoutes: Route = {
    pathPrefix("search" / "category") {
      get {
        complete(
          categoryEntityDAO.findAll().asJson
        )
      }
    }
  }

  val routes: Route = searchRoutes
}
