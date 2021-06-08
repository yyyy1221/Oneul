package com.example.oneul.viewmodel

import androidx.lifecycle.*
import com.example.oneul.data.Diary
import com.example.oneul.data.DiaryRepository
import kotlinx.coroutines.launch

class DiaryViewModel(private val repository:DiaryRepository) : ViewModel(){

    val allDiary: LiveData<List<Diary>> = repository.allDiaries.asLiveData()
    lateinit var currentDiary:LiveData<Diary>

    fun insert(d: Diary) = viewModelScope.launch {
        repository.insert(d)
    }

}

class DiaryViewModelFactory(private val repository: DiaryRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}