package product

import common.AbstractDao
import scalikejdbc._

import scala.util.Try

class ProductDao extends AbstractDao[ProductRecord] {
  val p = ProductRecord.syntax("p")
  val column = ProductRecord.column
  implicit val session: DBSession = AutoSession
  override def findByIdString(idString: String): Try[ProductRecord] = ???

  override def findAll(): Try[Seq[ProductRecord]] = Try {
    sql"select * from myapp.product"
      .map(ProductRecord(p)).list().apply()
  }

  override def store(t: ProductRecord): Try[ProductRecord] = ???

  override def updateRecord(t: ProductRecord): Try[Int] = ???

  override def destroy(idString: String): Try[Int] = ???
}
