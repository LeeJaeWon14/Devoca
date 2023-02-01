package com.jeepchief.devoca.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeepchief.devoca.model.database.DevocaDatabase
import com.jeepchief.devoca.model.database.VocaEntity
import com.jeepchief.devoca.model.rest.DevocaService
import com.jeepchief.devoca.model.rest.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel : ViewModel() {
//    private fun retrofit = RetrofitClient.getInstance().create()

    private val _vocaList: MutableLiveData<List<VocaEntity>> by lazy { MutableLiveData<List<VocaEntity>>() }
    val vocaList: LiveData<List<VocaEntity>> get() = _vocaList
    fun getVocaList(context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _vocaList.postValue(
                    DevocaDatabase.getInstance(context).getVocaDAO()
                        .selectVocaAll()
                )
            }
        }
    }

    fun saveVoca(context: Context, voca: VocaEntity) { // Save on Room
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                DevocaDatabase.getInstance(context).getVocaDAO()
                    .insertVoca(voca)
            }
        }
    }
    fun saveVoca() { // Save on Server

    }

    private val _apiTest: MutableLiveData<Response<String>> by lazy { MutableLiveData<Response<String>>() }
    val apiTest: LiveData<Response<String>> get() = _apiTest
    fun getApiTest() {
        viewModelScope.launch {
            _apiTest.value = RetrofitClient.getInstance().create(DevocaService::class.java)
                .getTest()
        }
    }
}