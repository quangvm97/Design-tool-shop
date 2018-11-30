package User

import scalikejdbc._

import scala.util.Try

/**
  * Created by thangkc on 17/12/2015.
  */

class UserDao {

  val u = UserRecord.syntax("u")
  val column = UserRecord.column
  implicit val session: DBSession = AutoSession

  def getUserByEmail(email: String): Try[Option[UserRecord]] = Try {
    withSQL {
      select.from(UserRecord as u).where.eq(u.email, email)
    }.map(UserRecord(u)).single().apply()
  }
}
