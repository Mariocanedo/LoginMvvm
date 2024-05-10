package com.example.myapplication

import android.util.Patterns
import java.util.regex.Pattern

 class User(private val email:String?, private val password: String?) {

    fun getEmail(): String {
        return email ?: ""
    }
    fun getPassword(): String {
        return password ?: ""
    }

    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()

    val isPasswordLengthGreaterThan5: Boolean
        get() = getPassword().length>5

}