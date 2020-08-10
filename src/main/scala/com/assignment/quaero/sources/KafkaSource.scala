package com.assignment.quaero.sources

import org.apache.spark.sql.{Dataset, SparkSession}

object KafkaSource {

  /**
    * Below API subscribe to 1 topic = <topicName>. Even we can subscribe to multiple
    * topics as <topic1, topic2, etc..>
    *
    * Kafka’s own configurations can be set via DataStreamReader.option with kafka.
    *
    * @param bootStrapServers
    * @param topicName
    * @param spark
    * @param subscribe
    * @param includeHeaders
    * @param pollTimeout
    * @return
    */
  def load(bootStrapServers: String,
           topicName: String,
           subscribe: String = "subscribe",
           includeHeaders: Boolean = false,
           pollTimeout: Int = 512)(implicit spark: SparkSession): Dataset[String] = {

    import spark.implicits._
    spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", bootStrapServers)
      .option(subscribe, topicName)
      .option("includeHeaders", includeHeaders)
      .option("kafkaConsumer.pollTimeoutMs", pollTimeout)
      .load()
      .selectExpr("CAST(value AS STRING)")
      .as[String]
  }

}
