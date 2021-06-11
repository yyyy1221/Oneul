package com.example.oneul.viewmodel

import androidx.lifecycle.*
import com.example.oneul.data.Calender
import com.example.oneul.data.CalenderRepository
import com.example.oneul.data.Diary
import com.example.oneul.data.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val calenderRepository: CalenderRepository, private val diaryRepository: DiaryRepository) : ViewModel() {
    val allCalenders: LiveData<List<Calender>> = calenderRepository.allCalenders.asLiveData()
    val allDiaries: LiveData<List<Diary>> = diaryRepository.allDiaries.asLiveData()

    private val _currentSchedule = MutableLiveData<Int>()
    val currentSchedule: LiveData<Int> = _currentSchedule

    fun insertCalender(calender: Calender) = viewModelScope.launch {
        calenderRepository.insert(calender)
    }

    fun insertDiary(diary: Diary) = viewModelScope.launch {
        diaryRepository.insert(diary)
    }

    fun notifyCurrententSchedule() {
        viewModelScope.launch(Dispatchers.Main) {
            _currentSchedule.value = _currentSchedule.value
        }
    }
}

class MainViewModelFactory(private val calenderRepository: CalenderRepository, private val diaryRepository: DiaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(calenderRepository, diaryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}