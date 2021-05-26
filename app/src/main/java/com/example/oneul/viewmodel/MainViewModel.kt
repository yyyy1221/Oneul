package com.example.oneul.viewmodel

import androidx.lifecycle.*
import com.example.oneul.data.Calender
import com.example.oneul.data.CalenderRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CalenderRepository) : ViewModel() {
    val allCalenders: LiveData<List<Calender>> = repository.allCalenders.asLiveData()

    fun insert(calender: Calender) = viewModelScope.launch {
        repository.insert(calender)
    }
}

class MainViewModelFactory(private val repository: CalenderRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}