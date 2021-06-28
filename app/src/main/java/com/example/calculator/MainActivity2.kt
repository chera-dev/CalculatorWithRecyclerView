package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.calculator.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var num1:EditText
    private lateinit var num2:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = getIntent()
        val action = intent.getStringExtra("Action")
        val button: Button = binding.buttonResult
        num1 =binding.editTextNumber
        num2 =binding.editTextNumber2
        button.text  = action
        button.setOnClickListener {
            toMainActivity(action)
        }
    }
    private fun toMainActivity(action: String?){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("num1",num1.text.toString())
        intent.putExtra("num2",num2.text.toString())
        setResult(RESULT_OK,intent)
        finish()
    }
}