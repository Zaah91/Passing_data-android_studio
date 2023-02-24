package com.example.passindata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class PersonViewModel : ViewModel() {
    val person = PersonCollectedData("", 0, "", 0)
    val personMutableLiveData: MutableLiveData<PersonCollectedData> = MutableLiveData()
}

data class PersonCollectedData(var name: String, var age: Int = 0, var address: String, var zipCode: Int = 0){
}