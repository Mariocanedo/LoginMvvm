package com.example.myapplication

import android.text.Html.ImageGetter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


// para agregar un toast y mostarlo en la vista
interface ToastCallback {
    fun showToast(message: String)
}

class LoginViewModel : ViewModel() , ToastCallback{


    // si ocurre un cambio como son observados va a notificar los cambior
    var email = MutableLiveData<String?>()
    var password = MutableLiveData<String?>()

    //me van a servir para comunicarme con la vista
    var emailError = MutableLiveData<String?>()
    var passwordError = MutableLiveData<String?>()
    private var userMutableLivedata: MutableLiveData<User>?
    // para hacer llamadoa  toast
    var callback: ToastCallback? = null

    init {

        userMutableLivedata = MutableLiveData()

    }


    fun onSingInClicked() {

        emailError.value = null
        passwordError.value = null

        val user = User(email.getValue(), password.getValue())
        if (email.getValue() == null || email.getValue()!!.isEmpty()) {

            emailError.value = "ENTER EMAIL ADDRESS."
            return
        }

        if (!user.isEmailValid) {
            emailError.value = "ENTER A VALID EMAIL ADDRESS"
            return
        }

        if (password.getValue() == null || password.getValue()!!.isEmpty()) {
            passwordError.value = "Enter Password"
            return
        }

        if (!user.isPasswordLengthGreaterThan5) {
            passwordError.value = "Password Length should be greater than 5."
            return
        }


        userMutableLivedata!!.value = user
         showToast("Datos Válidos")
        // Si todas las validaciones son exitosas, dejar los campos en blanco
        email.value = null
        password.value = null

    }


     val user: LiveData<User>

         get(){

             if(userMutableLivedata== null){
                 userMutableLivedata = MutableLiveData()
             }
             return  userMutableLivedata!!

         }

    override fun showToast(message: String) {
        callback?.showToast(message)
    }


}