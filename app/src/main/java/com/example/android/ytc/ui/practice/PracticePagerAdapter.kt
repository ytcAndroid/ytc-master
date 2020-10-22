package com.example.android.ytc.ui.practice

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.ytc.ui.practice.practicefragments.FragmentPractice1

class PracticePagerAdapter (
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity){
    companion object {
        private val PRACTICES = arrayListOf(
            Screen.PRACTICE1
        )
    }

    override fun getItemCount(): Int {
        return PRACTICES.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (PRACTICES[position]){
            Screen.PRACTICE1 -> {
                FragmentPractice1.createInstance()
            }
        }
    }
}