package com.fm.mylibrary.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.directives.DebuggingDirectives
import akka.stream.ActorMaterializer
import com.fm.mylibrary.producer._

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}


object MyLibraryAppServer extends App
          with Routes
          with Config
          with Database
          with MockData
          with DebuggingDirectives {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  val log = actorSystem.log


  def startApplication(): Unit = {
    migrate()
    populateDB()

    Http().bindAndHandle(handler = logRequestResult("log")(routes), interface = httpInterface, port = httpPort).onComplete {
      case Success(b) => log.info(s"application is up and running at ${b.localAddress.getHostName}:${b.localAddress.getPort}")
      case Failure(e) => log.error(s"could not start application: {}", e.getMessage)
    }
  }

  startApplication()
}
