package com.gebeya.event.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.gebeya.event.data.db.model.Events
import com.gebeya.event.viewmodel.MainActivityViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventApp(
    mainviewModel: MainActivityViewModel
){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val showMore = remember {
            mutableStateOf(false)
        }

        var goToHome by rememberSaveable {
            mutableStateOf(false)
        }

        var goToAddEvent by rememberSaveable {
            mutableStateOf(false)
        }

        val listOfEvent = remember {
            mutableStateListOf<Events>()
        }
        // A surface container using the 'background' color from the theme

        if(goToHome){
            HomeScreen(
                navBack = {
                    goToHome = false
                },
                navToAddEvent = {
                    goToHome = false
                    goToAddEvent = true
                },
                listOfEvent = listOfEvent
            )
        }else if(goToAddEvent){
            AddEvent(
                saveEvent = {
                    listOfEvent.add(it)
                    goToHome = true
                },
                mainViewModel = mainviewModel
            )
        }else{
            Greeting(
                navToHome = {
                    goToHome = true
                }
            )
        }
    }
}