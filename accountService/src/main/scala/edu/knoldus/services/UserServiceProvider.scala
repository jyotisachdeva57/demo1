package edu.knoldus.services

import edu.knoldus.models.{User, UserList}
import org.apache.log4j.Logger
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class UserServiceProvider {

  def addUser(inventory: UserList, item: User): Future[UserList] = Future {
    val listOfItems = inventory.listOfUsers
    val newList = item :: listOfItems
    UserList(newList)
  }

  def takeInput: User = {
    val mobNum = 12345678
    val userName = "jyoti"
    val password = "abcd"
    val obj = User(mobNum, userName, password)
    obj
  }
//    implicit val accesstoken = "abcd"
//    import scala.io.StdIn._
//    val log = Logger.getLogger(this.getClass)
//    log.info("\n ENTER USER NAME")
//    val name = readLine()
//    log.info("ENTER USER MOBILE NUMBER\n")
//    val mobNum = readInt()
//    log.info("ENTER USER PASSWORD\n")
//    val password = readLine()
//
//  }

  def authenticateUser(userName: String, password: String, userList: UserList): Boolean = {
    userList.listOfUsers.exists(x => x.userName == userName && x.password == password)
  }

  def generateAccessToken(userList: List[User]): Int = {
    userList match {
      case List(first) => first.mobileNum.##
      case _ => throw new Exception
    }
  }

  def returnAccessToken(userName: String, password: String, userList: UserList): Int = {
    if (authenticateUser(userName, password, userList)) {
      val user = userList.listOfUsers.filter(x => x.userName == userName && x.password == password)
      generateAccessToken(user)
    }
    else {
      -1
    }
  }

}
