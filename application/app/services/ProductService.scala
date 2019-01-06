package services

import javax.inject.Inject
import models.{Product, ProductRepository, ProductTemplateRepository, ProductTemplate}
import play.api.libs.json.{JsNumber, JsObject, JsString}

import scala.util.Try

class ProductService @Inject() (
  productRepository: ProductRepository,
  productTemplateRepository: ProductTemplateRepository                             ) {
  def toJsonList(product: Seq[Product]): Seq[JsObject] = {
    product.map(row => {toJson(row, productTemplateRepository.findProductTemplateByProductId(row.id).get)})
  }

  def toJson(product: Product, productTemplate: ProductTemplate): JsObject = JsObject(Seq(
    "id" -> JsString(product.id.toString()),
    "name" -> JsString(product.name),
    "image" -> JsString(product.image),
    "description" -> JsString(product.description),
    "price" -> JsNumber(product.price),
    "width" -> JsString(productTemplate.width.toString),
    "height" -> JsString(productTemplate.width.toString),
    "url" -> JsString(productTemplate.url),
    "right" -> JsString(productTemplate.right.toString),
    "left" -> JsString(productTemplate.left.toString),
    "top" -> JsString(productTemplate.top.toString),
    "bottom" -> JsString(productTemplate.top.toString)
  ))

  def findAll(): Try[Seq[Product]] = productRepository.findAll
}

