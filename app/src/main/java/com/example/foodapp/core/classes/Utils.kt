package com.example.foodapp.core.classes

import android.content.Context
import android.util.Log
import android.widget.Toast

class Utils {
    companion object {
        fun print(e: Exception) = Log.d("AK2000YY", e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}