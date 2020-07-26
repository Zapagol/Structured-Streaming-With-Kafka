package com.assignment.quaero.utils

import net.liftweb.json._

object JSONUtils {

  /**
    * This function is used to convert String JSON to case class <T>.
    * Schema of case class should match JSON structure
    *
    * @param json
    * @tparam T
    * @return
    */
  def extractJSONToObject[T](json: String)(implicit m: Manifest[T]): T ={
    implicit val formats = DefaultFormats
    // convert a String to a JValue object
    val jValue = parse(json)

    // create a Customer object from the string
    jValue.extract[T]
  }

}
