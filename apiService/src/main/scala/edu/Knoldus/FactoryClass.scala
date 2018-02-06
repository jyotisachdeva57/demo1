package edu.Knoldus
import edu.knoldus.knolkart.models.{InventoryList, Item}
import edu.knoldus.knolkart.services.ItemsServiceCall
import edu.knoldus.models.{User, UserList}
import edu.knoldus.services.UserServiceProvider
class FactoryClass {

  val itemsObj=new ItemsServiceCall
  val listOfItems=InventoryList(List[Item]())
  val input: Item =itemsObj.takeInput
  itemsObj.addItem(listOfItems,input)
  itemsObj.findByID(listOfItems,1)
  itemsObj.sortItemsPrice(listOfItems,"low to high")

   val userObj=new UserServiceProvider
   val listOfUsers=UserList(List[User]())
   val userInput: User =userObj.takeInput
   userObj.addUser(listOfUsers,userInput)
   userObj.returnAccessToken("jyoti","1234567",listOfUsers)


}
