package models

import java.util.UUID

import com.google.inject.Inject
import common.{ AbstractUserRepository, UserId }
import models.User
import user.{ UserDAO, UserRecord }

import scala.util.Try

class UserRepository @Inject() (userDao: UserDAO) extends AbstractUserRepository[User, UserRecord] {
  val dao = userDao

  def findByEmail(accountId: String): Try[User] =
    dao.getUserByEmail(accountId).map(record2Entity)

  def record2Entity(record: UserRecord): User = {
    User(
      id = UserId(record.id),
      name = record.name,
      email = record.email,
      passwordEncrypt = record.password)
  }

  override def findByAccountIdString(accountIdString: String): Try[User] = ???

  override def entity2Record(entity: User): UserRecord = ???
}
