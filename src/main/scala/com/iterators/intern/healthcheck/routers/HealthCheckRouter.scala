package com.iterators.intern.healthcheck.routers

import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Route

trait HealthCheckRouter {
  val routes: Route = path("healthcheck") {
    get {
      complete("Healthcheck response")
    }
  }
}
