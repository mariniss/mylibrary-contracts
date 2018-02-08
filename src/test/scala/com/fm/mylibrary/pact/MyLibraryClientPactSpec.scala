package com.fm.mylibrary.pact

import com.fm.mylibrary.consumer.MyLibraryClient
import com.fm.mylibrary.model.Category
import com.fm.mylibrary.model.JsonProtocol._
import com.itv.scalapact.ScalaPactForger._
import org.scalatest.{FunSpec, Matchers}
import spray.json._


class MyLibraryClientPactSpec extends FunSpec with Matchers {

  describe("Connecting to the MyLibrary server") {

    it("should be able to fetch the categories"){

      val categories = List(Category("Java"), Category("DevOps"))

      forgePact
        .between("ScalaConsumer")
        .and("myLibraryServer")
        .addInteraction(
          interaction
            .description("Fetching categories")
            .given("Categories: [Java, DevOps]")
            .uponReceiving(
              method = GET,
              path = "/search/category",
              query = None)
//              headers = Map("Accept" -> "application/json"), //Add this and see the strange error, it seems it does not match the request and retrun with another stuff
//              body = None,
//              matchingRules = None)
            .willRespondWith(
              status = 200,
              headers = Map("Content-Type" -> "application/json"),
              body = categories.toJson.toString())
        )
        .runConsumerTest { mockConfig =>
          import com.fm.mylibrary.app.MyLibraryAppClient._

          val results = new MyLibraryClient().fetchCategories(mockConfig.baseUrl)

          results.isDefined shouldEqual true
          results.get.size shouldEqual 2
          results.get.forall(c => categories.contains(c)) shouldEqual true
        }

    }
  }
}
