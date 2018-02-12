package com.fm.mylibrary.producer.entity

import com.fm.mylibrary.model.Category
import com.fm.mylibrary.producer.db.DatabaseSupport
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

trait CategoryEntity {

  class Categories(tag: Tag) extends Table[Category](tag, "CATEGORY") {
    def name = column[String]("NAME", O.PrimaryKey)

    def * = name  <> (Category.apply, Category.unapply)
  }

  protected val categories = TableQuery[Categories]
}


class CategoryDAO extends CategoryEntity with DatabaseSupport {

  def insertOrUpdate(category: Category): Future[Int] =
        db.run(categories.insertOrUpdate(category))

  def findAll(): Future[Seq[Category]] =
        db.run(categories.result)

}