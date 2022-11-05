package com.example.dictionary.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.apiManager.model.Months
import kotlinx.android.synthetic.main.example_item_list.view.*

class MonthsAdapter(private val data: ArrayList<Months>) :
    RecyclerView.Adapter<MonthsAdapter.ExampleViewHolder>() {


    inner class ExampleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val monthsName = itemView.findViewById<TextView>(R.id.nameMain)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
        var onItemClick : ((Int) -> Unit)? = null

        fun bindData(position: Int) {

            monthsName.text = data[position].name


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item_list, parent, false)
        return ExampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bindData(position)
        holder.btnDelete.setOnClickListener() { v: View ->
            Log.i("CLICK_TEST", onClickListener.toString())
            if (onClickListener != null) {
                onClickListener!!.onClick(position)
                removeItem(position)

            }


        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<Months>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()

    }


    private var onClickListener: OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int) {


        }
    }

    private fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }


}