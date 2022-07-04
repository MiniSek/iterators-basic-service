package com.iterators.intern.healthcheck.routers

import akka.http.scaladsl.server.Directives.{complete, get, onComplete, path}
import akka.http.scaladsl.server.Route
import com.iterators.intern.healthcheck.services.{HealthCheckService, HealthCheckServiceImpl}

import scala.util.{Failure, Success}


trait HealthCheckRouter {
  val healthCheckService: HealthCheckService = new HealthCheckServiceImpl

  val routes: Route = (get & path("healthcheck")) {
    onComplete(healthCheckService.getDBLatency) {
      case Success(latency) => complete(s"Latency is $latency microseconds")
      case Failure(_)    => complete(s"Database is unreachable")
    }
  }
}
