package com.example.bmicalculator

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.appCompatButton.setOnClickListener {
            hideKeyboard()
            val edInput_1 = binding.editTextName.text.toString().trim()
            val edInput_2 = binding.edInput.text.toString().trim()

            if (edInput_1.isEmpty()){
                binding.editTextName.error = "please your (CM)"
                return@setOnClickListener
            }

            if (edInput_2.isEmpty()){
                binding.edInput.error = "Please your Weight (KG)"
                return@setOnClickListener
            }




            val weight = edInput_1.toInt()
            val heightCM = edInput_2.toDouble()
            val heightMeter = heightCM/100

            val bmi = weight/(heightMeter*heightMeter)

            binding.display.text = "$bmi"

            val catagori = when{
                bmi <18.5 -> "Underweight"
                bmi in 18.5..24.9 -> "Normal Weight"
                bmi in 25.0 ..29.9 -> "Over Weight"
                else ->"Obese"
            }




        }


    }
// hide keyboard function
    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }



}