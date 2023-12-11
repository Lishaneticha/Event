package com.gebeya.event.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gebeya.event.R
import com.gebeya.event.data.db.model.Events
import com.gebeya.event.viewmodel.MainActivityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventApp(
    mainviewModel: MainActivityViewModel
){
    val navController = rememberNavController()

    var goToHome by rememberSaveable {
        mutableStateOf(false)
    }

    var goToAddEvent by rememberSaveable {
        mutableStateOf(false)
    }

    val showCreateButton = remember{ mutableStateOf(false) }

    val canNavBack = remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.events))
                },
                navigationIcon = {
                    if(canNavBack.value == true){
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack, contentDescription = ""
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if(showCreateButton.value){
                Button(
                    onClick = {
                        navController.navigate(route = "add event")
                    }
                ) {
                    Text(text = "create")
                }
            }
        }
    ) { paddingVal ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingVal)
        ) {
            val listOfEvent = remember {
                mutableStateListOf<Events>()
            }

            NavHost(navController = navController, startDestination = "welcome screen"){
                canNavBack.value = navController.previousBackStackEntry != null
                showCreateButton.value = false
                composable(route = "welcome screen"){
                    Greeting(
                        navToHome = {
                            navController.navigate(route = "home screen")
                        }
                    )
                }

                composable(route = "home screen"){
                    canNavBack.value = navController.previousBackStackEntry != null
                    showCreateButton.value = true
                    HomeScreen(
                        listOfEvent = listOfEvent
                    )
                }

                composable(route = "add event"){
                    canNavBack.value = navController.previousBackStackEntry != null
                    showCreateButton.value = false
                    AddEvent(
                        saveEvent = {
                            listOfEvent.add(it)
//                            navController.navigate(route = "home screen"){
//                                popUpTo("home screen"){
//                                    inclusive = true
//                                }
//                            }
                            navController.navigateUp()
                        },
                        mainViewModel = mainviewModel
                    )
                }

            }

        }
    }


}