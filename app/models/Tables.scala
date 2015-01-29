package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Category.ddl ++ Task.ddl
  
  /** Entity class storing rows of table Category
   *  @param categoryId Database column category_id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(Some()) */
  case class CategoryRow(categoryId: Int, name: Option[String] = Some(""))
  /** GetResult implicit for fetching CategoryRow objects using plain SQL queries */
  implicit def GetResultCategoryRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[CategoryRow] = GR{
    prs => import prs._
    CategoryRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table category. Objects of this class serve as prototypes for rows in queries. */
  class Category(_tableTag: Tag) extends Table[CategoryRow](_tableTag, "category") {
    def * = (categoryId, name) <> (CategoryRow.tupled, CategoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (categoryId.?, name).shaped.<>({r=>import r._; _1.map(_=> CategoryRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column category_id DBType(INT), AutoInc, PrimaryKey */
    val categoryId: Column[Int] = column[Int]("category_id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(Some()) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(Some("")))
  }
  /** Collection-like TableQuery object for table Category */
  lazy val Category = new TableQuery(tag => new Category(tag))
  
  /** Entity class storing rows of table Task
   *  @param taskId Database column task_id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(Some())
   *  @param description Database column description DBType(TEXT), Length(65535,true), Default(None)
   *  @param status Database column status DBType(BIT), Default(false)
   *  @param categoryId Database column category_id DBType(INT), Default(1)
   *  @param limit Database column limit DBType(DATETIME)
   *  @param lastUpdate Database column last_update DBType(TIMESTAMP) */
  case class TaskRow(taskId: Int, name: Option[String] = Some(""), description: Option[String] = None, status: Boolean = false, categoryId: Int = 1, limit: java.sql.Timestamp, lastUpdate: java.sql.Timestamp)
  /** GetResult implicit for fetching TaskRow objects using plain SQL queries */
  implicit def GetResultTaskRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Boolean], e3: GR[java.sql.Timestamp]): GR[TaskRow] = GR{
    prs => import prs._
    TaskRow.tupled((<<[Int], <<?[String], <<?[String], <<[Boolean], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table task. Objects of this class serve as prototypes for rows in queries. */
  class Task(_tableTag: Tag) extends Table[TaskRow](_tableTag, "task") {
    def * = (taskId, name, description, status, categoryId, limit, lastUpdate) <> (TaskRow.tupled, TaskRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (taskId.?, name, description, status.?, categoryId.?, limit.?, lastUpdate.?).shaped.<>({r=>import r._; _1.map(_=> TaskRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column task_id DBType(INT), AutoInc, PrimaryKey */
    val taskId: Column[Int] = column[Int]("task_id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(Some()) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(Some("")))
    /** Database column description DBType(TEXT), Length(65535,true), Default(None) */
    val description: Column[Option[String]] = column[Option[String]]("description", O.Length(65535,varying=true), O.Default(None))
    /** Database column status DBType(BIT), Default(false) */
    val status: Column[Boolean] = column[Boolean]("status", O.Default(false))
    /** Database column category_id DBType(INT), Default(1) */
    val categoryId: Column[Int] = column[Int]("category_id", O.Default(1))
    /** Database column limit DBType(DATETIME) */
    val limit: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("limit")
    /** Database column last_update DBType(TIMESTAMP) */
    val lastUpdate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("last_update")
  }
  /** Collection-like TableQuery object for table Task */
  lazy val Task = new TableQuery(tag => new Task(tag))
}