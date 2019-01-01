package controllers
import applications.services.AbstractSecured
import forms.orders.OrderFormFactory
import javax.inject.Inject
import models.UserRepository
import order.{ Order, OrderRepository, OrderStatus, Receiver }
import org.joda.time.DateTime
import play.api.i18n.Messages
import play.api.mvc.{ AbstractController, ControllerComponents, Security }
import services.{ OrderService, ResponseService }

import scala.util.{ Failure, Success }

class OrderController @Inject() (cc: ControllerComponents, orderRepository: OrderRepository, orderService: OrderService, userRepository: UserRepository) extends AbstractSecured(userRepository, cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def saveItemToCart() = Action { implicit request =>
    OrderFormFactory.orderItemForm.bindFromRequest.fold(
      errors => {
        Ok(ResponseService.badRequest(Some(errors.errorsAsJson)))
      },
      formData => {
        val newOrder = Order(
          0,
          formData.userId.toInt,
          Receiver("Fake", "Fake", "Fake"),
          DateTime.now,
          1,
          formData.productId.toInt,
          OrderStatus.DRAFT,
          formData.number.toInt)
        orderRepository.saveToCart(newOrder) match {
          case Success(order) =>
            Ok(ResponseService.success(data = Seq(orderService.toJson(order))))
          case Failure(error: Error) =>
            Ok(ResponseService.badRequest("user", Messages(error.toString)))
        }

      })
  }

  def payTotal(id: Long) = Action { implicit request =>
    orderRepository.findOrderDrafByUserId(id) match {
      case Success(listOrder) => {
        var total = 0L
        listOrder.foreach(order => total = total + (order.price + order.number))
        Ok(ResponseService.success(data = listOrder.map(orderService.toJson(_))))
      }
      case Failure(error: Error) =>
        Ok(ResponseService.badRequest("order", Messages(error.toString)))

    }
  }
}

