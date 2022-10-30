package com.example.dictionary.view

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.adapter.TextAdapter
import com.example.dictionary.apiManager.networkModel.Art
import com.example.dictionary.databinding.FragmentEducationBinding
import com.example.dictionary.viewmodel.MainViewModel
import org.legobyte.khanedan.ui.dialogs.SuggestDialog
import java.util.ArrayList


class EducationFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentEducationBinding
    private lateinit var textList: List<Art>
    private lateinit var adapter: TextAdapter
    private lateinit var viewModel: MainViewModel
    private var items = ArrayList<Art>()
    private val suggestDialog = SuggestDialog(requireView().context)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_education, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEducationBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupViewModel()

        initDialog()


    }

    private fun setupViewModel() {

        viewModel.wordsListObserve().observe({ lifecycle }, { model ->
            items.addAll(model.list_art)
            adapter = TextAdapter(requireContext(), items)
            recyclerView = requireView().findViewById(R.id.recyclerMain)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            textList = model.list_art
            adapter = TextAdapter(requireContext(), textList)
            recyclerView.adapter = adapter
            //adapter.notifyDataSetChanged()
        })
        viewModel.getWordsList()

    }
    private fun initDialog(){
        suggestDialog.apply {
            setup(
                getString(R.string.title_dialog),
                getString(R.string.btn_send_dialog),
                InputType.TYPE_CLASS_NUMBER,
                getString(R.string.title_dialog),
                getString(R.string.edt_desc_dialog),
                getString(R.string.edt_phone_dialog),
                11,
            )



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


}
