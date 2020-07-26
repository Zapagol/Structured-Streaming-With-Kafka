package com.assignment.quaero.utils

import org.apache.spark.sql.SparkSession

trait SparkUtils {

  implicit val spark = SparkSession
    .builder()
    .appName("Quaero Assignment")
    .master("local[*]")
    .getOrCreate()


}
