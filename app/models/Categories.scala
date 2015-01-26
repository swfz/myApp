package models

import scala.runtime.ScalaRunTime._
import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.MySQLDriver.simple._
import java.sql.Date
import models.Tables._

object Categories {
  // connection
  val database = Database.forDataSource(DB.getDataSource())

  def options: Seq[(String,String)] = database.withTransaction { implicit session: Session =>
    Category.sortBy(_.categoryId).list.map(c => c.categoryId.toString -> c.name.get )
  }
  def all() = database.withTransaction { implicit session: Session =>
    Category.sortBy(_.categoryId).list
  }
}
