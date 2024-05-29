package com.example.wampserver

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainAPI: MainAPI
) : ViewModel() {
    val userList = mutableStateOf(emptyList<User>())

    init {
        viewModelScope.launch {
            userList.value = mainAPI.getAllUsers()
        }
    }

    fun saveUser(user: User) = viewModelScope.launch{
        mainAPI.saveUser(user)
        userList.value = mainAPI.getAllUsers()
    }

}