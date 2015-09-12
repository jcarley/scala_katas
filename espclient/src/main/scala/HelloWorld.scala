package com.example.espclient

import java.io._
import org.apache.http.{HttpEntity, HttpResponse}
import org.apache.http.client._
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import scala.collection.mutable.StringBuilder
import org.apache.http.params.HttpConnectionParams
import org.apache.http.params.HttpParams

object HelloWorld {
  // client_id: 'y8u59tcu9gy7jmmv8jrcwbyf'
  // client_secret: '6FTGN3GpECXQkgRhBgVVwsJwQPAe9JGzYpTbT8aEVaaVr'
  // esp_username: 'jeff.carley@gettyimages.com'
  // esp_password: 'letmein123'
  // base_uri: 'https://esp-sandbox.api.gettyimages.com/esp'
  //
  // uri = URI('https://api.gettyimages.com/oauth2/token')

  def main(args: Array[String]) {
  }

  def getRestContent(url: String, connectionTimeout: Int, socketTimeout: Int): String = {
      val httpClient = buildHttpClient(connectionTimeout, socketTimeout)
      val httpResponse = httpClient.execute(new HttpGet(url))
      val entity = httpResponse.getEntity
      var content = ""
      if (entity != null) {
        val inputStream = entity.getContent
        content = io.Source.fromInputStream(inputStream).getLines.mkString
        inputStream.close
      }
      httpClient.getConnectionManager.shutdown
      content
  }

  def buildHttpClient(connectionTimeout: Int, socketTimeout: Int): DefaultHttpClient = {
    val httpClient = new DefaultHttpClient
    val httpParams = httpClient.getParams
    HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeout)
    HttpConnectionParams.setSoTimeout(httpParams, socketTimeout)
    httpClient.setParams(httpParams)
    httpClient
  }
}

