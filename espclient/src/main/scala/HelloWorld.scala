package com.example.espclient

import org.apache.http.client.fluent._
import org.apache.http.entity.ContentType

object HelloWorld {

  def main(args: Array[String]) {

    // Create request
    val content = Request.Post("https://api.gettyimages.com/auth/oauth2/token")
      // Add headers
      .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")

      // Add body
      .bodyForm(Form.form()
      .add("client_id", "y8u59tcu9gy7jmmv8jrcwbyf")
      .add("username", "YOUR USERNAME")
      .add("password", "YOUR PASSWORD")
      .add("client_secret", "6FTGN3GpECXQkgRhBgVVwsJwQPAe9JGzYpTbT8aEVaaVr")
      .add("grant_type", "password")
      .build())

      // Fetch request and return content
      .execute().returnContent()

      // Print content
      println(content)
  }

}

