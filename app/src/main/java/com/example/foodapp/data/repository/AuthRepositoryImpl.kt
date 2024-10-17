package com.example.foodapp.data.repository

import com.example.foodapp.core.constant.Constant.PASSWORDS_DO_NOT_MATCH
import com.example.foodapp.core.constant.Constant.USER_NOT_FOUND
import com.example.foodapp.core.constant.Constant.USER_NOT_VERIFICATION
import com.example.foodapp.domain.model.Response
import com.example.foodapp.domain.model.Response.Success
import com.example.foodapp.domain.model.Response.Loading
import com.example.foodapp.domain.model.Response.Failure
import com.example.foodapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun checkUser(): Response<Boolean> =
        try {
            //check if user is found
            if(currentUser == null) throw Exception(USER_NOT_FOUND)
            //check user is verified
            if(! currentUser!!.isEmailVerified) throw Exception(USER_NOT_VERIFICATION)
            //user is found
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }

    override suspend fun signup(email: String, password: String, confirmPassword: String): Response<Boolean> =
        try {
            if(password != confirmPassword) throw Exception(PASSWORDS_DO_NOT_MATCH)
            auth.createUserWithEmailAndPassword(email, password).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }

    override suspend fun login(email: String, password: String): Response<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }

    override suspend fun logout(): Response<Boolean> =
        try {
            auth.signOut()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }

    override suspend fun sendEmailVerification(): Response<Boolean> =
        try {
            if(currentUser!!.isEmailVerified) Success(true)
            else {
                currentUser?.sendEmailVerification()?.await()
                Loading
            }
        } catch (e: Exception) {
            Failure(e)
        }

    override suspend fun reload(): Response<Boolean> =
        try {
            while(!currentUser!!.isEmailVerified) {
                currentUser?.reload()?.await()
                delay(2000)
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }

}