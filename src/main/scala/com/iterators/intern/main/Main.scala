package com.iterators.intern.main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.iterators.intern.healthcheck.routers.HealthCheckRouter
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContextExecutor


object Main extends App with HealthCheckRouter {
  implicit val system: ActorSystem = ActorSystem()
  implicit val executor: ExecutionContextExecutor = system.dispatcher

  val config = ConfigFactory.load()

  Http().newServerAt(config.getString("http.interface"), config.getInt("http.port")).bindFlow(routes)
}
