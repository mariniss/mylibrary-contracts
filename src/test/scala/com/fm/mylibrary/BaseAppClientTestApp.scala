package com.fm.mylibrary

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer
import akka.testkit.{ImplicitSender, TestKit}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.{ExecutionContextExecutor, Future}


class BaseAppClientTestApp extends TestKit(ActorSystem("BaseAppClientTestApp"))
            with WordSpecLike
            with ImplicitSender
            with Matchers
            with BeforeAndAfterAll
            with MockFactory {

  implicit val actorSystem: ActorSystem = system
  implicit val materializer: ActorMaterializer = ActorMaterializer()(system)
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  implicit val requestExecutor = mockFunction[HttpRequest, Future[HttpResponse]]


  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

}
