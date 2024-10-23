package com.example.foodapp.data.repository

import android.graphics.Bitmap
import com.example.foodapp.domain.model.Response
import com.example.foodapp.domain.repository.ImageUploadRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ImageUploadRepositoryImpl @Inject constructor(
    private val storageReference: StorageReference
): ImageUploadRepository {

    override suspend fun uploadImage(bitmap: Bitmap): Response<String> =
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val data = byteArrayOutputStream.toByteArray()
            val uri = storageReference
                .child("${FieldValue.serverTimestamp()}")
                .putBytes(data).await()
                .storage.downloadUrl.await()
            Response.Success(uri.toString())
        }catch (e: Exception) {
            Response.Failure(e)
        }
}