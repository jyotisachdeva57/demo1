package edu.knoldus.knolkart.models

case class Item(productID: Int, rating: Int, productName: String, description: String, category: String, price: Double, productCount: Int, vendorInfo: Vendor)
