package product

import scalikejdbc._

case class ProductRecord(
                          id:                              Long,
                          name:                            String,
                          image:                           String,
                          description:                     String,
                          price:                           Long
                    )

object ProductRecord extends SQLSyntaxSupport[ProductRecord] {
  override val tableName = "product"

  def apply(c: SyntaxProvider[ProductRecord])(rs: WrappedResultSet): ProductRecord = apply(c.resultName)(rs)

  def apply(c: ResultName[ProductRecord])(rs: WrappedResultSet): ProductRecord = ProductRecord(
    id                              = rs.long(c.id),
    name                            = rs.string(c.name),
    image                           = rs.string(c.image),
    description                     = rs.string(c.description),
    price                           = rs.long(c.price)
   )
}