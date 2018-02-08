package com.fm.mylibrary.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.fm.mylibrary.producer.Routes

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object MyLibraryAppServer extends App with Routes {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  val log = actorSystem.log


  Http().bindAndHandle(searchRoutes, "0.0.0.0", 8080).onComplete {
    case Success(b) => log.info(s"application is up and running at ${b.localAddress.getHostName}:${b.localAddress.getPort}")
    case Failure(e) => log.error(s"could not start application: {}", e.getMessage)
  }
}
