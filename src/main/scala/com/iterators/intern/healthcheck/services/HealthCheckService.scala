package com.iterators.intern.healthcheck.services

import com.iterators.intern.healthcheck.repositories.{HealthCheckRepository, HealthCheckRepositoryImpl}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait HealthCheckService {
  def getDBLatency: Future[Long]
}

class HealthCheckServiceImpl extends HealthCheckService {
  val healthCheckRepository: HealthCheckRepository = new HealthCheckRepositoryImpl

  def getDBLatency: Future[Long] = {
    val time = System.nanoTime()
    healthCheckRepository.checkResponse.map {
      _ => (System.nanoTime() - time)/1000
    }
  }
}
