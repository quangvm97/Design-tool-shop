package order

import common.AbstractDao
import scalikejdbc.{AutoSession, DBSession}

import scala.util.Try

class OrderDAO extends AbstractDao{

  def store(order: OrderRecord)(implicit s: DBSession = AutoSession):Try[OrderRecord] = Try{
    val id = sql"INSERT INTO myapp.order (user_id, name_receiver, number_phone, address, created_at, total, product_id, status, number) VALUES (${order.userId} ,${order.nameReciver}, ${order.numberPhone},${order.address}, ${order.createdAt},${order.total}, ${order.total}, ${order.productId}, ${order.status}, ${order.number})"
      .updateAndReturnGeneratedKey().apply().toInt
    order.copy(id = id)
  }

  override def findByIdString(idString: String): Try[Nothing] = ???

  override def findAll: Try[Seq[Nothing]] = ???

  override def store(t: Nothing): Try[Nothing] = ???

  override def updateRecord(t: Nothing): Try[Int] = ???

  override def destroy(idString: String): Try[Int] = ???
}
