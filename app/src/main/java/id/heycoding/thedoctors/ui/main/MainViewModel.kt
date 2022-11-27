package id.heycoding.thedoctors.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.heycoding.thedoctors.data.remote.response.DataItem
import id.heycoding.thedoctors.data.repository.MainRepository

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _listDoctorData = MutableLiveData<List<DataItem>>()
    val listDoctorData: LiveData<List<DataItem>> = _listDoctorData
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getAllDoctors() {
        _isLoading.value = true
        repository.getDoctor({
            _isLoading.value = false
            _listDoctorData.postValue(it.data)
        }, {
            _isLoading.value = false
            _message.value = it.printStackTrace().toString()
        })
    }
}