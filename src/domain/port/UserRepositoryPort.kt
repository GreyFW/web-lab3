package port

import model.User

interface UserRepositoryPort {              // по CRUD
    fun create(user: User): User            // :C
    fun findById(id: Integer): User?        // :R - один и все пользователи
    fun findAll(): List<User>
    fun update(user: User): User            // :U
    fun delete(id: Integer)                 // :D
}