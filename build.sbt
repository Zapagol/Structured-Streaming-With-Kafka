import Dependencies._

name := "Quaero"

version := "0.1"

scalaVersion := "2.12.10"

resolvers ++= Seq(
  "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven",
  "Typesafe Simple Repository" at "https://repo.typesafe.com/typesafe/simple/maven-releases",
  "MavenRepository" at "https://mvnrepository.com"
)

libraryDependencies ++= Seq(
  sparkCore,
  sparkSql,
  sparkSqlKafka,
  sparkStreamingKafka,
  apacheKafka,
  apacheKafkaStream,
  liftJson,
  configTypeSafe
)