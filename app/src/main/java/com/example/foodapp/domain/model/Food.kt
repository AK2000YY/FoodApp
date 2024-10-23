package com.example.foodapp.domain.model

import com.google.firebase.firestore.Exclude

data class Food(
    @get: Exclude
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
    val date: String? = null
)
