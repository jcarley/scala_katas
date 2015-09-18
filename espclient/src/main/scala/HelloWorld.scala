package com.example.espclient

import dispatch._, Defaults._

// import java.io._
// import org.apache.http.{HttpEntity, HttpResponse}
// import org.apache.http.client._
// import org.apache.http.client.methods.HttpGet
// import org.apache.http.impl.client.DefaultHttpClient
// import scala.collection.mutable.StringBuilder
// import org.apache.http.params.HttpConnectionParams
// import org.apache.http.params.HttpParams

// import org.apache.commons._
import org.apache.http._
// import org.apache.http.client._
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import java.util.ArrayList
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity

case class Person(firstName: String, lastName: String, age: Int)

object HelloWorld {
  // client_id: 'y8u59tcu9gy7jmmv8jrcwbyf'
  // client_secret: '6FTGN3GpECXQkgRhBgVVwsJwQPAe9JGzYpTbT8aEVaaVr'
  // esp_username: 'jeff.carley@gettyimages.com'
  // esp_password: 'letmein123'
  // base_uri: 'https://esp-sandbox.api.gettyimages.com/esp'
  //
  // uri = URI('https://api.gettyimages.com/oauth2/token')
  // Content-Type: application/x-www-form-urlencoded; charset=UTF-8

  def main(args: Array[String]) {
    // val myRequest = url("https://api.gettyimages.com/oauth2/token")
    val mySecureHost = host("api.gettyimages.com").secure
    val myRequest = mySecureHost / "oauth2" / "token"
    myRequest.setContentType("application/x-www-form-urlencoded", "UTF-8")
    myRequest << Map(
      "client_id" -> "xzn9qhy2avaxtp84vaq2xj74",
      "client_secret" -> "GyYFmSUTSk2WHsAyTZfX3yhFWc3mw6GgyRMHurzpXdBum",
      "grant_type" -> "password",
      "esp_username" -> "jeff.carley@gettyimages.com",
      "esp_password" -> "letmein123"
      )

    for(str <- Http(myRequest OK as.String)) {
      println(str)
    }

  }

  // def myPostWithParams = myPost.addParameter("key", "value")

  def post() {

    // create our object as a json string
    // val spock = new Person("Leonard", "Nimoy", 82)
    // val spockAsJson = new Gson().toJson(spock)

    // add name value pairs to a post object
    val post = new HttpPost("http://localhost:8080/posttest")
      val nameValuePairs = new ArrayList[NameValuePair]()

      nameValuePairs.add(new BasicNameValuePair("JSON", "world"))
      post.setEntity(new UrlEncodedFormEntity(nameValuePairs))
      post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));

      // send the post request
      val client = new DefaultHttpClient
      val response = client.execute(post)
      println("--- HEADERS ---")
      response.getAllHeaders.foreach(arg => println(arg))
  }

  // def getRestContent(url: String, connectionTimeout: Int, socketTimeout: Int): String = {
      // val httpClient = buildHttpClient(connectionTimeout, socketTimeout)
      // val httpResponse = httpClient.execute(new HttpGet(url))
      // val entity = httpResponse.getEntity
      // var content = ""
      // if (entity != null) {
        // val inputStream = entity.getContent
        // content = scala.io.Source.fromInputStream(inputStream).getLines.mkString
        // inputStream.close
      // }
      // httpClient.getConnectionManager.shutdown
      // content
  // }

  // def buildHttpClient(connectionTimeout: Int, socketTimeout: Int): DefaultHttpClient = {
    // val httpClient = new DefaultHttpClient
    // val httpParams = httpClient.getParams
    // HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeout)
    // HttpConnectionParams.setSoTimeout(httpParams, socketTimeout)
    // httpClient.setParams(httpParams)
    // httpClient
  // }
}

