package com.example.dictionary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dictionary.view.DictionaryFragment
import com.example.dictionary.view.EducationFragment
import com.example.dictionary.view.ImmigrationFragment
import com.example.dictionary.view.TeachingFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return DictionaryFragment()
            }
            1 -> {
                return EducationFragment()
            }
            2 -> {
                return ImmigrationFragment()
            }
            3 -> {
                return TeachingFragment()
            }
            else -> {
                return Fragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return 4
    }


}