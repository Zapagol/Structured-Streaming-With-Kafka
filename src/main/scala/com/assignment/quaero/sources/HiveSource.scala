package com.assignment.quaero.sources

import org.apache.spark.sql._

object HiveSource {

  /**
    * This function loads data from <T> hive table.
    * Note : Hive table schema must be same as case class schema
    *
    * @param tableName
    * @param dataBase
    * @param spark
    * @param encoder
    * @tparam T
    * @return
    */
  def load[T](tableName: String, dataBase: String)(implicit spark: SparkSession, encoder: Encoder[T]): Dataset[T] ={
    spark
      .table(s"$dataBase.$tableName").as[T]
  }

}
