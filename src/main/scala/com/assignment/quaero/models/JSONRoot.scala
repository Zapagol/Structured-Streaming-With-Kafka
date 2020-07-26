package com.assignment.quaero.models

case class Customer(customer_id: Int, attribute_name: String, attribute_value: String)
case class Root (cust_info: Seq[Customer])
case class JSONRoot(root: Root)
