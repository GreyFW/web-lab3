package com.example.lab3.adapter.persistence.mock

import com.example.lab3.application.exception.NotFoundByIdException
import com.example.lab3.application.exception.AlreadyExistsException
import com.example.lab3.domain.model.User
import com.example.lab3.domain.port.UserRepositoryPort
import org.springframework.stereotype.Repository

@Repository
class UserMockRepository : UserRepositoryPort {
    private val usersStorage = mutableMapOf<Long,User>()
    private var idCounter = 1L

    override fun create(user: User): User {
        val existingUser = usersStorage.values.find { it.email == user.email }
        if (existingUser != null ) {
            throw AlreadyExistsException("User", "email", user.email)
            ("User with email=${user.email} already exists")
        }
        val id = idCounter++
        val newUser = user.copy(id = id)
        usersStorage[id] = newUser
        return newUser
    }

    override fun findById(id: Long): User? {
        return usersStorage[id]
    }

    override fun findAll(): List<User> {
        return usersStorage.values.toList()
    }

    override fun update(user: User): User {
        val existingUser = usersStorage[user.id] ?: throw NotFoundByIdException("User", user.id)

        val updatedUser = existingUser.copy(
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            isActive = user.isActive
        )

        usersStorage[user.id] = updatedUser
        return updatedUser
    }

    override fun delete(id: Long) {
        val user = usersStorage[id] ?: throw NotFoundByIdException("User", id)
        usersStorage.remove(id)
    }
}