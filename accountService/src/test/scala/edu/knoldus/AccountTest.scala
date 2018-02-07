package edu.knoldus

import edu.knoldus.models.{User, UserList}
import edu.knoldus.services.UserServiceProvider
import org.scalatest.FunSuite


class AccountTest extends FunSuite {

  val invenObj = new UserServiceProvider

  test("take input") {
    val input = invenObj.takeInput
    val result = User(12345678,"jyoti","abcd")
    assert(input === result)
  }

  test("add User") {
    intercept[Exception] {
      val listOfItems = UserList(List[User]())
      val input = invenObj.addUser(listOfItems, User(12345678, "jyoti", "abcd"))
      val result = UserList(List(User(12345678, "jyoti", "abcd")))
        assert (input === result)
    }
  }

  test("authenticate user") {
    val listOfItems = UserList(List(User(12345678,"jyoti","abcd")))
    val input = invenObj.authenticateUser("jyoti","abcd",listOfItems)
    val result = true
    assert (input === result)
  }

  test("authenticate Failed") {
    val listOfItems = UserList(List(User(12345678,"jyoti","abcd")))
    val input = invenObj.authenticateUser("jyoti","a",listOfItems)
    val result = true
    assert (input !== result)
  }


  test("return access token") {
    val listOfItems = UserList(List(User(12345678,"jyoti","abcd")))
    val input = invenObj.returnAccessToken("jyoti","abcd",listOfItems)
    assert (input !== -1)
  }

  test("Failed access token") {
    val listOfItems = UserList(List(User(12345678,"jyoti","abcd")))
    val input = invenObj.returnAccessToken("jyoti","abc",listOfItems)
    assert (input === -1)
  }

}
