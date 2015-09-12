package com.example.passwordvalidator

import org.scalatest.FunSpec

/**
  * ## Password strength indicator
  *
  * ### Problem statement
  *
  * Create a program that determines the complexity of a given password based on
  * these rules:
  *
  * - A very weak password contains only numbers and is less than 8 characters
  * - A weak password contains only letters and is less than 8 characters
  * - Astrongpasswordcontainslettersandatleast1number and is at least 8 characters
  * - A very strong password contains letters, numbers, and special characters and
  *   is at least 8 characters.
  *
  * ### Example Output
  *
  *     The password '12345' is a very weak password
  *     The password 'abcdef' is a weak password
  *     The password 'abc123xyz' is a strong password
  *     The password '1337h@xor!' is a very strong password.
  *
  **/

class PasswordValidatorSpec extends FunSpec {

  describe("measure") {
    it("returns Very Weak") {
      assert(PasswordValidator.measure("12345") == "very weak")
    }

    it("returns Weak") {
      assert(PasswordValidator.measure("abcdef") == "weak")
    }

    it("returns Strong") {
      assert(PasswordValidator.measure("abc123xyz") == "strong")
    }

    it("returns Very Strong") {
      assert(PasswordValidator.measure("1337h@xor!") == "very strong")
    }
  }

  describe("testPassword") {
    it("is a very weak password") {
      assert(PasswordValidator.testPassword("12345") == (1, -1))
      assert(PasswordValidator.testPassword("1285858585345") == (1, 1))
    }

    it("is a weak password") {
      assert(PasswordValidator.testPassword("abcdef") == (2, -1))
    }

    it("is a strong password") {
      assert(PasswordValidator.testPassword("abc123xyz") == (3, 1))
    }

    it("is a very strong password") {
      assert(PasswordValidator.testPassword("1337h@xor!") == (7, 1))
    }
  }

  describe("Compare the string length") {
    it("returns -1") {
      assert(PasswordValidator.compareLength("small") === -1)
    }
    it("returns 0") {
      assert(PasswordValidator.compareLength("jakdifls") === 0)
    }
    it("returns 1") {
      assert(PasswordValidator.compareLength("jfkdjfialdie") === 1)
    }
  }

  describe("matchNumbers") {
    it("returns 1 for numbers only") {
      assert(PasswordValidator.matchNumbers("123456") === 1)
    }

    it("returns 0 when no numbers are present") {
      assert(PasswordValidator.matchNumbers("ajbidle") === 0)
    }

    it("returns 1 when some numbers are present") {
      assert(PasswordValidator.matchNumbers("ajbi2dl3e") === 1)
    }
  }

  describe("matchLetters") {
    it("returns 2 for letters only") {
      assert(PasswordValidator.matchLetters("akfkdladk") === 2)
    }

    it("returns 0 when no letters are present") {
      assert(PasswordValidator.matchLetters("464383728") === 0)
    }

    it("returns 2 when some letters are present") {
      assert(PasswordValidator.matchLetters("fjaskkjfi4646464") == 2)
    }
  }

  describe("matchSpecialChars") {
    it("returns 4 for special characters") {
      assert(PasswordValidator.matchSpecialChars("&*$%$#@@") === 4)
    }

    it("returns 0 when no special characters are present") {
      assert(PasswordValidator.matchSpecialChars("kfjlskdie828282") === 0)
    }

    it("returns 4 when some special characters are present") {
      assert(PasswordValidator.matchSpecialChars("kfjl$$$%skdie828282") === 4)
    }
  }
}

