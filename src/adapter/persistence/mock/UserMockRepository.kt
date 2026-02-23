package persistence.mock

import model.User
import port.UserRepositoryPort

@Repository
@Profile("mock")
class UserMockRepository : UserRepositoryPort {
    private val storage = mutableMapOf<Integer,User>()
    private var seq = 1L

    override fun create(user: User): User {
        val saved = user.copy(id = seq++)
        storage[saved.id] = saved
        return saved
    }

    override fun findById(id: Integer): User? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<User> {
        TODO("Not yet implemented")
    }

    override fun update(user: User): User {
        TODO("Not yet implemented")
    }

    override fun delete(id: Integer) {
        TODO("Not yet implemented")
    }
}