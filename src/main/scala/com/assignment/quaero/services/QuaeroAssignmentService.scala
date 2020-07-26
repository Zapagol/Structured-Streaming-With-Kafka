package com.assignment.quaero.services

import com.typesafe.config.Config
import org.apache.spark.sql.SparkSession
import com.assignment.quaero.constants.ConfigConstants._
import com.assignment.quaero.models.{EmailMappings, JSONRoot}
import com.assignment.quaero.utils.JSONUtils.extractJSONToObject
import com.assignment.quaero.bridgers.QuaeroBridger
import com.assignment.quaero.sinks.CsvSink
import com.assignment.quaero.sources.HiveSource
import com.assignment.quaero.sources.KafkaSource
import com.assignment.quaero.udfs.UDFs._

object QuaeroAssignmentService {

  def run(config: Config)(implicit spark: SparkSession): Unit = {

    import spark.implicits._
    val bootStrapServers = config.getString(BOOT_STRAP_SERVERS)
    val quaeroTopic = config.getString(QUAERO_ASSIGNMENT_TOPIC)
    val databaseName = config.getString(HADOOP_DATABASE_NAME)
    val emailMappingTable = config.getString(EMAIL_MAPPINGS)
    val outputPath = config.getString(CSV_OUTPUT_PATH)

    //Load value from Kafka topic = <quaeroTopic>
    val kafkaSourceDS = KafkaSource.load(bootStrapServers, quaeroTopic)

    //Load email mapping data from hive table = <email_mappings>
    val emailMappingsDS =
      HiveSource.load[EmailMappings](emailMappingTable, databaseName)

    //Extract Customer details from kafka source
    val customerDS = kafkaSourceDS
      .map{json =>
        extractJSONToObject[JSONRoot](json)
          .root
          .cust_info
      }
      .flatMap(x => x)

    //Join Customer with Email Mappings
    val innerJoined = QuaeroBridger.customerEmailMappingBridger(customerDS, emailMappingsDS)

    //Convert Customer Email details to required csv format
    val emailAttributeDF = innerJoined
      .groupByKey(_.email)
      .mapGroups {
        case (key, itr) =>
          val attribute = itr.toList.map { record =>
            record.attribute_name + " = " + record.attribute_value
          }
          (key, attribute)
      }.toDF()
      .withColumn("_2", stringify($"_2"))
      .coalesce(1)

    /**
      * Store Data in CSV format as below
      * email, "attribute_name1 = attriute_value1, attribute_name2 = attriute_value2"
      */
    CsvSink
      .saveDataFrame(emailAttributeDF, outputPath)

  }
}
