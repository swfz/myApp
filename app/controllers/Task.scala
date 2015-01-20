package controllers

import play.api._
import play.api.mvc._
import models.Tasks
// import models.db.common._
import play.api.data._
import play.api.data.Forms._
import org.joda.time._
import java.sql.Timestamp


case class TaskForm(
    name        : String,
    description : String,
    status      : Int,
    category_id : Int
)

object Task extends Controller {
  val taskForm = Form(
    mapping(
        "name"        -> nonEmptyText(maxLength = 50),
        "description" -> nonEmptyText(maxLength = 500),
        "status"      -> number,
        "category_id" -> number
      )(TaskForm.apply)(TaskForm.unapply)
    )

  // def index = Action { implicit request =>
  //   Ok(views.html.main("title", "test"))
  // }
  def index = Action { implicit request =>
    Ok(views.html.task.index("tesetmessage"))
  }

  def create = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      hasErrors = { form => 
        Ok(views.html.task.create(form))
      },
      success = { form =>
        Ok(views.html.task.index("susscess"))
      }
    )
  }
}
