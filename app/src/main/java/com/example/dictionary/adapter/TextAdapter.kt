package com.example.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.apiManager.model.Text
import com.example.dictionary.databinding.ItemListBinding


class TextAdapter(private val data: ArrayList<Text>) :
    RecyclerView.Adapter<TextAdapter.TextViewHolder>() {
    private lateinit var binding: ItemListBinding
    inner class TextViewHolder(binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(position: Int) {
            binding.titleMain.text = data[position].title_1
            binding.textMain.text = data[position].text_1
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context))
        return TextViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bindData(position)

    }

    override fun getItemCount(): Int {
        return data.size
    }

}