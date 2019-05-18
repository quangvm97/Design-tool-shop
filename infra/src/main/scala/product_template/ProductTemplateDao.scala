package product_template

import common.AbstractDao
import product.ProductRecord
import scalikejdbc._

import scala.util.Try

class ProductTemplateDao extends AbstractDao[ProductTemplateRecord] {
  implicit val session: DBSession = AutoSession

  def findByProductId(id: Long): Try[ProductTemplateRecord] = Try {
    sql"SELECT * from myapp.product_template where product_id = ${id}"
      .map(o => ProductTemplateRecord(o)).single().apply().getOrElse(throw new Exception("Couldn't find product with id: " + id))
  }
  override def findByIdString(idString: String): Try[Nothing] = ???

  override def findAll: Try[Seq[Nothing]] = ???

  override def store(t: ProductTemplateRecord): Try[ProductTemplateRecord] = Try {
    val id = sql"insert into myapp.product_template values (${t.id}, ${t.productId}, ${t.width}, ${t.height}, ${t.url}, ${t.right}, ${t.left}, ${t.top}, ${t.bottom})"
      .updateAndReturnGeneratedKey().apply().toInt
    t.copy(id = id)
  }

  override def updateRecord(t: ProductTemplateRecord): Try[Int] = ???

  override def destroy(idString: String): Try[Int] = ???
}

