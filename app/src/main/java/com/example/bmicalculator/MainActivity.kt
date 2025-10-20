package com.example.bmicalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
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


        val adapter = ArrayAdapter.createFromResource(this,R.array.height_units,R.layout.spinner_item)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        binding.spinnerUnit.adapter= adapter


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

            val selectedUnit = binding.spinnerUnit.selectedItem.toString()


            val heightInMeters = when(selectedUnit){
                "cm" -> heightCM / 100
                "feet" -> heightCM * 0.3048
                else -> heightCM / 100
            }

            val bmi = weight / (heightInMeters * heightInMeters)


            val catagori = when{
                bmi <18.5 -> "Underweight"
                bmi in 18.5..24.9 -> "Normal Weight"
                bmi in 25.0 ..29.9 -> "Over Weight"
                else ->"Obese"
            }

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("bmi_value",bmi)
            intent.putExtra("bmi_catagori",catagori)
            startActivity(intent)


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