package edu.knoldus

import edu.knoldus.knolkart.models.{Item, Vendor}
import edu.knoldus.models.UserCart
import edu.knoldus.services.UserCartList
import org.scalatest.FunSuite
import scala.collection.immutable.Map
import scala.concurrent.ExecutionContext.Implicits.global

class CheckoutTest extends FunSuite {
  val user = new UserCartList

  test("place order test") {
    val item = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
    val usercart = Map[Int,List[Item]]()
    val pass = UserCart(usercart)
    val input = user.placeOrder(item,12345678,pass)
    val result = Map((12345678, List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1)))))
    assert(input === result)
  }


  test("remove from cart") {
    val item = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
    val usercart = Map((12345678, List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1)))))
    val pass = UserCart(usercart)
    val input = user.removeFromCart(item, pass, 12345678)
    val result = Map((12345678, List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1)))))
    assert(input !== result)
  }

  test("remove from cart Empty List") {
    val item = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
    val usercart = UserCart(Map())
    val input = user.removeFromCart(item, usercart, 12345678)
    val result = Map((12345678, List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1)))))
    assert(input !== result)
  }


  test("generate bill test") {
    val item = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
    val usercart = Map((12345678, List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1)))))
    val pass = UserCart(usercart)
    val input = user.generateBill(12345678, pass)
    val result = 100
    input map { sum => assert(sum === result) }
  }


  test("add to cart for empty cart test") {
    val item = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
    val usercart = Map[Int,List[Item]]()
    val pass = UserCart(usercart)
    val input = user.addToCart(item,12345678,pass)
    val result = Map((12345678, List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1)))))
    assert(input === result)
  }

  test("add to cart test when item exists") {
    val item = Item(1, 1, "vivel", "soap", "a", 100, 1, Vendor("a", 1))
    val firstItem = Item(2, 1, "vivel", "soap", "a", 100, 1, Vendor("a", 1))
    val usercart = Map((12345678, List(item)))
    val pass = UserCart(usercart)
    val input = user.addToCart(firstItem,12345678,pass)
    val result = Map((12345678, List(item,firstItem)))
    assert(input === result)
  }


}
