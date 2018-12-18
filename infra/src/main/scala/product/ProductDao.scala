package product

import common.AbstractDao

import scala.util.Try

class ProductDao extends AbstractDao [ProductRecord]{
  override def findByIdString(idString: String): Try[ProductRecord] = ???

  override def findAll(): Try[Seq[ProductRecord]] =

  override def store(t: ProductRecord): Try[ProductRecord] = ???

  override def updateRecord(t: ProductRecord): Try[Int] = ???

  override def destroy(idString: String): Try[Int] = ???
}
