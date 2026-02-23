package com.example.lab3.adapter.persistence.mock

import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.port.DishRepositoryPort
import com.example.lab3.application.exception.NotFoundByIdException
import org.springframework.stereotype.Repository
import java.math.BigDecimal

class DishMockRepository : DishRepositoryPort {
    private val dishesStorage = mutableMapOf<Long, Dish>()
    private var idCounter = 1L

    override fun create(dish: Dish): Dish {
        val existingDish = dishesStorage.values.find { it.name == dish.name }
        if (existingDish != null ) {
            throw
        }
    }
    override fun findById(id: Long): Dish?
    override fun findAll(namePart: String?): List<Dish>
    override fun update(dish: Dish): Dish
    override fun delete(id: Long)
}