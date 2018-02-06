package edu.knoldus.services

import edu.knoldus.models.{User, UserList}

import scala.concurrent.Future

trait ServiceProvider {
  def addItem(inventory: UserList, item: User): Future[UserList]

  def takeInput: User

  def authenticateUser(userName: String, password: String, userList: UserList): Boolean

  def generateAccessToken(user: List[User]): Int

  def returnAccessToken(userName: String, password: String, userList: UserList): String
}
