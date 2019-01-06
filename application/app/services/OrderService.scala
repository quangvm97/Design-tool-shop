package services

import com.google.inject.Inject
import order.{ Order, OrderRepository }
import models.{ Product, ProductTemplate }
import play.api.libs.json.{ JsObject, JsString }

class OrderService @Inject() (
  orderRepository: OrderRepository) {
  def toJson(order: Order): JsObject = JsObject(Seq(
    // format: OFF
    "id" -> JsString(order.id.toString()),
    "price" -> JsString(order.price.toString()),
    "number" -> JsString(order.number.toString()),
    "status" -> JsString(order.status.toString),
    "product_id" -> JsString(order.productId.toString)
  ))


  def toJsonWithProduct(order: Order, product: Product, productTemplate: ProductTemplate): JsObject = JsObject(Seq(
    "id" -> JsString(order.id.toString()),
    "price" -> JsString(product.price.toString()),
    "number" -> JsString(order.number.toString()),
    "status" -> JsString(order.status.toString),
    "product_id" -> JsString(order.productId.toString),
    "image" -> JsString(product.image),
    "nameProduct" -> JsString(product.name),
    "description" -> JsString(product.description),
    "nameReceiver" -> JsString(order.receiver.name),
    "phone" -> JsString(order.receiver.numberPhone),
    "address" -> JsString(order.receiver.address),
    "product_template_id" -> JsString(productTemplate.id.toString),
    "width" -> JsString(productTemplate.width.toString),
    "height" -> JsString(productTemplate.width.toString),
    "url" -> JsString(productTemplate.url),
    "right" -> JsString(productTemplate.right.toString),
    "left" -> JsString(productTemplate.left.toString),
    "top" -> JsString(productTemplate.top.toString),
    "bottom" -> JsString(productTemplate.top.toString)
  ))
}
