package com.example.android.ytc.ui.practice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.android.ytc.R
import com.example.android.ytc.databinding.ActivityPracticeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PracticeActivity: AppCompatActivity() {
    private val viewModel: PracticeViewModel by viewModels()
    private lateinit var binding: ActivityPracticeBinding
    private lateinit var practicePagerAdapter: PracticePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("Practice created.")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_practice)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupPage()
    }

    private fun setupPage(){
        practicePagerAdapter = PracticePagerAdapter(this)

        selectScreen(Screen.PRACTICE1)

        binding.viewPager.adapter = practicePagerAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                val selectedScreen: Screen = Screen.values()[position]

            }
        })
    }

    private fun selectScreen(screen: Screen){
        scrollToScreen(screen)
    }

    private fun scrollToScreen(screen: Screen){
//        binding.viewPager.setCurrentItem(screen.ordinal, true)
        if(screen.ordinal != binding.viewPager.currentItem) {
            binding.viewPager.setCurrentItem(screen.ordinal, true)

        }
    }
}