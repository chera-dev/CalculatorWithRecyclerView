package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(),ItemActionListener {

    private lateinit var textView: TextView
    private lateinit var resetButton: Button
    private var state = 0

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ButtonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MainActivity","oncreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView2)
        resetButton = findViewById(R.id.resetButton)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        adapter = ButtonAdapter(this,this)
        recyclerView.adapter = adapter

        if (state == 0)
            onReset()
        else
            onResult()

        resetButton.setOnClickListener {
            state = 0
            onReset() }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MainActivity","onRestoreInstanceState called")
        textView.text = savedInstanceState.getString("result")
        state = savedInstanceState.getInt("state")
        if ( state == 1)
            onResult()
    }

    override fun onItemClicked(action: String) {
        onButtonClick(action)
    }

    fun onButtonClick(action: String){
        val intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("Action",action)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("MainActivity","onActivityResult called")
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                if (data != null) {
                    state = 1
                    onResult()
                    val num1 = data.getStringExtra("num1").toString()
                    val num2 = data.getStringExtra("num2").toString()
                    val action = data.getStringExtra("action").toString()
                    val actionResult = data.getStringExtra("result").toString()
                    textView.text ="Action :  $action\nInput1 :  $num1\nInput2 :  $num2\nResult :  $actionResult"
                }
            }
        }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        Log.i("MainActivity","onSaveInstanceState called")
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("state",state)
        savedInstanceState.putString("result", textView.text.toString())
    }

    private fun onResult(){
        textView.visibility = View.VISIBLE
        resetButton.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun onReset(){
        textView.visibility = View.GONE
        resetButton.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}