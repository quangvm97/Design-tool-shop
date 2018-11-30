package User

import common.AbstractDao
import scalikejdbc._

import scala.util.Try

/**
 * Created by thangkc on 17/12/2015.
 */

class UserDAO extends AbstractDao[UserRecord]{

  val u = UserRecord.syntax("u")
  val column = UserRecord.column
  implicit val session: DBSession = AutoSession

  def getUserByEmail(email: String): Try[Option[UserRecord]] = Try {
    withSQL {
      select.from(UserRecord as u).where.eq(u.email, email)
    }.map(UserRecord(u)).single().apply()
  }

  override def findAll: Try[Seq[UserRecord]] = ???

  override def updateRecord(t: UserRecord): Try[Int] = ???

  override def destroy(idString: String): Try[Int] = ???

  override def store(t: UserRecord): Try[UserRecord] = ???

  override def findByIdString(idString: String): Try[UserRecord] = ???
}
