package models

import com.google.inject.Inject
import product_template.{ ProductTemplateDao, ProductTemplateRecord }

import scala.util.Try

class ProductTemplateRepository @Inject() (productTemplateDao: ProductTemplateDao) {
  val dao = productTemplateDao

  def findProductTemplateByProductId(id: Long): Try[ProductTemplate] = {
    dao.findByProductId(id).map(record2Entity)
  }

  def store(productTemplate: ProductTemplate): Try[ProductTemplate] = {
    dao.store(entity2Record(productTemplate)).map(record2Entity)
  }

  def record2Entity(record: ProductTemplateRecord): ProductTemplate = {
    models.ProductTemplate(
      id = record.id,
      productId = record.productId,
      width = record.width,
      height = record.height,
      url = record.url,
      right = record.right,
      left = record.left,
      top = record.top,
      bottom = record.bottom)
  }

  def entity2Record(entity: ProductTemplate): ProductTemplateRecord = {
    ProductTemplateRecord(
      id = entity.id,
      productId = entity.productId,
      width = entity.width,
      height = entity.height,
      url = entity.url,
      left = entity.left,
      right = entity.right,
      top = entity.top,
      bottom = entity.bottom)
  }
}
