package com.example.realm.main.ui.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.realm.base.BaseViewModel
import com.example.realm.main.background.model.User
import com.example.realm.main.background.repository.MainHomeRepository
import com.example.realm.main.background.response.Planets
import kotlinx.coroutines.launch
import retrofit2.Response

class MainHomeViewModel (private val mRepository: MainHomeRepository): BaseViewModel(mRepository), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    private var _planets = MutableLiveData<Planets>()
    val mPlanets: LiveData<Planets> = _planets

    private var _planet = MutableLiveData<com.example.realm.main.background.response.Result>()
    val mPlanet: LiveData<com.example.realm.main.background.response.Result> = _planet

    private var _erroResponse = MutableLiveData<Boolean>()
    val mErrorResponse : LiveData<Boolean> =_erroResponse

    fun saveUser(name: String, lastName1: String, lastName2: String, country: String, birthDate: String){
        var user = User()
        user.name = name
        user.parentLastName = lastName1
        user.lastName = lastName2
        user.birthDate = birthDate
        user.country = country
        mRepository.dao.save(user)
    }


    fun getNullableUser(): User?{
        return mRepository.getNullableUser()
    }

    fun getPlanets(){
        _progressMessage.value = "Consultando Planetas"
        viewModelScope.launch {
            mRepository.getPlanets().apply {
                if (this!!.isSuccessful)
                    _planets.value = this.body()
                else
                    _erroResponse.value = true
                }
            }
        }

    fun getPlanetsById(id: String){
        _progressMessage. value = "Consultando Planeta:" + "id"
        viewModelScope.launch {
            mRepository.getPlanetById(id).apply {
                if (this!!.isSuccessful){
                    _planet.value = this.body()
                } else
                    _erroResponse.value = true
            }
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

}


