package com.fm.mylibrary.consumer.service

import com.fm.mylibrary.consumer.MyLibraryClient
import com.fm.mylibrary.consumer.model.Category
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpec}

class CategoriesServiceSpec extends WordSpec with Matchers with MockFactory {

  private val mockMyLibraryClient = mock[MyLibraryClient]
  private val service = new CategoriesService(mockMyLibraryClient)

  "Count Categories" must {
    "return the number of all categories fetched form MyLibrary" in {
      val javaCategory = Category("Java")
      val devopsCategory = Category("DevOps")
      (mockMyLibraryClient.fetchCategories _).expects().returning(Some(List(javaCategory, devopsCategory)))

      val result = service.countCategories()

      result shouldBe 2
    }

    "return 0 in case of the fetch form MyLibrary fails" in {
      (mockMyLibraryClient.fetchCategories _).expects().returning(None)

      val result = service.countCategories()

      result shouldBe 0
    }
  }

}
