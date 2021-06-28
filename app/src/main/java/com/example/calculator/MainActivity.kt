package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonAdd = binding.buttonAdd
        buttonSub = binding.buttonSub
        buttonMultiply = binding.buttonMultiply
        buttonDivide = binding.buttonDivide
        val intent = getIntent()
        val num1 = intent.getStringExtra("num1")
        val num2 = intent.getStringExtra("num2")
        val result = intent.getDoubleExtra("result",0.00)
        val action = intent.getStringExtra("action")
        if(num1!=null){
            showResult(num1,num2,result,action)
        }
    }

    fun onButtonClick(view: View){
        val action: String = getButtonText(view)
        val intent= Intent(this,MainActivity2::class.java)
        intent.putExtra("Action",action)
        startActivity(intent)
    }

    private fun getButtonText(view: View):String{
        return when(view.id){
            R.id.buttonAdd -> buttonAdd.text.toString()
            R.id.buttonSub -> buttonSub.text.toString()
            R.id.buttonMultiply -> buttonMultiply.text.toString()
            else -> buttonDivide.text.toString()
        }
    }

    private fun showResult(num1: String, num2: String?, result: Double, action: String?) {
        val num1TextView:TextView = findViewById(R.id.textViewresult)
        val buttonReset:Button = findViewById(R.id.buttonReset)
        num1TextView.visibility = View.VISIBLE
        buttonReset.visibility = View.VISIBLE
        buttonAdd.visibility=View.INVISIBLE
        buttonSub.visibility=View.INVISIBLE
        buttonMultiply.visibility=View.INVISIBLE
        buttonDivide.visibility=View.INVISIBLE
        num1TextView.text = "Action :  $action\nInput1 :  $num1\nInput2 :  $num2\nResult :  $result"

        buttonReset.setOnClickListener {
            finish()
            //destroying the activity instead of making views visible and invisible
            /*num1TextView.visibility = View.INVISIBLE
            buttonReset.visibility = View.INVISIBLE
            buttonAdd.visibility=View.VISIBLE
            buttonSub.visibility=View.VISIBLE
            buttonMultiply.visibility=View.VISIBLE
            buttonDivide.visibility=View.VISIBLE*/
        }
    }
}