package com.example.lab3.application.service

import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.port.DishRepositoryPort
import com.example.lab3.application.exception.NotFoundByIdException
import org.springframework.stereotype.Service

@Service
class DishService(
    private val dishRepository: DishRepositoryPort
) {

    fun create(dish: Dish): Dish = dishRepository.create(dish)

    fun getById(id: Long): Dish =
        dishRepository.findById(id) ?: throw NotFoundByIdException("Dish", id)

    fun update(id: Long, updatedDish: Dish): Dish {
        val existingDish = dishRepository.findById(id) ?: throw NotFoundByIdException("Dish", id)
        val dishToSave = existingDish.copy(
            name = updatedDish.name,
            description = updatedDish.description,
            price = updatedDish.price,
            isAvailable = updatedDish.isAvailable
        )
        return dishRepository.update(dishToSave)
    }

    fun delete(id: Long) {
        val existingDish = dishRepository.findById(id) ?: throw NotFoundByIdException("Dish", id)
        dishRepository.delete(existingDish.id)
    }

    fun getAll(namePart: String? = null): List<Dish> =
        dishRepository.findAll(namePart)
}