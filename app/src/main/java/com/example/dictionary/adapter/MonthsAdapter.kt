package com.example.dictionary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.apiManager.model.Months

class MonthsAdapter(private val data: ArrayList<Months>) :
    RecyclerView.Adapter<MonthsAdapter.EmptyViewHolder>() {


    inner class EmptyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val monthsName = itemView.findViewById<TextView>(R.id.nameMain)


        fun bindData(position: Int) {
            monthsName.text = data[position].name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.example_item_list , parent , false)
        return EmptyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData (newList : ArrayList<Months> ){
        data.clear()
        data.addAll( newList )
        notifyDataSetChanged()

    }




}