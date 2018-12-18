package services

import javax.inject.Inject
import models.ProductRepository
import models.Product
import play.api.libs.json.{JsObject, JsString}

import scala.util.Try

class ProductService @Inject()(
                                productRepository:                        ProductRepository
                              )
{
  def toJsonList(product: Seq[Product]): Seq[JsObject] = {
    product.map(row => toJson(row))
  }

  def toJson(product: Product) : JsObject = JsObject(Seq(
    "id" -> JsString(product.id.toString()),
    "name" -> JsString(product.name),
    "image" -> JsString(product.image),
    "description" -> JsString(product.description),
    "price" -> JsString(product.price.toString())
  ))

  def findAll():Try[Seq[Product]] = productRepository.findAll
}

