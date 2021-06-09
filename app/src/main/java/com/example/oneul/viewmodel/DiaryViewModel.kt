package com.example.oneul.viewmodel

import androidx.lifecycle.*
import com.example.oneul.data.Diary
import com.example.oneul.data.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel(private val repository:DiaryRepository) : ViewModel(){

    val allDiaries: LiveData<List<Diary>> = repository.allDiaries.asLiveData()

    private val _currentDiary = MutableLiveData<Diary>()
    val currentDiary: LiveData<Diary> = _currentDiary

    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int> = _id

    fun insert(d: Diary) = viewModelScope.launch {
        repository.insert(d)
    }

    fun setCurrentDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.Main) {
            _currentDiary.value = diary
        }
    }

    fun setCurrentDiary(date: String): Boolean {
        val diary = allDiaries.value?.filter { d -> d.date == date}
        if(diary?.isEmpty() == true) return false
        viewModelScope.launch {
            _currentDiary.value = diary?.get(0)
        }
        return true
    }

    fun notifyCurrentDiary() {
        viewModelScope.launch(Dispatchers.Main) {
            _currentDiary.value = _currentDiary.value
        }
    }

}

class DiaryViewModelFactory(private val repository: DiaryRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}