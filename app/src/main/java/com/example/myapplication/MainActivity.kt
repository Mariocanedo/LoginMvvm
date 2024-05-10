package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),ToastCallback {

    // referencias a binding y viewmodel
    private var binding : ActivityMainBinding? = null
    private var viewModel: LoginViewModel?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        // establecer el View model en la vista de manera segura
        // falto estoa agregar en la sesión
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this

        // establecer call bal
        viewModel?.callback=this



  }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
