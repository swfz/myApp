package models

import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.MySQLDriver.simple._
import java.sql.Date
import models.Tables._

object Tasks {
  // connection
  val database = Database.forDataSource(DB.getDataSource())

  // def findById(id : Int) = database.withTransaction { implicit session : Session =>
  //   Tasks.filter(_.id == id).first
  // }

  def create(e: TaskRow) = database.withTransaction { implicit session: Session =>
    Task.insert(e)
  }
}
