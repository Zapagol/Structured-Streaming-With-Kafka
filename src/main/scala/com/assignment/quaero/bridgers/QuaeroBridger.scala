package com.assignment.quaero.bridgers

import com.assignment.quaero.models.{Customer, CustomerEmailDetails, EmailMappings}
import org.apache.spark.sql.{Dataset, SparkSession}

object QuaeroBridger {

  /**
    * Bridge customer with email_mappings. <inner join> with on <customer_id>
    *
    * @param customerDS
    * @param emailMappingsDS
    * @param spark
    * @return
    */
  def customerEmailMappingBridger(customerDS: Dataset[Customer],
                                  emailMappingsDS: Dataset[EmailMappings])(implicit spark: SparkSession): Dataset[CustomerEmailDetails] = {
    import spark.implicits._
    customerDS
      .join(emailMappingsDS, Seq("customer_id"), "inner")
      .select($"email", $"attribute_name", $"attribute_value")
      .as[CustomerEmailDetails]
  }
}
