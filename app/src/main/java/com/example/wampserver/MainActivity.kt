package com.example.wampserver

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    private lateinit var mainAPI: MainAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            CoroutineScope(Dispatchers.IO).launch {
                val list = mainAPI.getAllUsers()
                list.forEach {user->
                    Log.d("MyLog", "User name: ${user.name}")
                }
            }
    }
}