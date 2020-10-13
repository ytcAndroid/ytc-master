package com.example.android.ytc.test

import org.mockito.Mockito

inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)
