package com.example.foodapp.domain.repository

import com.example.foodapp.domain.model.Food
import com.example.foodapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    suspend fun getFood(): Flow<List<Food?>>

    suspend fun addFood(food: Food): Response<Boolean>

    suspend fun updateFood(id: String, isFavour: Boolean): Response<Boolean>

    suspend fun deleteFood(id: String): Response<Boolean>

}