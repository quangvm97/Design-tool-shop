package models

import User.{UserDAO, UserDao}
import com.google.inject.Inject
import common.AbstractUserRepository

class UserRepository @Inject()(userDao: UserDAO
                              ) extends AbstractUserRepository{

}
