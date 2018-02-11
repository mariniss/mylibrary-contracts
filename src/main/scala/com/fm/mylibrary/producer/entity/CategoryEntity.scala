package com.fm.mylibrary.producer.entity

import com.fm.mylibrary.producer.db.DatabaseSupport
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future


case class CategoryEntity(tag: Tag) extends Table[(String)](tag, "CATEGORY") {
  def name = column[String]("NAME", O.PrimaryKey)

  def * = (name)
}

class CategoryEntityDAO extends DatabaseSupport {
  val categories = TableQuery[CategoryEntity]

  def findAll(): Future[Seq[CategoryEntity]] = db.run(categories.result)
        .map(new CategoryEntity(_))

}