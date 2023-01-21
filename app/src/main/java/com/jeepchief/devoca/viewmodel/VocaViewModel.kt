package com.jeepchief.devoca.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeepchief.devoca.model.database.DevocaDatabase
import com.jeepchief.devoca.model.database.VocaEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VocaViewModel : ViewModel() {
    private val _voca: MutableLiveData<VocaEntity> by lazy { MutableLiveData<VocaEntity>() }
    val voca: LiveData<VocaEntity> get() = _voca
    fun getVoca(context: Context, vid: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _voca.postValue(
                    DevocaDatabase.getInstance(context).getVocaDAO()
                        .selectVoca(vid)
                )
            }
        }
    }
}