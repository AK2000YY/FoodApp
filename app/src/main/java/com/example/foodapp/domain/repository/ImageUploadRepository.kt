package com.example.foodapp.domain.repository

import android.graphics.Bitmap
import com.example.foodapp.domain.model.Response

interface ImageUploadRepository {

    suspend fun uploadImage(bitmap: Bitmap): Response<String>

}