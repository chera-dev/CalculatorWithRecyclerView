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
    private lateinit var num1: EditText
    private lateinit var num2: EditText
    private lateinit var action: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = getIntent()
        action = intent.getStringExtra("Action").toString()
        val button: Button = binding.buttonResult
        num1 = binding.editTextNumber
        num2 = binding.editTextNumber2
        button.text = action
        button.setOnClickListener {
            val actionResult = operation(num1.text.toString().toDoubleOrNull(), num2.text.toString().toDoubleOrNull())
            if (actionResult != null)
                toMainActivity(actionResult)
        }
    }
    private fun toMainActivity(actionResult: Double?) {
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("num1",num1.text.toString())
        intent.putExtra("num2",num2.text.toString())
        intent.putExtra("result",actionResult.toString())
        setResult(RESULT_OK,intent)
        finish()
    }
    private fun operation(num1: Double?, num2: Double?):Double?{
        return if(num1 != null && num2 != null){
            when(action){
                "ADDITION" -> num1+num2
                "SUB" -> num1-num2
                "MULTIPLY" -> num1*num2
                else -> num1/num2
            }
        }
        else{
            Toast.makeText(this, "enter valid number and try again", Toast.LENGTH_LONG).show()
            null
        }
    }
}