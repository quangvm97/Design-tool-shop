package controllers

import applications.services.AbstractSecured
import com.google.inject.Inject
import forms.product.ProductFactory
import models.{ Product, ProductRepository, ProductTemplate, ProductTemplateRepository, User, UserRepository }
import play.api.i18n.Messages
import play.api.mvc.ControllerComponents
import services.{ ProductService, ResponseService }
import user.UserRecord
import scala.util.Random

import scala.util.{ Failure, Success }

class ProductController @Inject() (
  userRepository: UserRepository,
  productRepository: ProductRepository,
  productTemplateRepository: ProductTemplateRepository,
  productService: ProductService,
  cc: ControllerComponents) extends AbstractSecured[User, UserRecord](userRepository, cc) {
  val rand = new Random()
  def findAll() = withoutAuth { implicit request =>
    Ok(ResponseService.success(data = productService.toJsonList(productService.findAll().get), returnEmptyData = true))
  }

  def create() = Action { implicit request =>
    ProductFactory.productItemForm.bindFromRequest.fold(
      errors => {
        Ok(ResponseService.badRequest(Some(errors.errorsAsJson)))
      },
      formData => {
        val newProduct = Product(
          rand.nextInt(100000),
          formData.name,
          formData.image,
          formData.description,
          formData.price)
        productRepository.store(newProduct) match {
          case Success(product) =>
            val newProductTemplate = ProductTemplate(
              rand.nextInt(100000),
              product.id,
              formData.template.width,
              formData.template.height,
              formData.template.url,
              formData.template.left,
              formData.template.right,
              formData.template.top,
              formData.template.bottom)
            productTemplateRepository.store(newProductTemplate) match {
              case Success(productTemplate) => Ok(ResponseService.success(productService.toJson(product, productTemplate)))
              case Failure(error: Error) => Ok(ResponseService.badRequest("productTemplate", Messages(error.toString)))
            }
          case Failure(error: Error) => Ok(ResponseService.badRequest("product", Messages(error.toString)))
        }
      })
  }
}
