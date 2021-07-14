package com.example.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ButtonAdapter(private val parentContext: Context, private val actionListener: ItemActionListener): RecyclerView.Adapter<ButtonAdapter.ButtonHolder>() {

    private var buttonName = arrayOf("ADDITION", "SUB", "MULTUPLY", "DIVIDE")

    inner class ButtonHolder(val view: View): RecyclerView.ViewHolder(view) {
        var itemButton: Button
        init {
            itemButton = view.findViewById(R.id.buttonItem)
            itemButton.setOnClickListener {
                val context = view.context
                Toast.makeText(context, "clciked ${buttonName[position]}",Toast.LENGTH_SHORT).show()
                actionListener.onItemClicked(buttonName[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonHolder {
        val itemView = LayoutInflater.from(parentContext).inflate(R.layout.button_view,parent,false)
        return ButtonHolder(itemView)
    }

    override fun onBindViewHolder(holder: ButtonHolder, position: Int) {
        holder.itemButton.text = buttonName[position]
        /*holder.itemButton.setOnClickListener {
            val context = holder.view.context
            Toast.makeText(context, "clciked ${buttonName[position]}",Toast.LENGTH_SHORT).show()
            val action = buttonName[position]
            val intent = Intent(context,MainActivity2::class.java)
            intent.putExtra("Action",action)
            context.startActivity(intent)
        }*/
    }

    override fun getItemCount(): Int {
        return buttonName.size
    }
}