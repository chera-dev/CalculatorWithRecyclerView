package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity2 : AppCompatActivity() {
    private lateinit var num1: EditText
    private lateinit var num2: EditText
    private lateinit var action: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent = getIntent()
        action = intent.getStringExtra("Action").toString()
        val button: Button =  findViewById(R.id.buttonResult)
        num1 = findViewById(R.id.editTextNumber)
        num2 = findViewById(R.id.editTextNumber2)
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
        intent.putExtra("action",action)
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