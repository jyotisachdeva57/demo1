package edu.knoldus.knolkart.services

import edu.knoldus.knolkart.models.{InventoryList, Item, Vendor}
import org.apache.log4j.Logger
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class ItemsServiceCall extends InventoryService {

  def addItem(inventory: InventoryList, item: Item): InventoryList = {
    val listOfItems = inventory.listOfItems
    val newList = item :: listOfItems
    InventoryList(newList)
  }

  def takeInput: Item = {
    val first = 1
    val testVal = "a"
    val testDesc = "soap"
    val second = 100
    val item = Item(first, first, testVal, testDesc, testVal, second, first, Vendor(testVal, first))
    item
  }
    import scala.io.StdIn._
//    val log = Logger.getLogger(this.getClass)
//    log.info("\n ENTER PRODUCT ID")
//    val productID = readInt()
//    log.info("ENTER PRODUCT NAME\n")
//    val productName = readLine()
//    log.info("ENTER PRODUCT DESCRIPTION\n")
//    val productDesc = readLine()
//    log.info("ENTER PRODUCT CATEGORY\n")
//    val productCategory = readLine()
//    log.info("ENTER PRODUCT PRICE\n")
//    val productPrice = readDouble()
//    log.info("ENTER PRODUCT COUNT\n")
//    val productCount = readInt()
//    log.info("ENTER VENDOR NAME\n")
//    val vendorName = readLine()
//    log.info("ENTER VENDOR ID\n")
//    val vendorID = readInt()
//    Item(productID, productName, productDesc, productCategory, productPrice, productCount, Vendor(vendorName, vendorID))


  def sortItemsRating(inventory: InventoryList, filterParameter: String): Future[List[Item]] = {
    Future {
      filterParameter.toLowerCase match {

        case "low to high" => inventory.listOfItems.sortWith(_.rating < _.rating)

        case "high to low" => inventory.listOfItems.sortWith(_.rating > _.rating)

        case "default" => inventory.listOfItems.sortWith(_.rating > _.rating)
      }
    }
  }

  def sortItemsPrice(inventory: InventoryList, filterParameter: String): Future[List[Item]] = {
    Future {
      filterParameter.toLowerCase match {

        case "low to high" => inventory.listOfItems.sortWith(_.price < _.price)

        case "high to low" => inventory.listOfItems.sortWith(_.price > _.price)

        case "default" => inventory.listOfItems.sortWith(_.price < _.price)
      }
    }
  }


  def findByID(products: InventoryList, itemID: Int): Future[List[Item]] = Future {
    products.listOfItems.filter(_.productID == itemID)
  }

  def findByCategory(products: InventoryList, category: String): Future[List[Item]] = Future {
    products.listOfItems.filter(_.category == category)
  }


  def availableItems(inventory: InventoryList): InventoryList = {
    val number = 10
    val length = inventory.listOfItems.size
    number match {
      case num if length > num => InventoryList(inventory.listOfItems.take(num))
      case size if length > number => InventoryList(inventory.listOfItems.take(number))
      case _ => InventoryList(inventory.listOfItems)
    }
  }

}
