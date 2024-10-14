package com.example.foodapp.presentation.introduction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.domain.model.Response
import com.example.foodapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    var response by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    init {
        checkUser()
    }


    private fun checkUser() = viewModelScope.launch {
        response = Response.Loading
        delay(2000)
        response = authRepository.checkUser()
    }

}