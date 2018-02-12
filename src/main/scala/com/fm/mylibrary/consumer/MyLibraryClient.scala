package com.fm.mylibrary.consumer

import akka.actor.ActorSystem
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.fm.mylibrary.model.Category
import com.fm.mylibrary.model.JsonProtocol._

import scala.concurrent.{ExecutionContextExecutor, Future}


class MyLibraryClient(implicit val myLibraryServerUrl: String,
                      implicit val actorSystem: ActorSystem,
                      implicit val materializer: ActorMaterializer,
                      implicit val executionContext: ExecutionContextExecutor,
                      implicit val requestExecutor: HttpRequest => Future[HttpResponse]) extends BaseHttpClient {

  def fetchCategories(): Option[List[Category]] = executeSyncRequest(
      RequestBuilding.Get(s"$myLibraryServerUrl/search/category"),
      response =>
        if(response.status == StatusCodes.OK)
          Unmarshal(response.entity).to[Option[List[Category]]]
        else
          Future.successful(None)
  )
}
