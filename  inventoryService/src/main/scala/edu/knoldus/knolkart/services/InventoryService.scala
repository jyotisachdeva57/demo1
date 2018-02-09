package edu.knoldus.knolkart.services

import edu.knoldus.knolkart.models.{InventoryList, Item}
import scala.concurrent.Future

trait InventoryService {

  def sortItemsRating(inventory: InventoryList, filterParameter: String): Future[List[Item]]

  def addItem(inventory: InventoryList, item: Item): InventoryList

  def takeInput: Item

  def availableItems(inventory: InventoryList): InventoryList //default display 10 items

  def findByID(products: InventoryList, itemID: Int): Future[List[Item]]

  def sortItemsPrice(inventory: InventoryList, category: String): Future[List[Item]]

  def findByCategory(products: InventoryList, category: String): Future[List[Item]]

  def updateInventory(products: InventoryList, itemID: Int, count: Int, f: (Int, Int) => Int): Future[InventoryList]
}


