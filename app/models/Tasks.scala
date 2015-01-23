package models

import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.MySQLDriver.simple._
import java.sql.Date
import models.Tables._

object Tasks {
  // connection
  val database = Database.forDataSource(DB.getDataSource())

  def findById(id : Int) = database.withTransaction { implicit session: Session =>
    Task.filter(_.taskId === id).first
  }

  def find(name : String) = database.withTransaction { implicit session: Session =>
    var tasks = Task.sortBy(_.name)
    tasks = if (name.isEmpty) tasks else tasks.filter(_.name like s"%${name}%")
    tasks.invoker.list
  }

  def create(e: TaskRow) = database.withTransaction { implicit session: Session =>
    Task.insert(e)
  }

  def update(e: TaskRow) = database.withTransaction { implicit session: Session =>
    Task.update(e)
  }

  def delete(id: Int) = database.withTransaction { implicit session: Session =>
    Task.filter(_.taskId === id).delete
  }
}
