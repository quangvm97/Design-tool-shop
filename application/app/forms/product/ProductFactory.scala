package forms.product

import play.api.data.Form
import play.api.data.Forms._

case class ProductTemplateForm(
  width: Long,
  height: Long,
  url: String,
  right: Long,
  left: Long,
  top: Long,
  bottom: Long)
case class ProductCreateForm(name: String, image: String, description: String, price: Long, template: ProductTemplateForm)

object ProductFactory {
  def productItemForm: Form[ProductCreateForm] = Form(
    mapping(
      "name" -> nonEmptyText,
      "image" -> nonEmptyText,
      "description" -> nonEmptyText,
      "price" -> longNumber,
      "template" -> mapping(
        "width" -> longNumber,
        "height" -> longNumber,
        "url" -> nonEmptyText,
        "right" -> longNumber,
        "left" -> longNumber,
        "top" -> longNumber,
        "bottom" -> longNumber)(ProductTemplateForm.apply)(ProductTemplateForm.unapply))(ProductCreateForm.apply)(ProductCreateForm.unapply))
}
