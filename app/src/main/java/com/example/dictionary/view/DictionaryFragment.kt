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
import com.example.dictionary.apiManager.model.Months
import com.example.dictionary.databinding.FragmentDictionaryBinding
import java.lang.reflect.Array.get


class DictionaryFragment : Fragment() {
    private lateinit var monthsList: ArrayList<Months>
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var _binding: ActivityMainBinding
    private lateinit var rvMonths: RecyclerView
    private lateinit var monthsAdapter: MonthsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = ActivityMainBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_dictionary, container, false)
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

        rvMonths = view.findViewById(R.id.rvMonths)
        rvMonths.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        monthsAdapter = MonthsAdapter(monthsList.clone() as ArrayList<Months>)
        rvMonths.adapter = monthsAdapter
        monthsAdapter.notifyDataSetChanged()

        monthsAdapter.onDeleteClick = { removedItem ->
            monthsList.remove(monthsList[removedItem])
            monthsAdapter.notifyItemRemoved(removedItem)

        }

        (activity as MainActivity).textChanged = { text ->
            addText(text)

        }

//        adapterList.itemClickListener = { _, position ->
//            val dialog=AlertDialog.Builder(context)
//            dialog.setTitle("delete item")
//            dialog.setMessage("do you want delete this item")
//
//            //delete item from recyclerview
//            dialog.setPositiveButton(android.R.string.yes) { dialog, which ->
//                items.remove(items.get(position))
//                adapterList.notifyItemRemoved(position)
//            }
//
//            //add item in recyclerview
//            dialog.setNegativeButton(android.R.string.no) { dialog, which ->
////                               items.add(0,num1)
//                adapterList.notifyItemInserted(0)
//            }
//            //update item in recyclerview
//            dialog.setNeutralButton("maybe"){ dialog, which ->
////                               items.set(position,num1/*new item*/)
////                               adapterList.notifyItemInserted(position)
//
//                val cloneList = items.clone() as ArrayList<LetterItem>
//                val filteredList = cloneList.filter { foodItem ->
//                    foodItem.title.contains( "de" )
//                }
//                adapterList.setUpdateData( ArrayList( filteredList ))
//
//            }
//            dialog.show()
//        }



    }

    private fun addText(text: String) {

        if (text.isNotEmpty()) {
            val cloneList = monthsList.clone() as ArrayList<Months>
            val filterList = cloneList.filter { textItem ->
                textItem.name.contains(text)
            }
            monthsAdapter.setData(ArrayList(filterList))


        } else {
            monthsAdapter.setData(monthsList.clone() as ArrayList<Months>)


        }


    }

}
