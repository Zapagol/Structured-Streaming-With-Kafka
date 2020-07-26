package com.assignment.quaero.udfs

import org.apache.spark.sql.functions.udf

object UDFs {

  /**
    * This UDF is used to convert Seq[String] to String
    *
    * @param arrayString
    */
  val stringify = udf((arrayString: Seq[String]) => arrayString match {
    case null => null
    case _    => s"""${arrayString.mkString(",")}"""
  })

}
