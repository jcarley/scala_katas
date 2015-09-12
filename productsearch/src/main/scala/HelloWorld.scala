package com.example.productsearch

import java.io._
import scala.collection.mutable.{Map, HashMap}

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

case class Product(name: String, price: String, quantity: Int)
object Product {
  def fromMap(map: Map[String, String]): Product =
    new Product(map.get("product").head, map.get("price").head, map.get("quantity").head.toInt)
}

object HelloWorld {

  def main(args: Array[String]) {

    var searcher = createSearcher { () => openDataFile(source()) }

    run {(criteria: String) => {

      val (result, isFound) = searcher(criteria)

      if(isFound) {
        println(result)
      } else {
        searcher = createSearcher { () => saveDataFile(source()) }
      }

    }}

  }

  def run(action: (String) => Unit) {
    while(true) {
      val input = prompt("What is the product name? (:q to quit) ")
      if(input == ":q") {
        return
      } else {
        action(input)
      }
    }
  }

  def source() = {
    io.Source.fromFile("products.json")
  }

  def prompt(msg: String) = {
    print(msg)
    val lines = io.Source.stdin.getLines()
    val input = lines.next()
    input
  }

  def createSearcher(data: () => Map[String, Product]) = {

    val dataSet = data()

    (criteria: String) => {
      val result = dataSet.get(criteria.toLowerCase())
      (result.getOrElse(()=>"not found"), result.nonEmpty)
    }
  }

  def saveDataFile(source: io.Source) = {

      val choices = Array("product", "price", "quantity")

      val dataMap =
        (Map[String, String]() /: choices) { (map, choice) =>
          val input = prompt(s"What is the ${choice}: ")
          map + (choice -> input)
        }
      val product = Product.fromMap(dataMap)

      // save to hash
      val data = openDataFile(source)
      data.update(product.name, product)

      //TODO: save hash to file

      data
  }

  def openDataFile(source: io.Source) = {

    implicit val formats = DefaultFormats

    val json = source.mkString

    val products = parse(json)

    val dataSet =
      (Map[String, Product]() /: (products \ "products").extract[List[Product]]) { (map, item) =>
        map + (item.name.toLowerCase() -> item)
      }

    dataSet
  }

}

