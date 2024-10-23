package com.example.foodapp.presentation.verification

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.domain.model.Response
import com.example.foodapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authRepository: AuthRepository
): ViewModel(){

    var response by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    init {
        sendEmail()
    }

    fun sendEmail() = viewModelScope.launch {
        val responseSendEmail = authRepository.sendEmailVerification()
        if(responseSendEmail == Response.Success(true)) response = Response.Success(true)
        else verifyEmail()
    }

    private fun verifyEmail() = viewModelScope.launch {
        Utils.showMessage(context, "The Email is send")
        response = Response.Loading
        response = authRepository.reload()
    }
}