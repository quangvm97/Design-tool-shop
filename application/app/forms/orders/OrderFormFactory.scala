package forms.orders

import play.api.data.Form
import play.api.data.Forms._

case class OrderFormItem(
  userId: String,
  productId: String,
  number: String)

object OrderFormFactory {
  def orderItemForm: Form[OrderFormItem] = Form(
    mapping(
      "userId" -> nonEmptyText,
      "productId" -> nonEmptyText,
      "number" -> nonEmptyText)(OrderFormItem.apply)(OrderFormItem.unapply))
}

