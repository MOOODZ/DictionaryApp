package com.example.dictionary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.adapter.MonthsAdapter
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.databinding.FragmentDictionaryBinding
import com.example.dictionary.apiManager.model.Months


class TeachingFragment : Fragment() {
    private lateinit var monthsList: ArrayList<Months>
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var _binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MonthsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = ActivityMainBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_teaching, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDictionaryBinding.inflate(layoutInflater)

        monthsList = arrayListOf(
            Months(
                "January"
            ),
            Months(
                "February"
            ),
            Months(
                "March"
            ),
            Months(
                "April"
            ),
            Months(
                "May"
            ),
            Months(
                "June"
            ),
            Months(
                "July"
            ),
            Months(
                "August"
            ),
            Months(
                "September"
            ),
            Months(
                "October"
            ),
            Months(
                "November"
            ),
            Months(
                "December"
            ),


            )




        recyclerView = view.findViewById(R.id.recyclerEmpty)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = MonthsAdapter(monthsList.clone() as ArrayList<Months>)
        recyclerView.adapter = adapter

        (activity as MainActivity).textChanged = {text->
            addText(text)
        }



    }

    private fun addText(text:String) {

        if (text.isNotEmpty()){
            val cloneList = monthsList.clone() as ArrayList<Months>
            val filterList = cloneList.filter {textItem->
                textItem.name.contains(text)
            }
            adapter.setData( ArrayList( filterList ) )


        }else{
            adapter.setData(monthsList.clone() as ArrayList<Months>)



        }




    }

}
