package com.example.dictionary.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.adapter.ArticleAdapter
import com.example.dictionary.apimanager.networkModel.Art
import com.example.dictionary.viewmodel.MainViewModel
import org.legobyte.khanedan.ui.dialogs.ArticleDialog


class EducationFragment : Fragment() {
    private lateinit var rvArticle: RecyclerView
    private lateinit var artList: List<Art>
    private lateinit var artListAdapter: ArticleAdapter
    private lateinit var viewModel: MainViewModel
    private var items = ArrayList<Art>()
    private lateinit var articleDialog: ArticleDialog
    private lateinit var pbArticle: ProgressBar
    private lateinit var pbDialog: ProgressBar
    private lateinit var btnRetry: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(com.example.dictionary.R.layout.fragment_education,
            container,
            false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleDialog = ArticleDialog(requireContext())
        pbDialog = requireView().findViewById(com.example.dictionary.R.id.pbDialog)
        pbDialog.visibility = View.GONE


        getListVM()


        btnRetry = view.findViewById(com.example.dictionary.R.id.btnRetry)
        btnRetry.visibility = View.VISIBLE
        btnRetry.setOnClickListener {

            getListVM()
        }


    }


    private fun getListVM() {

        pbArticle = requireView().findViewById(com.example.dictionary.R.id.pbArticle)
        pbArticle.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.wordsListObserve().observe({ lifecycle }, { model ->
            if (model.list_art.isEmpty()) {
                Toast.makeText(requireContext(), "Error Connection!", Toast.LENGTH_SHORT).show()
            } else {
                btnRetry.visibility = View.GONE
                pbArticle.visibility = View.GONE
                items.addAll(model.list_art)
                artListAdapter = ArticleAdapter(requireContext(), items)
                rvArticle = requireView().findViewById(com.example.dictionary.R.id.rvArticle)
                rvArticle.setHasFixedSize(true)
                Log.i("EMPTY_LIST", model.list_art.toString())
                rvArticle.layoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                artList = model.list_art
                artListAdapter = ArticleAdapter(requireContext(), artList)
                rvArticle.adapter = artListAdapter
                artListAdapter.notifyDataSetChanged()

                artListAdapter.onItemClickId = { id ->

                    getDetailArticleVM(id)

                }


            }
        })


        viewModel.getWordsList()

    }


    private fun getDetailArticleVM(id: String) {
        pbDialog.visibility = View.VISIBLE
        viewModel.articlesListObserve().observe({ lifecycle }, { art ->
            Log.i("CHECK_API", "desc ${art.detaile_article.dese.isEmpty()}")
            if (art.detaile_article.dese.isEmpty()) {
                Toast.makeText(context, "Error data", Toast.LENGTH_SHORT).show()
            } else {

                pbDialog.visibility = View.GONE

            }
            Log.i("CHECK_API", art.detaile_article.toString())
            initDialog(
                art.detaile_article.image,
                art.detaile_article.title,
                art.detaile_article.dese
            )


        })
        viewModel.getArticlesList(id)

    }


    private fun initDialog(id: String, title: String, desc: String) {
        articleDialog.apply {


            setup(image = id, title = title, desc = desc)


        }.show()


    }

}

/* Using only Retrofit
private fun getData() {

    val retrofitData = getRetrofitInstance().create(ApiService::class.java)
        .getWords()

    retrofitData.enqueue(object : Callback<Words> {

        override fun onResponse(call: Call<Words>, response: Response<Words>) {
            if (response.body()!!.list_art.isEmpty()) {

                recyclerView.visibility = View.GONE
            } else {
                textList = response.body()!!.list_art
                recyclerView = view!!.findViewById(R.id.recyclerMain)
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = TextAdapter(textList)
                recyclerView.adapter = adapter

            }
        }

        override fun onFailure(call: Call<Words>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })
}*/



