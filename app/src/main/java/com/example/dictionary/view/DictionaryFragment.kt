package com.example.dictionary.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.adapter.MonthsAdapter
import com.example.dictionary.apiManager.model.Months
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.databinding.FragmentDictionaryBinding
import kotlinx.android.synthetic.main.example_item_list.*


class DictionaryFragment : Fragment() {
    private lateinit var monthsList: ArrayList<Months>
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var _binding: ActivityMainBinding
    private lateinit var rvMonths: RecyclerView
    private lateinit var monthsAdapter: MonthsAdapter
    private lateinit var dialog: AlertDialog
    private lateinit var builder: AlertDialog.Builder

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
            )
        )

        rvMonths = view.findViewById(R.id.rvMonths)
        rvMonths.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        monthsAdapter = MonthsAdapter(monthsList.clone() as ArrayList<Months>)
        rvMonths.adapter = monthsAdapter

        monthsAdapter.onDeleteClick = {

            monthsList.remove(monthsList[it])
            monthsAdapter.notifyItemRemoved(it)

        }

        (activity as MainActivity).textChanged = { text ->
            addText(text)

        }



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

     fun initAlertDialog() {


        builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Item")
        builder.setMessage("Are you sure?")
        builder.setPositiveButton(android.R.string.yes) { _, _ ->


            builder.setNegativeButton(android.R.string.no) { _, _ ->
                monthsAdapter.notifyItemInserted(0)

            }

            dialog = builder.create()
            dialog.show()


        }


    }
}
