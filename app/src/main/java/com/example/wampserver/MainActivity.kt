package com.example.wampserver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    private lateinit var mainAPI: MainAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val name = remember {
        mutableStateOf("")
    }

    val age = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        ) {
            items(viewModel.userList.value) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        text = it.name, modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                            .padding(10.dp)
                    )
                }
            }
        }

        Column() {
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp), value = name.value, onValueChange = {
                name.value = it
            })
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                value = age.value,
                onValueChange = {
                    age.value = it
                })
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                onClick = {
                    viewModel.saveUser(
                        User(
                            name.value,
                            0,
                            System.currentTimeMillis().toString(),
                            age.value
                        )
                    )
                }) {
                Text(text = "Save dates")
            }
            Spacer(modifier = Modifier.height(5.dp))

        }
    }

}

