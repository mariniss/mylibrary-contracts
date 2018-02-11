package com.fm.mylibrary.producer.entity

import slick.jdbc.H2Profile.api._


object CategoryEntity {
  val categories = TableQuery[CategoryEntity]
}

class CategoryEntity(tag: Tag) extends Table[(String)](tag, "CATEGORY") {
  def name = column[String]("NAME", O.PrimaryKey)

  def * = (name)
}
