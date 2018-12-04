package services

import com.google.inject.Inject
import models.{ User, UserRepository }
import play.api.libs.json.{ JsObject, JsString }

class UserService {
  def toJson(user: User): JsObject = JsObject(Seq(
    // format: OFF
    "id" -> JsString(user.id.value.toString()),
    "accountId" -> JsString(user.email),
    "name" -> JsString(user.name)))
}
