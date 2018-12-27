package services

import com.google.inject.Inject
import order.{ Order, OrderRepository }
import play.api.libs.json.{ JsObject, JsString }

class OrderService @Inject() (
  orderRepository: OrderRepository) {
  def toJson(order: Order): JsObject = JsObject(Seq(
    // format: OFF
    "id" -> JsString(order.id.toString())))
}
