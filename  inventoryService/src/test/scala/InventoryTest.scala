import edu.knoldus.knolkart.models.{InventoryList, Item, Vendor}
import edu.knoldus.knolkart.services.ItemsServiceCall
import org.scalatest.FunSuite
import scala.concurrent.ExecutionContext.Implicits.global

class InventoryTest extends FunSuite {
  val invenObj = new ItemsServiceCall
  val input = invenObj.takeInput

  test("take input test") {
    val input = invenObj.takeInput
    val result = Item(1, 1, "a", "soap", "a", 100, 1, Vendor("a", 1))
    assert(input === result)
  }

  test("add Item") {
      val itemsObj = new ItemsServiceCall
      val listOfItems = InventoryList(List[Item]())
      val input = invenObj.addItem(listOfItems, Item(2, 1, "vivel", "soap", "a", 100, 1, Vendor("a", 1)))
      val result = InventoryList(List(Item(2, 1, "vivel", "soap", "a", 100, 1, Vendor("a", 1))))
      assert(input === result)
  }

  test("sort on price low to high") {
    val itemsObj = new ItemsServiceCall
    val item1 = Item(1, 1, "b", "soap", "a", 200, 1, Vendor("a", 1))
    val item2 = Item(2, 1, "b", "soap", "a", 100, 1, Vendor("a", 1))
    val listOfItems = InventoryList(List(item1, item2))
    val input = itemsObj.sortItemsPrice(listOfItems, "low to high")
    val result = List(item2, item1)
    input map { sum => assert(sum == result) }
  }

  test("sort on price high to low") {
    val itemsObj = new ItemsServiceCall
    val item1 = Item(1, 1, "b", "soap", "a", 200, 1, Vendor("a", 1))
    val item2 = Item(2, 1, "b", "soap", "a", 100, 1, Vendor("a", 1))
    val listOfItems = InventoryList(List(item1, item2))
    val input = itemsObj.sortItemsPrice(listOfItems, "high to low")
    val result = List(item1, item2)
    input map { sum => assert(sum == result) }
  }


  test("sort on price") {
    val itemsObj = new ItemsServiceCall
    val item1 = Item(1, 4, "vivel", "soap", "a", 200, 1, Vendor("a", 1))
    val item2 = Item(2, 5, "vivel", "soap", "a", 100, 1, Vendor("a", 1))
    val listOfItems = InventoryList(List(item1, item2))
    val input = itemsObj.sortItemsPrice(listOfItems, "default")
    val result = List(item2, item1)
    input map { sum => assert(sum == result) }
  }

  test("sort on rating low to high") {
    val itemsObj = new ItemsServiceCall
    val item1 = Item(1, 4, "vivel", "soap", "a", 200, 1, Vendor("a", 1))
    val item2 = Item(2, 5, "vivel", "soap", "a", 100, 1, Vendor("a", 1))
    val listOfItems = InventoryList(List(item1, item2))
    val input = itemsObj.sortItemsRating(listOfItems, "low to high")
    val result = List(item1, item2)
    input map { sum => assert(sum == result) }
  }


  test("sort on rating default") {
    val itemsObj = new ItemsServiceCall
    val item1 = Item(1, 4, "vivel", "soap", "a", 200, 1, Vendor("a", 1))
    val item2 = Item(2, 5, "vivel", "soap", "a", 100, 1, Vendor("a", 1))
    val listOfItems = InventoryList(List(item1, item2))
    val input = itemsObj.sortItemsRating(listOfItems, "default")
    val result = List(item2, item1)
    input map { sum => assert(sum == result) }
  }

  test("sort on rating high to low") {
    val itemsObj = new ItemsServiceCall
    val item1 = Item(1, 4, "vivel", "soap", "a", 200, 1, Vendor("a", 1))
    val item2 = Item(2, 5, "vivel", "soap", "a", 100, 1, Vendor("a", 1))
    val listOfItems = InventoryList(List(item1, item2))
    val input = itemsObj.sortItemsRating(listOfItems, "high to low")
    val result = List(item2, item1)
    input map { sum => assert(sum == result) }
  }




  test("check for find by id") {
    val itemsObj = new ItemsServiceCall
    val listOfItems = InventoryList(List(Item(2, 1, "vivel", "soap", "a", 100, 1, Vendor("a", 1))))
    val input = itemsObj.findByID(listOfItems, 2)
    val result = List(Item(2, 1, "b", "soap", "a", 100, 1, Vendor("a", 1)))
    input map { sum => assert(sum == result) }
  }


  test("check for failed case by id") {
    val itemsObj = new ItemsServiceCall
    val listOfItems = InventoryList(List(Item(2, 1, "vivel", "soap", "a", 100, 1, Vendor("a", 1))))
    val input = itemsObj.findByID(listOfItems, 1)
    val result = List(Item(2, 1, "b", "soap", "a", 100, 1, Vendor("a", 1)))
    input map { sum => assert(sum != result) }
  }

  test("check for find by Category") {
    val itemsObj = new ItemsServiceCall
    val listOfItems = InventoryList(List(Item(2, 1, "b", "cloth", "jeans", 100, 1, Vendor("a", 1))))
    val input = itemsObj.findByCategory(listOfItems, "jeans")
    val result = List(Item(2, 1, "b", "soap", "a", 100, 1, Vendor("a", 1)))
    input map { sum => assert(sum == result) }
  }

  test("check for failed case by Category") {
    val itemsObj = new ItemsServiceCall
    val listOfItems = InventoryList(List(Item(2, 1, "b", "cloth", "jeans", 100, 1, Vendor("a", 1))))
    val input = itemsObj.findByCategory(listOfItems, "jeans")
    val result = List(Item(2, 1, "b", "soap", "a", 100, 1, Vendor("a", 1)))
    input map { sum => assert(sum == result) }
  }


  test("check for fail by Category ") {
    val itemsObj = new ItemsServiceCall
    val listOfItems = InventoryList(List(Item(2, 1, "b", "cloth", "jeans", 100, 1, Vendor("a", 1))))
    val input = itemsObj.findByCategory(listOfItems, "shirt")
    val result = List(Item(2, 1, "b", "soap", "a", 100, 1, Vendor("a", 1)))
    input map { sum => assert(sum != result) }
  }

  test("available items test ") {
    val itemsObj = new ItemsServiceCall
    val listOfItems = InventoryList(List(Item(2, 1, "b", "cloth", "jeans", 100, 1, Vendor("a", 1))))
    val input = itemsObj.availableItems(listOfItems)
    val result = List(Item(2, 1, "b", "soap", "a", 100, 1, Vendor("a", 1)))
 assert(input === result)
  }


}
