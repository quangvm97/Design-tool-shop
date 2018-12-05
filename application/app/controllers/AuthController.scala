package controllers

import applications.services.AbstractSecured
import com.google.inject.Inject
import forms.login.LoginFormFactory
import models.UserRepository
import services.{ ResponseService, UserService }
import play.api.i18n.Messages
import play.api.mvc.{ Action, AnyContent, ControllerComponents, Security }

import scala.util.{ Failure, Success }

class AuthController @Inject() (
  userRepository: UserRepository,
  userService: UserService,
  cc: ControllerComponents) extends AbstractSecured(userRepository, cc) {
  def login: Action[AnyContent] = Action { implicit request =>
    LoginFormFactory.userLoginForm.bindFromRequest.fold(
      errors => Ok(ResponseService.badRequest(Some(errors.errorsAsJson))),
      formData => userRepository.findByEmail(formData.accountId) match {
        case Success(user) =>
          if (user.passwordMatch(formData.password))
            Ok(ResponseService.success(data = Seq(userService.toJson(user))))
              .withSession(Security.username -> user.email)
          else
            Ok(ResponseService.badRequest("password", Messages("user.invalid.password")))
        case Failure(err) => err match {
          case _: Exception => Ok(ResponseService.badRequest("user", Messages("user.invalid.account.id")))
          case _ => Ok(ResponseService.serverError(Messages("system.fails")))
        }
      })
  }
}
