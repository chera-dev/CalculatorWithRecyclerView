package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textView: TextView
    private lateinit var buttonReset:Button
    private lateinit var action: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonAdd = binding.buttonAdd
        buttonSub = binding.buttonSub
        buttonMultiply = binding.buttonMultiply
        buttonDivide = binding.buttonDivide
        textView= binding.textViewresult
        buttonReset= binding.buttonReset
        buttonReset.setOnClickListener { onReset() }
    }

    fun onButtonClick(view: View){
        action= getButtonText(view)
        val intent= Intent(this,MainActivity2::class.java)
        intent.putExtra("Action",action)
        //startActivity(intent)
        //get data from second activity
        startActivityForResult(intent,1)
    }

    private fun getButtonText(view: View):String{
        return when(view.id){
            R.id.buttonAdd -> buttonAdd.text.toString()
            R.id.buttonSub -> buttonSub.text.toString()
            R.id.buttonMultiply -> buttonMultiply.text.toString()
            else -> buttonDivide.text.toString()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                textView.visibility = View.VISIBLE
                buttonReset.visibility = View.VISIBLE
                buttonAdd.visibility=View.INVISIBLE
                buttonSub.visibility=View.INVISIBLE
                buttonMultiply.visibility=View.INVISIBLE
                buttonDivide.visibility=View.INVISIBLE
                if (data != null) {
                    val num1 = data.getStringExtra("num1")
                    val num2 = data.getStringExtra("num2")
                    val actionResult = operation(num1?.toDoubleOrNull(), num2?.toDoubleOrNull(),action)
                    textView.text = "Action :  $action\nInput1 :  $num1\nInput2 :  $num2\nResult :  $actionResult"
                }
            }
        }
    }
    fun onReset(){
        textView.visibility = View.INVISIBLE
        buttonReset.visibility = View.INVISIBLE
        buttonAdd.visibility=View.VISIBLE
        buttonSub.visibility=View.VISIBLE
        buttonMultiply.visibility=View.VISIBLE
        buttonDivide.visibility=View.VISIBLE
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
            Toast.makeText(this, "enter valid number and try again", Toast.LENGTH_LONG).show()
            return null
        }
    }
}