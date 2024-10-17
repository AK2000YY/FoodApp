package com.example.foodapp.presentation.signupLogin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.domain.model.Response
import com.example.foodapp.domain.model.Response.Success
import com.example.foodapp.domain.model.Response.Loading
import com.example.foodapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupLoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    var response by mutableStateOf<Response<Boolean>>(Success(false))
        private set


    var loginEmail by mutableStateOf("")
    var loginPassword by mutableStateOf("")

    var signupEmail by mutableStateOf("")
    var signupPassword by mutableStateOf("")
    var signupConfirmPassword by mutableStateOf("")


    fun login() = viewModelScope.launch {
        println("$loginEmail $loginPassword \n")
        response = Loading
        response = authRepository.login(loginEmail, loginPassword)
    }

    fun signup() = viewModelScope.launch {
        response = Loading
        response = authRepository.signup(signupEmail, signupPassword, signupConfirmPassword)
    }

}