package com.example.dictionary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.apiManager.model.Months
import com.example.dictionary.apiManager.networkModel.Art
import com.example.dictionary.view.DictionaryFragment
import kotlinx.android.synthetic.main.example_item_list.view.*

class MonthsAdapter(private val data: ArrayList<Months>) :
    RecyclerView.Adapter<MonthsAdapter.ExampleViewHolder>() {
    var onDeleteClick: ((Int) -> Unit)? = null



    inner class ExampleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val monthsName = itemView.findViewById<TextView>(R.id.nameMain)

         var onBtnDeleteClick = itemView.findViewById<ImageButton>(R.id.btnDelete)


        fun bindData(position: Int) {

            monthsName.text = data[position].name

            onBtnDeleteClick.setOnClickListener {

                onDeleteClick?.invoke(position)

            }

           


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item_list, parent, false)
        return ExampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<Months>) {
        data.clear()
        data.addAll(newList)
    }

    fun delete(position: Int) {
        data.remove(data[position])
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }


}