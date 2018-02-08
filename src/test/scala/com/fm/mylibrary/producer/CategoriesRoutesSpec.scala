package com.fm.mylibrary.producer

import com.fm.mylibrary.BaseAppServerTestApp
import com.fm.mylibrary.model.Category
import com.fm.mylibrary.model.JsonProtocol._


class CategoriesRoutesSpec extends BaseAppServerTestApp {

  "The service" should {

    "return an empty JSon array if there are no categories" in {
      Get("/search/category") ~> routes ~> check {
        responseAs[List[Category]] shouldBe List()
      }
    }

  }
}
