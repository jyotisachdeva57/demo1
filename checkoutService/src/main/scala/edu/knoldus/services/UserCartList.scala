package edu.knoldus.services

import edu.knoldus.knolkart.models.{InventoryList, Item}
import edu.knoldus.models.{User, UserCart, UserList}
import scala.collection.Map
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserCartList extends checkOutService {
  //  class Container[A](value: A) { def addIt(implicit evidence: A <%< Int) = 123 + value }


  val obj = new UserServiceProvider
  val mobNum = 12345678
  val userName = "jyoti"
  val password = "abcd"
  val userList = UserList(List(User(mobNum, userName, password)))

  def placeOrder(item: Item, userMob: Int, cart: UserCart): Future[Map[Int, List[Item]]] = Future{
    val access = obj.returnAccessToken("jyoti", "abcd", userList)
    access match {
      case -1 => throw new Exception("could not authenticate")
      case _ => addToCart(item, userMob, cart)
    }
  }

  def generateBill(mobileNum: Int, cart: UserCart): Future[Double] = Future {
    val user = cart.userMap.filter{ case (a, b) => a == mobileNum }
    user map {
      case (key, valu) => val total = valu.map(_.price).sum
        total
      case _ => throw new Exception("could not generate bill")
    }
  }

  def addToCart(item: Item, mobileNum: Int, cart: UserCart): Map[Int, List[Item]]= {
    if (cart.userMap.exists { case (a, b) => a == mobileNum }) {
      val hold = cart.userMap.filter { case (a, b) => a == mobileNum }
      hold map {
        case (key, value) => val list = value :+ item
            val removed = cart.userMap - mobileNum
            removed + (mobileNum -> list)
      }
    }
    else
      {
        cart.userMap + (mobileNum -> item)
      }
  }


  def removeFromCart(item: Item, cart: UserCart, mobileNum: Int): Future[Map[Int, List[Item]]] = Future {
    cart.userMap - mobileNum
  }

  def updateInventory(products: InventoryList, itemID: Int, count: Int, f: (Int, Int) => Int): Future[InventoryList] = {
    val hold = products.listOfItems.filter(x => x.productID != itemID)
    Future {
      val oldCount = products.listOfItems.filter(_.productID == itemID)(0)
      if (count != 0) {
        val newProduct = oldCount.copy(productCount = f(oldCount.productCount, count))
        InventoryList(hold :+ newProduct)
      }
      else products
    }
  }


}
