package com.mrizaf.counter.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrizaf.counter.Counter

class HomeViewModel : ViewModel() {

    val counterObject = Counter()
    val counter = MutableLiveData<Int>(0)

    fun counterPlus() {
        counter.value = (counter.value)?.plus(1)
    }

    fun counterMinus() {
        counter.value = (counter.value)?.minus(1)
    }

    fun counterReset() {
        counter.value = 0
    }

    fun counterEdit(title: String, value: String) {
        counter.value = value.toIntOrNull()
    }
}