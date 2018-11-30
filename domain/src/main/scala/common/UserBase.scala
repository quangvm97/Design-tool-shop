package common

import play.api.libs.Crypto

/**
 * Created by thangkc on 12/01/2016.
 */
trait UserBase {
  val id: Option[Long]
  val identifier: IdBase
  val accountId: String
  val name: String
  val passwordEncrypt: String
  val phoneNumber: Option[String]
  val nameFurigana: Option[String]
  val email: String

  def passwordMatch(inputPassword: String): Boolean = inputPassword == Crypto.decryptAES(passwordEncrypt)

  lazy val idStr: String = id.fold("")(CommonService.createViewId(_, "U"))
}
