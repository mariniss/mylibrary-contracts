package com.fm.mylibrary.consumer

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.fm.mylibrary.consumer.model.Category
import com.fm.mylibrary.consumer.model.JsonProtocol._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object MyLibraryClient {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher


  def fetchCategories(serverUrl: String): Option[List[Category]] = {

    val request = RequestBuilding.Get(s"$serverUrl/search/category")
    val response : Future[Option[List[Category]]] = Http().singleRequest(request).flatMap({  response =>
        Unmarshal(response.entity).to[Option[List[Category]]]
    })

    Await.result(response, 5000 millis)
  }

}
