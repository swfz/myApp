package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def generate = Action {
    import scala.slick.codegen.SourceCodeGenerator
    val slickDriver = "scala.slick.driver.MySQLDriver"
    val jdbcDriver  = "com.mysql.jdbc.Driver"
    val url         = "jdbc:mysql://localhost/todo?user=root"
    val outputFolder = "app"
    val pkg         = "models"

    SourceCodeGenerator.main(
      Array(
        slickDriver,
        jdbcDriver,
        url,
        outputFolder,
        pkg
      )
    )
    Ok(views.html.index("Done"))
  }
}
