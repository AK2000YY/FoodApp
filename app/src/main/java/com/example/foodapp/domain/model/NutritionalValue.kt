package com.example.foodapp.domain.model

import com.google.gson.annotations.SerializedName

data class NutritionalValue(
    val id: Int? = null,
    val name: String? = null,
    @SerializedName("Energy")
    val energy: String? = null,
    @SerializedName("Fats")
    val fats: String? = null,
    @SerializedName("saturated fatty acids")
    val saturatedFattyAcids: String? = null,
    @SerializedName("Sugars")
    val sugars: String? = null,
    @SerializedName("Proteins")
    val proteins: String? = null
)
