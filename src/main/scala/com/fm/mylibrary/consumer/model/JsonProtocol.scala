package com.fm.mylibrary.consumer.model

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport


object JsonProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val categoryFormat = jsonFormat1(Category)
}
