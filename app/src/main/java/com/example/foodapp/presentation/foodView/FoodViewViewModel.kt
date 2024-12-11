package com.example.foodapp.presentation.foodView

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.domain.model.NutritionalValue
import com.example.foodapp.domain.model.Response
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoodViewViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    var response by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    var nutritionalValue by mutableStateOf(NutritionalValue())
    private var list by mutableStateOf<List<NutritionalValue>>(emptyList())

    init {
        list = parseJsonToItems(readJsonFromAssets())
    }

    private fun readJsonFromAssets(): String {
        return context.assets.open("data.json").use { inputStream ->
            inputStream.bufferedReader().use { it.readText() }
        }
    }

    private fun parseJsonToItems(json: String): List<NutritionalValue> {
        return Gson().fromJson(json, Array<NutritionalValue>::class.java).toList()
    }

    fun findItemByName(name: String) = viewModelScope.launch {
        response = Response.Loading
        nutritionalValue = list.firstOrNull { it.name == name } ?: NutritionalValue()
        response = Response.Success(true)
    }
}