package com.example.productsearch

import scala.collection.mutable.AbstractMap
import scala.collection.mutable.HashMap

object HelloWorld {

  def main(args: Array[String]) {

    val searcher = createSearcher { () => openDataFile("mydatafile.json") }

    val result = prompt(searcher)

    println(result)
  }

  def prompt(searcher: (String) => String) = {
    println("What is the product name? ")
    searcher("truck")
  }

  def saveDataFile(path: String, product: String, price: String, quantity: Int) {
      val data = openDataFile(path)
  }

  def createSearcher(data: () => AbstractMap[String, String]) = {

    val dataSet = data()

    (criteria: String) = (String) => String = {
      val result = dataSet.get(criteria)
      if(result.isEmpty) {
        return ""
      }
      result.get
    }
  }

  def openDataFile(path: String) = {

    // open data file
    val fileName = path.toUpperCase()

    // parse into a HashMap
    val dataSet = new HashMap[String, String]()
    dataSet.update("truck", "price:10.00")
    dataSet.update("ball", "price:5.00")
    dataSet.update("crayon", "price:7.00")

    dataSet
  }

}

