package com.example.foodapp.domain.model

import android.graphics.Bitmap

data class Classification(
    val name: String? = null,
    val score: Float? = null,
    val image: Bitmap? = null
)
