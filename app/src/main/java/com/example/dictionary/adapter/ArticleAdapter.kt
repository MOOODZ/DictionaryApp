package com.example.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.apiManager.networkModel.Art
import com.example.dictionary.databinding.ArticleListBinding


class ArticleAdapter(private val data: List<Art>) :
    RecyclerView.Adapter<ArticleAdapter.TextViewHolder>() {
    private lateinit var binding: ArticleListBinding

    var onItemClick : ((String) -> Unit)? = null


    inner class TextViewHolder(binding: ArticleListBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindData(position: Int) {
            binding.tvTitle.text = data[position].ID
            binding.tvArticle.text = data[position].title


            itemView.setOnClickListener {
                onItemClick?.invoke(data[position].ID)

            }




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
        binding = ArticleListBinding.inflate(LayoutInflater.from(parent.context))
        return TextViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bindData(position)



    }

    override fun getItemCount(): Int {
        return data.size
    }

}