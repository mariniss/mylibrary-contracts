package com.fm.mylibrary.producer

import akka.stream.Materializer
import akka.http.scaladsl.server.Directives._
import com.fm.mylibrary.model.Category

import scala.concurrent.ExecutionContext
import spray.json._
import com.fm.mylibrary.model.JsonProtocol._


trait Routes {
  implicit val materializer: Materializer
  implicit val executionContext: ExecutionContext

  val searchRoutes = {
    pathPrefix("search" / "category") {
      get {
        complete {
          List[Category]().toJson
        }
      }
    }
  }

  val routes = searchRoutes
}
