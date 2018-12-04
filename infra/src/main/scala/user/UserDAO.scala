package user

import common.AbstractDao
import scalikejdbc._

import scala.util.Try

/**
 * Created by thangkc on 17/12/2015.
 */

class UserDAO extends AbstractDao[UserRecord] {

  val u = UserRecord.syntax("u")
  val column = UserRecord.column
  implicit val session: DBSession = AutoSession

  def getUserByEmail(email: String): Try[UserRecord] = Try {
    withSQL {
      select.from(UserRecord as u).where.eq(u.email, email)
    }.map(UserRecord(u)).single().apply() match {
      case Some(record) => record
      case None => throw new Exception("Couldn't find user with email: " + email)
    }
  }

  override def findAll: Try[Seq[UserRecord]] = ???

  override def updateRecord(t: UserRecord): Try[Int] = ???

  override def destroy(idString: String): Try[Int] = ???

  override def store(t: UserRecord): Try[UserRecord] = ???

  override def findByIdString(idString: String): Try[UserRecord] = ???
}
