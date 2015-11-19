package com.example.countfiles

import akka.actor._

class HollywoodActor() extends Actor {
  def receive = {
    case message => println(s"playing the role of $message")
  }
}

object HollywoodActorApp extends App {

  val system = ActorSystem("sample")

  val depp = system.actorOf(Props[HollywoodActor])

  depp ! "Wonka"

  system.shutdown()

}

