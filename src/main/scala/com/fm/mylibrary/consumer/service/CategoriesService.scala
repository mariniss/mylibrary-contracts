package com.fm.mylibrary.consumer.service

import com.fm.mylibrary.consumer.MyLibraryClient

class CategoriesService(val myLibraryClient: MyLibraryClient) extends {

  def countCategories(): Int = myLibraryClient.fetchCategories() match {
    case None => 0
    case Some(categories) =>
      categories.size
  }
}
