package port

import model.Dish

interface  DishRepositoryPort {
    // по CRUD
    fun create(dish: Dish): Dish            // :C
    fun findById(id: Integer): Dish?        // :R - одно и все блюда
    fun findAll(namePart: String?): List<Dish>
    fun update(dish: Dish): Dish            // :U
    fun delete(id: Integer)                 // :D
}