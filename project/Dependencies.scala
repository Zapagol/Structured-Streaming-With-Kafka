import sbt._

object Dependencies {

  private lazy val sparkVersion = "3.0.0"
  private lazy val kafkaVersion = "2.4.0"
  private lazy val liftJsonVersion = "3.3.0"
  private lazy val configTypeSafeVersion = "1.3.1"

  lazy val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion
  lazy val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion

  // streaming
  lazy val sparkStreamer = "org.apache.spark" %% "spark-streaming" % sparkVersion

  // streaming-kafka
  lazy val sparkSqlKafka = "org.apache.spark" % "spark-sql-kafka-0-10_2.12" % sparkVersion

  // low-level integrations
  lazy val sparkStreamingKafka = "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion

  // kafka
  lazy val apacheKafka = "org.apache.kafka" %% "kafka" % kafkaVersion
  lazy val apacheKafkaStream = "org.apache.kafka" % "kafka-streams" % kafkaVersion

  //lift-json
  lazy val liftJson = "net.liftweb" %% "lift-json" % liftJsonVersion

  //typesafe config
  lazy val configTypeSafe = "com.typesafe" % "config" % configTypeSafeVersion

}
