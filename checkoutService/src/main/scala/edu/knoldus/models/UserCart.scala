package edu.knoldus.models

import edu.knoldus.knolkart.models.Item

case class UserCart(userMap: Map[Int, List[Item]])
