package models

case class UserId(value: Long)

case class User(userId: UserId, name: String, email: String, password: String)
