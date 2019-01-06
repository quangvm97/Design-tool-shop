package order

import common.AbstractDao
import scalikejdbc._

import scala.util.Try

class OrderDAO extends AbstractDao {

  def store(order: OrderRecord)(implicit s: DBSession = AutoSession): Try[OrderRecord] = Try {
    val id = sql"INSERT INTO myapp.order (user_id, name_receiver,number_phone,address,created_at,price,product_id, status, number) VALUES (${order.userId}, ${order.nameReciver}, ${order.numberPhone}, ${order.address},${order.createdAt}, ${order.price}, ${order.productId}, ${order.status}, ${order.number})"
      .updateAndReturnGeneratedKey().apply().toInt
    order.copy(id = id)
  }

  def findOrderDraftByUserId(id: Long)(implicit s: DBSession = AutoSession): Try[Seq[OrderRecord]] = Try {
    sql"SELECT * from myapp.order where user_id = ${id} and status = 'DRAFT' "
      .map(o => OrderRecord(o)).list().apply()
  }

  def storeReceiver(userId: Long, name: String, phone: String, address: String)(implicit s: DBSession = AutoSession): Try[Int] = Try {
    sql"UPDATE myapp.order SET name_receiver = ${name}, number_phone = ${phone}, address = ${address} where user_id = ${userId} and status = 'DRAFT'"
      .updateAndReturnGeneratedKey().apply().toInt
  }

  def destroy(orderId: Long)(implicit s: DBSession = AutoSession): Try[Int] = Try {
    sql"DELETE FROM myapp.order WHERE id = ${orderId}"
      .updateAndReturnGeneratedKey().apply().toInt
  }

  def updateStatus(orderId: Long, status: String)(implicit s: DBSession = AutoSession): Try[Int] = Try {
    sql"UPDATE myapp.order SET status = ${status} where id = ${orderId}"
      .updateAndReturnGeneratedKey().apply().toInt
  }

  def updateStatusAll(userId: Long, status: String)(implicit s: DBSession = AutoSession): Try[Int] = Try {
    sql"UPDATE myapp.order SET status = ${status} where user_id = ${userId}"
      .updateAndReturnGeneratedKey().apply().toInt
  }

  override def findByIdString(idString: String): Try[Nothing] = ???

  override def findAll: Try[Seq[Nothing]] = ???

  override def store(t: Nothing): Try[Nothing] = ???

  override def updateRecord(t: Nothing): Try[Int] = ???

  override def destroy(idString: String): Try[Int] = ???
}
