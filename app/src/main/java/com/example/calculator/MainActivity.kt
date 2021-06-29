package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textView: TextView
    private lateinit var buttonReset: Button
    private lateinit var action: String
    private lateinit var num1:String
    private lateinit var num2:String
    private var state = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MainActivity","oncreate called")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonAdd = binding.buttonAdd
        buttonSub = binding.buttonSub
        buttonMultiply = binding.buttonMultiply
        buttonDivide = binding.buttonDivide
        textView = binding.textViewresult
        buttonReset = binding.buttonReset
        buttonReset.setOnClickListener {
            state = 0
            onReset() }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        Log.i("MainActivity","onSaveInstanceState called")
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("state",state)
        savedInstanceState.putString("result", textView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MainActivity","onRestoreInstanceState called")
        textView.text = savedInstanceState.getString("result")
        state = savedInstanceState.getInt("state")
        if ( state == 1)
            onResult()
    }

    fun onButtonClick(view: View){
        action = getButtonText(view)
        val intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("Action",action)
        //startActivity(intent)
        //get data from second activity
        startActivityForResult(intent,1)
    }

    private fun getButtonText(view: View): String{
        return when(view.id){
            R.id.buttonAdd -> buttonAdd.text.toString()
            R.id.buttonSub -> buttonSub.text.toString()
            R.id.buttonMultiply -> buttonMultiply.text.toString()
            else -> buttonDivide.text.toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("MainActivity","onActivityResult called")
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                if (data != null) {
                    state = 1
                    onResult()
                    num1 = data.getStringExtra("num1").toString()
                    num2 = data.getStringExtra("num2").toString()
                    val actionResult = data.getStringExtra("result").toString()
                    textView.text ="Action :  $action\nInput1 :  $num1\nInput2 :  $num2\nResult :  $actionResult"
                }
            }
        }
    }

    private fun onResult(){
        textView.visibility = View.VISIBLE
        buttonReset.visibility = View.VISIBLE
        buttonAdd.visibility = View.INVISIBLE
        buttonSub.visibility = View.INVISIBLE
        buttonMultiply.visibility = View.INVISIBLE
        buttonDivide.visibility = View.INVISIBLE
    }

    private fun onReset(){
        textView.visibility = View.INVISIBLE
        buttonReset.visibility = View.INVISIBLE
        buttonAdd.visibility = View.VISIBLE
        buttonSub.visibility = View.VISIBLE
        buttonMultiply.visibility = View.VISIBLE
        buttonDivide.visibility = View.VISIBLE
    }
}