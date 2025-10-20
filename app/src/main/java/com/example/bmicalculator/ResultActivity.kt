package com.example.bmicalculator

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

        }



        val bmi = intent.getDoubleExtra("bmi_value",0.0)
        val catagori = intent.getStringExtra("bmi_catagori")


        binding.countTxt.text ="Your Bmi: %.2f".format(bmi)
        binding.resultTxt.text = "$catagori"


        when{
            bmi<18.5 ->{
              binding.countTxt.setTextColor(Color.parseColor("#2196F3"))
                binding.resultTxt.setTextColor(Color.parseColor("#2196F3"))
            }
            bmi in 18.5..24.9 -> {
                binding.countTxt.setTextColor(Color.parseColor("#4CAF50"))
                binding.resultTxt.setTextColor((Color.parseColor("#4CAF50")))
            }
            bmi in 25.0 ..29.9 ->{
                binding.countTxt.setTextColor(Color.parseColor("#FF9800"))
                binding.resultTxt.setTextColor(Color.parseColor("#FF9800"))
            }
            else ->{
                binding.countTxt.setTextColor(Color.parseColor("#F44336"))
                binding.resultTxt.setTextColor(Color.parseColor("#F44336"))
            }
        }



    }
}