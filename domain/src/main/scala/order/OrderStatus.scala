package order

import scala.util.Try

object OrderStatus extends Enumeration {
  val PENDING, READY_TO_SHIP, DELIVERED, FAIL_TO_DELIVERED, RETURN = Value

  def apply(orderStatus: OrderStatus.Value): Try[OrderStatus.Value] = Try {
    orderStatus match {
      case OrderStatus.PENDING  => OrderStatus.PENDING
      case OrderStatus.READY_TO_SHIP   => OrderStatus.READY_TO_SHIP
      case OrderStatus.DELIVERED   => OrderStatus.DELIVERED
      case OrderStatus.FAIL_TO_DELIVERED    => OrderStatus.FAIL_TO_DELIVERED
      case OrderStatus.RETURN    => OrderStatus.RETURN
      case _                     => throw new IllegalArgumentException()
    }
  }
}
