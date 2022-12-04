package com.example.dictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dictionary.R
import com.example.dictionary.apimanager.networkModel.Art
import com.example.dictionary.databinding.ArticleRowBinding


class ArticleAdapter(context: Context, private val data: List<Art>) :
    RecyclerView.Adapter<ArticleAdapter.TextViewHolder>() {
    private lateinit var binding: ArticleRowBinding

    var onItemClickId : ((String) -> Unit)? = null



    inner class TextViewHolder(binding: ArticleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bindData(position: Int) {


            //binding.tvTitle.text = data[position].ID
            binding.tvArticle.text = data[position].title





            itemView.setOnClickListener {
                onItemClickId?.invoke(data[position].ID)

            }







        }

    }

//    var items = ArrayList<Art>()

//    @SuppressLint("NotifyDataSetChanged")
//    fun setUpdateData(items: ArrayList<Art>) {
//        this.items = items
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        binding = ArticleRowBinding.inflate(LayoutInflater.from(parent.context))
        return TextViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bindData(position)
        Glide
            .with(holder.itemView.context)
            .load(data[position].image).placeholder(R.drawable.image)
            .into(binding.ivMain)


    }

    override fun getItemCount(): Int {
        return data.size

    }





}