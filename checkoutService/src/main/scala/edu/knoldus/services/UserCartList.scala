package edu.knoldus.services

import edu.knoldus.knolkart.models.{InventoryList, Item}
import edu.knoldus.models.{User, UserCart, UserList}
import scala.collection.Map
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserCartList extends checkOutService {

  val obj = new UserServiceProvider
  val mobNum = 12345678
  val userName = "jyoti"
  val password = "abcd"
  val userList = UserList(List(User(mobNum, userName, password)))

  def placeOrder(item: Item, userMob: Int, cart: UserCart): Map[Int, List[Item]] = {
    val access = obj.returnAccessToken("jyoti", "abcd", userList)
    access match {
      case -1 => throw new Exception("could not authenticate")
      case _ => addToCart(item, userMob, cart)
    }
  }

  def generateBill(mobileNum: Int, cart: UserCart): Future[Double] = Future {
    val user = cart.userMap.filter { case (num, listItem) => num == mobileNum }
    val bill = user map {
      case (key, itemList) => val total = itemList.map(_.price).sum
        total
      case _ => throw new Exception("could not generate bill")
    }
    bill match {
      case head :: Nil => head
    }
  }


  def addToCart(item: Item, mobileNum: Int, cart: UserCart): Map[Int, List[Item]] = {
    if (cart.userMap.exists { case (num, listItem) => num == mobileNum }) {
      val hold = cart.userMap.filter { case (num, listItem) => num == mobileNum }
      val finalMap = hold map {
        case (key, value) => val list = value ::: List(item)
          val removed = cart.userMap - mobileNum
          removed + (mobileNum -> list)
      }
      finalMap match {
        case head :: Nil => head
      }
    }
    else {
      val list = List(item)
      val returnMap = cart.userMap + (mobileNum -> list)
      returnMap
    }
  }


  def removeFromCart(item: Item, cart: UserCart, mobileNum: Int): Map[Int, List[Item]] = {
    cart.userMap - mobileNum
  }

}
