package edu.knoldus.services

import edu.knoldus.knolkart.models.{InventoryList, Item}
import edu.knoldus.models.UserCart

import scala.collection.Map
import scala.concurrent.Future

trait checkOutService {
  def addToCart(item: Item, mobileNum: Int, cart: UserCart): Map[Int, List[Item]]

  def placeOrder(item: Item, userMob: Int, cart: UserCart): Map[Int, List[Item]]

  def generateBill(mobileNum: Int, cart: UserCart): Future[Double]

  def removeFromCart(item: Item, cart: UserCart, mobileNum: Int): Map[Int, List[Item]]

}
