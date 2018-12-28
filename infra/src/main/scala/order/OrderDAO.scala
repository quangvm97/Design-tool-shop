package order

import common.AbstractDao
import scalikejdbc._

import scala.util.Try

class OrderDAO extends AbstractDao {

  def store(order: OrderRecord)(implicit s: DBSession = AutoSession): Try[OrderRecord] = Try {
    val id = sql"INSERT INTO myapp.order (user_id, product_id, status, number) VALUES (${order.userId}, ${order.productId}, ${order.status}, ${order.number})"
      .updateAndReturnGeneratedKey().apply().toInt
    order.copy(id = id)
  }

  def findOrderDraftByUserId(id:Int)(implicit s: DBSession = AutoSession):Try[Seq[OrderRecord]] = Try{
    sql"SELECT * from myapp.order where user_id = ${id} and status = 'DRAFT' "
      .map(o => OrderRecord(o)).list().apply()
  }

  override def findByIdString(idString: String): Try[Nothing] = ???

  override def findAll: Try[Seq[Nothing]] = ???

  override def store(t: Nothing): Try[Nothing] = ???

  override def updateRecord(t: Nothing): Try[Int] = ???

  override def destroy(idString: String): Try[Int] = ???
}
