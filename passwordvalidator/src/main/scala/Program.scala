package com.example.passwordvalidator

object Program {
  def main(args: Array[String]) {
    val passwords = List(
      "12345",
      "abcdef",
      "abc123xyz",
      "1337h@xor!")
    for(password <- passwords) {
      println(s"The password '$password' is a ${PasswordValidator.measure(password)} password.")
    }
  }
}
