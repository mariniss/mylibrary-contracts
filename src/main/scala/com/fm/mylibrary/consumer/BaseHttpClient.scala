package com.fm.mylibrary.consumer

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.language.postfixOps

trait BaseHttpClient {

  implicit def actorSystem: ActorSystem
  implicit def materializer: ActorMaterializer
  implicit def executionContext: ExecutionContextExecutor

  implicit def requestExecutor: HttpRequest => Future[HttpResponse]

  val awaitTime: FiniteDuration = 5000 millis


  def executeSyncRequest[T](request: HttpRequest, responseHandler: HttpResponse => Future[T]): T = {
    val response: Future[T] = requestExecutor(request).flatMap({ response =>
      responseHandler(response)
    })

    Await.result(response, awaitTime)
  }
}
