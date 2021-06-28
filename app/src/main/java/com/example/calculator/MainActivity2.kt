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
        var actionResult :Double?
        val intent = getIntent()
        val action = intent.getStringExtra("Action")
        val button: Button = binding.buttonResult
        num1 =binding.editTextNumber
        num2 =binding.editTextNumber2
        button.text  = action
        button.setOnClickListener {
            //finish()
            actionResult = operation(num1.text.toString().toDoubleOrNull(),num2.text.toString().toDoubleOrNull(),action)
            actionResult?.let { toMainActivity(action, it) }
        }
    }
    private fun toMainActivity(action: String?, actionResult: Double){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("num1",num1.text.toString())
        intent.putExtra("num2",num2.text.toString())
        intent.putExtra("result",actionResult)
        intent.putExtra("action",action )
        startActivity(intent)
        finish()
    }
    private fun operation(num1: Double?, num2: Double?, action: String?):Double?{
        if(num1!=null && num2!=null && action!=null){
            return when(action){
                "ADDITION" -> num1+num2
                "SUB" -> num1-num2
                "MULTIPLY" -> num1*num2
                else -> num1/num2
            }
        }
        else{
            Toast.makeText(this, "enter number and try again", Toast.LENGTH_SHORT).show()
            return null
        }
    }
}