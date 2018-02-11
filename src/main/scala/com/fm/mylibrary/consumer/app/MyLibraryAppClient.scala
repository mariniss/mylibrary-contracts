package com.fm.mylibrary.consumer.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContextExecutor, Future}

object MyLibraryAppClient {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  implicit val requestExecutor: HttpRequest => Future[HttpResponse] = Http().singleRequest(_)
}
