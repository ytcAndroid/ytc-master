package com.example.android.ytc.ui.practice.practicefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.ytc.R
import com.example.android.ytc.databinding.FragmentPractice1Binding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class FragmentPractice1: Fragment() {
    companion object {
        fun createInstance(): FragmentPractice1 {
            return FragmentPractice1()
        }
    }

    lateinit var binding : FragmentPractice1Binding
    val viewModel: FragmentPracticeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Got injected from %s", viewModel)
        Timber.d("FRAGMENT CREATED YEAH")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_practice_1, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigationEvents(viewModel)
    }

    private fun observeNavigationEvents(viewModel: FragmentPracticeViewModel){

    }
}