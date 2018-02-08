package com.fm.mylibrary.consumer

import akka.http.scaladsl.model._
import com.fm.mylibrary.BaseAppClientTestApp
import com.fm.mylibrary.model.Category

import scala.concurrent.Future


class MyLibraryClientSpec extends BaseAppClientTestApp {

  "Fetch categories" must {
    "execute the HTTP request to get all categories and returns them" in {
      val request = HttpRequest(HttpMethods.GET, "//test/search/category")
      val responseEntity = HttpEntity(bytes = """[{"name": "Java"}, {"name": "DevOps"}]""".getBytes,
                                      contentType = ContentTypes.`application/json`)
      val response = HttpResponse(status = StatusCodes.OK, entity = responseEntity)
      requestExecutor.expects(request).returning(Future.successful(response))

      val results = new MyLibraryClient().fetchCategories("//test")

      results.isDefined shouldEqual true
      results.get.size shouldEqual 2
      results.get.contains(Category("Java")) shouldEqual true
      results.get.contains(Category("DevOps")) shouldEqual true
    }

    "execute the HTTP request to get all categories and return an empty array if there are none" in {
      val request = HttpRequest(HttpMethods.GET, "//test/search/category")
      val responseEntity = HttpEntity(bytes = "[]".getBytes, contentType = ContentTypes.`application/json`)
      val response = HttpResponse(status = StatusCodes.OK, entity = responseEntity)
      requestExecutor.expects(request).returning(Future.successful(response))

      val results = new MyLibraryClient().fetchCategories("//test")

      results.isDefined shouldEqual true
      results.get.size shouldEqual 0
    }

    "execute the HTTP request to get all categories and return an empty option in case of error" in {
      val request = HttpRequest(HttpMethods.GET, "//test/search/category")
      val response = HttpResponse(status = StatusCodes.BadRequest)
      requestExecutor.expects(request).returning(Future.successful(response))

      val results = new MyLibraryClient().fetchCategories("//test")

      results.isDefined shouldEqual false
    }
  }

}
