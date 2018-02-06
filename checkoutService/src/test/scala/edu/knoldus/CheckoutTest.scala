package edu.knoldus

import edu.knoldus.knolkart.models.{Item, Vendor}
import edu.knoldus.models.{User, UserCart}
import edu.knoldus.services.UserCartList
import org.scalatest.FunSuite


class CheckoutTest extends FunSuite{
  val user=new UserCartList

//  test("take input") {
//    val item = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
//    val userCart=UserCart(Map((12345678,List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))))))
//    val input = user.addToCart(item,userCart,12345678)
//    val result = User(12345678,"jyoti","abcd")
//    input map { sum => assert(sum == result) }
//  }


  test("remove from cart") {
    val item = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
    val userCart = UserCart(Map((12345678, List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))))))
    val input = user.removeFromCart(item, userCart, 12345678)
    val result = User(12345678, "jyoti", "abcd")
    assert(input !== result)
  }

  test("remove from cart Empty List") {
    val item = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
    val userCart = UserCart(Map((12345678, List(Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))))))
    val input = user.removeFromCart(item, userCart, 12345678)
    val result = List()
    assert(input === result)
  }
}
