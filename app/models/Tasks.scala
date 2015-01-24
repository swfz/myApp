package models

import scala.runtime.ScalaRunTime._
import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.MySQLDriver.simple._
import java.sql.Date
import models.Tables._

object Tasks {
  // connection
  val database = Database.forDataSource(DB.getDataSource())

  def findById(id : Int) = database.withTransaction { implicit session: Session =>
    val joinedTask = for {
      t <- Task if (t.taskId === id)
      c <- Category
      if t.categoryId === c.categoryId
    } yield (t.taskId,t.name,t.description,t.status,c.name,c.categoryId)
    // println(stringOf(joinedTask))
    // println(joinedTask.selectStatement)
    joinedTask.invoker.first
  }

  def find(name : String) = database.withTransaction { implicit session: Session =>
    val joinedTasks = for {
      t <- Task if (t.name like s"%${name}%")
      c <- Category
      if t.categoryId === c.categoryId
    } yield (t.taskId,t.name,t.description,t.status,c.name,c.categoryId)
    joinedTasks.invoker.list
  }

  def create(e: TaskRow) = database.withTransaction { implicit session: Session =>
    Task.insert(e)
  }

  def update(e: TaskRow) = database.withTransaction { implicit session: Session =>
    Task.filter(_.taskId === e.taskId).update(e)
  }

  def delete(id: Int) = database.withTransaction { implicit session: Session =>
    Task.filter(_.taskId === id).delete
  }
}
