package com.fm.mylibrary.producer

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import com.fm.mylibrary.producer.entity.CategoryDAO

import scala.concurrent.ExecutionContext

import spray.json._
import com.fm.mylibrary.model.JsonProtocol._


trait Routes {

  implicit val materializer: Materializer
  implicit val executionContext: ExecutionContext

  private val categoryEntityDAO = new CategoryDAO()


  val searchRoutes: Route = {
    pathPrefix("search" / "category") {
      get {
        complete(
          categoryEntityDAO.findAll()
              .map(_.toJson)
        )
      }
    }
  }

  val routes: Route = searchRoutes
}
