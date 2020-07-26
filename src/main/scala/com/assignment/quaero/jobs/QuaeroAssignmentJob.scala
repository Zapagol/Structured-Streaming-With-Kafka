package com.assignment.quaero.jobs

import com.assignment.quaero.services.QuaeroAssignmentService
import com.assignment.quaero.utils.SparkUtils
import com.typesafe.config.{Config, ConfigFactory}

object QuaeroAssignmentJob extends SparkUtils {

  def main(args: Array[String]): Unit = {
    //Load config details
    val config: Config = ConfigFactory.load()
    QuaeroAssignmentService.run(config)
  }
}
