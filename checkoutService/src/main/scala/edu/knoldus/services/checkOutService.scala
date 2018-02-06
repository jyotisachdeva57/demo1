package edu.knoldus.services

import edu.knoldus.knolkart.models.{InventoryList, Item}
import edu.knoldus.models.UserCart
import scala.collection.Map
import scala.concurrent.Future

trait checkOutService {
  def addToCart(item: Item, cart: UserCart, mobileNum: Int): Future[Map[Int, List[Item]]]

  def placeOrder(item: Item, userMob: Int, cart: UserCart): Future[UserCart]

  def generateBill(mobileNum: Int, cart: UserCart): Future[Double]

  def removeFromCart(item: Item, cart: UserCart, mobileNum: Int): Map[Int, List[Item]]

  def updateInventory(products: InventoryList, itemID: Int, count: Int, f: (Int, Int) => Int): Future[InventoryList]
}
