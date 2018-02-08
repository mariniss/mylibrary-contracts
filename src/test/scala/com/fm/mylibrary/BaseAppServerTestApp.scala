package com.fm.mylibrary

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.fm.mylibrary.producer.Routes
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.ExecutionContextExecutor


class BaseAppServerTestApp extends WordSpec
            with ScalatestRouteTest
            with Matchers
            with MockFactory
            with Routes {

  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

}
