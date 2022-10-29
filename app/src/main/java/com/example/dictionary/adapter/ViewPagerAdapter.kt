package com.example.dictionary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dictionary.view.EducationFragment
import com.example.dictionary.view.DictionaryFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                EducationFragment()
            }
            1 -> {
                DictionaryFragment()
            }

            else -> {
                Fragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }


}