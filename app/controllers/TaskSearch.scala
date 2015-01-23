package controllers

import play.api._
import play.api.mvc._
import models.Tables._
import models.Tasks
import play.api.data._
import play.api.data.Forms._
import org.joda.time._


case class TaskSearchForm(name : String)

object TaskSearch extends Controller {
  val taskSearchForm = Form(
    mapping(
      "name" -> text(maxLength = 50)
    )(TaskSearchForm.apply)(TaskSearchForm.unapply)
  )

  def index = Action { implicit request =>
    Ok(views.html.task.search(taskSearchForm)(null))
  }

  def search = Action { implicit request =>
    taskSearchForm.bindFromRequest.fold(
      hasErrors = { form =>
        val tasks = Tasks.find("")
        Ok(views.html.task.search(taskSearchForm.bindFromRequest)(tasks))
      },
      success = { form =>
        val tasks = Tasks.find(form.name)
        Ok(views.html.task.search(taskSearchForm.bindFromRequest)(tasks))
      }
    )
  }
}

