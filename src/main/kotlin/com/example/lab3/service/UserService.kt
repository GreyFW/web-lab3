package service

import model.User
import port.UserRepositoryPort

@Service
class UserService(
    private val userRepository: UserRepositoryPort
) {
    fun create(user: User): User = userRepository.create(user)

    fun getById(id: Long): User =
        userRepository.findById(id)
            ?: throw NotFoundException("User not found")

    fun getAll(): List<User> = userRepository.findAll()

    fun update(user: User): User = userRepository.update(user)

    fun delete(id: Long) = userRepository.delete(id)
}