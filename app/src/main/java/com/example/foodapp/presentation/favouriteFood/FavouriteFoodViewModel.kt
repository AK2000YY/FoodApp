package com.example.foodapp.presentation.favouriteFood

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.domain.model.Food
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
class FavouriteFoodViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val foodRepository: FoodRepository
): ViewModel() {

    private val _foods = MutableStateFlow<List<Food?>>(emptyList())
    val foods: StateFlow<List<Food?>> = _foods.asStateFlow()


    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        foodRepository.getFood()
            .catch {
                Utils.showMessage(context, it.message)
            }
            .collect{
                _foods.value = it.filter {
                    it?.favour == true
                }
            }
    }

}