package com.fm.mylibrary.producer.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.directives.DebuggingDirectives
import akka.stream.ActorMaterializer
import com.fm.mylibrary.producer.db.{DatabaseMigrationSupport, MockData}
import com.fm.mylibrary.producer.{Config, Routes}

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}


object MyLibraryAppServer extends App
          with Routes
          with Config
          with DatabaseMigrationSupport
          with MockData
          with DebuggingDirectives {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  val log = actorSystem.log


  def startApplication(): Unit = {
    migrateDB()
    addMockCategories()

    Http().bindAndHandle(handler = logRequestResult("log")(routes), interface = httpInterface, port = httpPort).onComplete {
      case Success(b) => log.info(s"application is up and running at ${b.localAddress.getHostName}:${b.localAddress.getPort}")
      case Failure(e) => log.error(s"could not start application: {}", e.getMessage)
    }
  }

  def stopApplication(): Unit = {
    actorSystem.terminate()
  }

  startApplication()
}
