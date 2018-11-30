package controllers

import applications.services.AbstractSecured
import com.google.inject.Inject
import forms.login.LoginFormFactory
import models.UserRepository
import services.ResponseService
import play.api.Play.current
import play.api.i18n.Messages
import play.api.i18n.Messages.Implicits._
import play.api.mvc.{Action, AnyContent, Security}

import scala.util.{Failure, Success}




class AuthController @Inject()(userRepositoty: UserRepository) extends AbstractSecured(userRepositoty){
  def login: Action[AnyContent] = Action { implicit request =>
    LoginFormFactory.userLoginForm.bindFromRequest.fold(
      errors => Ok(ResponseService.badRequest(Some(errors.errorsAsJson))),
      formData => userRepositoty.findByAccountIdString(formData.accountId) match {
        case Success(user) =>
          if (user.passwordMatch(formData.password))
            Ok(ResponseService.success(data = Seq(adminService.toJson(user))))
              .withSession(Security.username -> user.accountId)
          else
            Ok(ResponseService.badRequest("password", Messages("user.invalid.password")))
              case Failure(err) => err match {
              case _: EntityNotFound => Ok(ResponseService.badRequest("user", Messages("user.invalid.account.id")))
              case _                 => Ok(ResponseService.serverError(Messages("system.fails")))
              }
          })
  }
}
