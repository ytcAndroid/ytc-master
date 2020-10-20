package com.example.android.ytc.ui.practice.practicefragments

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import timber.log.Timber


class FragmentPracticeViewModel @ViewModelInject constructor(
    sharedPreferences: SharedPreferences
) : ViewModel() {
    init {
        Timber.i("Ok got it")
    }
}