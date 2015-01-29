package controllers

import play.api._
import play.api.mvc._
import models.Tasks
// import models.db.common._
import play.api.data._
import play.api.data.Forms._
import org.joda.time._
import java.sql.Timestamp
import scala.runtime.ScalaRunTime._
import models.Tables._
import java.sql.Timestamp._


case class TaskForm(
    name        : String,
    description : String,
    status      : Int,
    category_id : Int,
    limit       : java.util.Date
)

object Task extends Controller {
  val taskForm = Form(
    mapping(
        "name"        -> nonEmptyText(maxLength = 50),
        "description" -> nonEmptyText(maxLength = 500),
        "status"      -> number,
        "category_id" -> number,
        "limit"       -> date
      )(TaskForm.apply)(TaskForm.unapply)
    )

  def index = Action { implicit request =>
    Ok(views.html.task.create(taskForm))
  }

  def create = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.task.create(form))
      },
      success = { form =>
        // println(stringOf(form))
        val task = TaskRow(
          taskId      = 0,
          name        = Option( form.name ),
          description = Option( form.description ),
          status      = form.status == 1,
          limit       = new java.sql.Timestamp( form.limit.getTime() ),
          categoryId  = form.category_id,
          lastUpdate  = new Timestamp(System.currentTimeMillis())
        )
        Tasks.create(task)
        Redirect(controllers.routes.Task.create).flashing(
          "success" -> "registered"
        )
      }
    )
  }

  def edit(id : Int) = Action { implicit request =>
    val task = Tasks.findById(id)
    // println(stringOf(task))
    val status = if(task._4) 1 else 0

    val form = TaskForm(
      task._2.get,
      task._3.get,
      status,
      task._6,
      task._7
    )
    Ok(views.html.task.edit(taskForm.fill(form))(id))
  }

  def update(id : Int) = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.task.edit(form)(id))
      },
      success = { form =>
        val task = TaskRow(
          taskId      = id,
          name        = Option( form.name ),
          description = Option( form.description ),
          status      = form.status == 1,
          categoryId  = form.category_id,
          limit       = new java.sql.Timestamp( form.limit.getTime() ),
          lastUpdate  = new Timestamp(System.currentTimeMillis())
        )
        // println("taskRow========================")
        // println(stringOf(task))
        Tasks.update(task)
        Redirect(controllers.routes.Task.edit(id)).flashing(
          "success" -> "done update"
        )
      }
    )
  }

  def delete(id : Int) = Action { implicit request =>
    Tasks.delete(id)
    Redirect(controllers.routes.TaskSearch.index.url, Map("name" -> Seq(""))).flashing(
      "success" -> s"ID : ${id}のデータを削除しました。"
    )
  }
}
