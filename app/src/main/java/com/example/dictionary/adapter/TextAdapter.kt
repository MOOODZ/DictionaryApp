package com.example.dictionary.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.util.Log.w
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.dictionary.apiManager.networkModel.Art
import com.example.dictionary.databinding.ItemListBinding
import java.util.ArrayList


class TextAdapter(private val context: Context , private val data: List<Art>) :
    RecyclerView.Adapter<TextAdapter.TextViewHolder>() {
    private lateinit var binding: ItemListBinding

    inner class TextViewHolder(binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindData(position: Int) {
            binding.titleMain.text = data[position].ID
            binding.textMain.text = data[position].title

            /*Glide
                .with(context)
                .load(data[position].image)
                .into(binding.ivMain)
*/




        }

    }

//    var items = ArrayList<Art>()

//    @SuppressLint("NotifyDataSetChanged")
//    fun setUpdateData(items: ArrayList<Art>) {
//        this.items = items
//        notifyDataSetChanged()
//    }

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