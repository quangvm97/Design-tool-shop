package order

import org.joda.time.DateTime
import scalikejdbc._
import user.UserRecord
import scalikejdbc.jodatime.JodaParameterBinderFactory._
// If you need TypeBinder for joda-time classes
import scalikejdbc.jodatime.JodaTypeBinder._

case class OrderRecord(
  id: Long,
  userId: Long,
  nameReciver: String,
  numberPhone: String,
  address: String,
  createdAt: DateTime,
  price: Long,
  productId: Long,
  status: String,
  number: Long)

object OrderRecord extends SQLSyntaxSupport[OrderRecord] {
  override val tableName = "order"

  def apply(c: SyntaxProvider[OrderRecord])(rs: WrappedResultSet): OrderRecord = apply(c.resultName)(rs)
  def apply(c: ResultName[OrderRecord])(rs: WrappedResultSet): OrderRecord = OrderRecord(
    id = rs.long(c.id),
    userId = rs.long(c.userId),
    nameReciver = rs.string(c.nameReciver),
    numberPhone = rs.string(c.numberPhone),
    address = rs.string(c.numberPhone),
    createdAt = rs.get[DateTime](c.createdAt),
    price = rs.long(c.price),
    productId = rs.long(c.productId),
    status = rs.string(c.status),
    number = rs.long(c.number))
}
