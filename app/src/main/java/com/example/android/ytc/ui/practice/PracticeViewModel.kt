package com.example.android.ytc.ui.practice

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import timber.log.Timber

class PracticeViewModel @ViewModelInject constructor(private val preferences: SharedPreferences): ViewModel(){
    init {
        Timber.d("Injected shared pref")
    }
}