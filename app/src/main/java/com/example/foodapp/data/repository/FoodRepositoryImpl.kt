package com.example.foodapp.data.repository

import com.example.foodapp.domain.model.Food
import com.example.foodapp.domain.model.Response
import com.example.foodapp.domain.repository.FoodRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.getField
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val collectionReference: CollectionReference
): FoodRepository {

    override suspend fun getFood(): Flow<List<Food?>> = callbackFlow {
        val snapshotListener = collectionReference.addSnapshotListener { snapshot, e ->
            if(e!=null) {
                close(e)
                return@addSnapshotListener
            }
            val foods = snapshot?.documents?.map {
                Food(
                    id = it.id,
                    image = it.getField("image"),
                    name = it.getField("name"),
                    date = it.getField("date"),
                    favour = it.getField("favour")
                )
            } ?: emptyList()
            trySend(foods)
        }
        awaitClose{ snapshotListener.remove() }
    }

    override suspend fun addFood(food: Food): Response<Boolean> =
        try {
            collectionReference.add(food).await()
            Response.Success(true)
        }catch (e: Exception) {
            Response.Failure(e)
        }

    override suspend fun updateFood(id: String, isFavour: Boolean): Response<Boolean> =
        try {
            collectionReference.document(id).update(mapOf(
                "favour" to !isFavour
            )).await()
            Response.Success(true)
        }catch (e: Exception) {
            Response.Failure(e)
        }

    override suspend fun deleteFood(id: String): Response<Boolean> =
        try {
            collectionReference.document(id).delete().await()
            Response.Success(true)
        }catch (e: Exception) {
            Response.Failure(e)
        }


}