package com.assignment.quaero.sinks

import org.apache.spark.sql.{DataFrame, Dataset, SaveMode}

object CsvSink {

  /**
    * Save Dataset into a CSV file format delimited by <delimiter>.
    * By defulat, it overrides the CSV file. This behaviour can change with <saveMode>
    *
    * @param dataset
    * @param intermediateLocation
    * @param saveMode
    * @param charset
    * @param header
    * @param delimiter
    */
  def save[T](
               dataset: Dataset[T],
               intermediateLocation: String,
               saveMode: SaveMode = SaveMode.Overwrite,
               charset: String = "UTF-8",
               header: Boolean = false,
               delimiter: String = ","
             ): Unit = {
    dataset.write
      .option("header", header.toString)
      .option("charset", charset)
      .option("sep", delimiter)
      .option("escape", "\\")
      .option("ignoreLeadingWhiteSpace", "false")
      .option("ignoreTrailingWhiteSpace", "false")
      .mode(saveMode)
      .csv(intermediateLocation)

  }

  /**
    * Save DataFrame into a CSV file format delimited by <delimiter>.
    * By defulat, it overrides the CSV file. This behaviour can change with <saveMode>
    *
    * @param dataFrame
    * @param intermediateLocation
    * @param saveMode
    * @param charset
    * @param header
    * @param delimiter
    */
  def saveDataFrame(
                     dataFrame: DataFrame,
                     intermediateLocation: String,
                     saveMode: SaveMode = SaveMode.Overwrite,
                     charset: String = "UTF-8",
                     header: Boolean = false,
                     delimiter: String = ","
                   ): Unit = {
    dataFrame.write
      .option("header", header.toString)
      .option("charset", charset)
      .option("sep", delimiter)
      .option("escape", "\\")
      .option("ignoreLeadingWhiteSpace", "false")
      .option("ignoreTrailingWhiteSpace", "false")
      .mode(saveMode)
      .csv(intermediateLocation)

  }
}
