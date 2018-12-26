package order

import org.joda.time.DateTime
import scalikejdbc._
import user.UserRecord

case class OrderRecord (
                       id: Long,
                       userId: Long,
                       nameReciver: String,
                       numberPhone: String,
                       address: String,
                       createdAt: DateTime,
                       total: Long,
                       productId: Long,
                       status: Long,
                       number: Long
                       )



object OrderRecord extends SQLSyntaxSupport[OrderRecord]{
  override val tableName = "order"

  def apply(c: SyntaxProvider[OrderRecord])(rs: WrappedResultSet): OrderRecord = apply(c.resultName)(rs)
  def apply(c:ResultName[OrderRecord])(rs: WrappedResultSet): OrderRecord = OrderRecord(
    id = rs.long(c.id),
    userId = rs.long(c.userId),
    nameReciver = rs.string(c.nameReciver),
    numberPhone = rs.string(c.numberPhone),
    address = rs.string(c.numberPhone),
    createdAt = rs.get(c.createdAt),
    total = rs.long(c.total),
    productId = rs.long(c.product_id),
    status = rs.long(c.status),
    number =  rs.long(c.number))
}
