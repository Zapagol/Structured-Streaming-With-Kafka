package com.assignment.quaero.udfs

import org.apache.spark.sql.functions.udf

object UDFs {

  val stringify = udf((vs: Seq[String]) => vs match {
    case null => null
    case _    => s"""${vs.mkString(",")}"""
  })

}
