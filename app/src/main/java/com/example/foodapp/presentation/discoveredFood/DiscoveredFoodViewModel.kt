package com.example.foodapp.presentation.discoveredFood

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.domain.model.Food
import com.example.foodapp.domain.model.Response
import com.example.foodapp.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DiscoveredFoodViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val foodRepository: FoodRepository
): ViewModel() {

    private val _foods = MutableStateFlow<List<Food?>>(emptyList())
    val foods: StateFlow<List<Food?>> = _foods.asStateFlow()

    var updateResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set


    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        foodRepository.getFood()
            .catch {
                Log.d("AK2000YYE", it.toString())
                Utils.showMessage(context, it.message)
            }
            .collect{
                Log.d("AK2000YY", it.toString())
                _foods.value = it
            }
    }

    fun updateFood(id: String, isFavour: Boolean) = viewModelScope.launch {
        updateResponse = Response.Loading
        updateResponse = foodRepository.updateFood(id, isFavour)
    }

}