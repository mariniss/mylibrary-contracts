package com.fm.mylibrary.consumer

import com.fm.mylibrary.consumer.model.Category
import org.scalatest.{FunSpec, Matchers}

import com.itv.scalapact.ScalaPactForger._
import com.fm.mylibrary.consumer.model.JsonProtocol._
import spray.json._


class MyLibraryClientSpec extends FunSpec with Matchers {

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
          val results = MyLibraryClient.fetchCategories(mockConfig.baseUrl)

          results.isDefined shouldEqual true
          results.get.size shouldEqual 2
          results.get.forall(c => categories.contains(c)) shouldEqual true
        }

    }
  }
}
