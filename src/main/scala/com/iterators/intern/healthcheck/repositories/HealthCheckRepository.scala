package com.iterators.intern.healthcheck.repositories

import com.iterators.intern.healthcheck.repositories.HealthCheckRepository.db
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

trait HealthCheckRepository {
  def checkResponse: Future[_]
}

class HealthCheckRepositoryImpl extends HealthCheckRepository {
  def checkResponse: Future[_] = {
    db.run(sql"SELECT 1;".as[Int])
  }
}

object HealthCheckRepository {
  val db = Database.forConfig("mydb")
}
