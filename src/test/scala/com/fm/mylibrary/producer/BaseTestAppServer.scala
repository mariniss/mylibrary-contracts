package com.fm.mylibrary.producer

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.fm.mylibrary.producer.db.{DatabaseMigrationSupport, MockData}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

import scala.concurrent.ExecutionContextExecutor


class BaseTestAppServer extends WordSpec
            with ScalatestRouteTest
            with Matchers
            with MockFactory
            with DatabaseMigrationSupport
            with MockData
            with Routes
            with BeforeAndAfterAll {

  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  override def beforeAll(): Unit = {
    migrateDB()
    addMockCategories()
  }

}
