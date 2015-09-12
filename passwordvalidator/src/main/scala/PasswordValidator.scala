package com.example.passwordvalidator

object PasswordValidator {

  def measure(data: String): String = {
    testPassword(data) match {
      case (1, -1) => "very weak"
      case (2, -1) => "weak"
      case (3, 1) => "strong"
      case (7, 1) => "very strong"
    }
  }

  def testPassword(data: String):(Int, Int) = {
    (matchNumbers(data) + matchLetters(data) + matchSpecialChars(data), compareLength(data))
  }

  // val compareLength = (data: String) => data.length() compare 8

  def compareLength(data: String): Int = data.length() compare 8

  def matchNumbers(data: String): Int = matcher(data, "[0-9]+", 1)

  def matchLetters(data: String): Int = matcher(data, "[a-zA-Z]+", 2)

  def matchSpecialChars(data: String): Int = matcher(data, "[\\W]+", 4)

  def matcher(data: String, regex: String, retVal: Int): Int = {
    val rx = regex.r.unanchored
    data match {
      case rx() => retVal
      case _ => 0
    }
  }

}
